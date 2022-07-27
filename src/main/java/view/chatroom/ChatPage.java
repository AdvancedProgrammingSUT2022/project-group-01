package view.chatroom;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import controller.SocketHandler;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.MouseButton;
import javafx.scene.input.ScrollEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import lombok.Getter;
import lombok.Setter;
import model.User;
import utils.GraphicUtils;

import java.util.ArrayList;
import java.util.Vector;

@Getter @Setter
public class ChatPage extends BorderPane {

    private final User self;
    private final Vector<User> users = new Vector<User>();
    private Chat chat;
    private ScrollPane scrollPane = new ScrollPane();
    private VBox vBox = new VBox();
    private JFXTextField textField = new JFXTextField();
    private JFXButton sendBtn = new JFXButton();
    private JFXButton editBtn = new JFXButton();
    private MessageView editingMessage;
    private SocketHandler socketHandler;
    HBox bottomHolder = new HBox();
    private final boolean isPrivate;
    public ChatPage(User self, ArrayList<User> users, Chat chat, SocketHandler socketHandler){
        this.self = self;
        this.users.addAll(users);
        this.chat = chat;
        this.socketHandler = socketHandler;
        isPrivate = (users.size() == 2);
        init();

    }

    private void init(){
        initBorderPane();
        initHeader();
        initScrollPane();
        initVBox();
        initTextBox();
        initSendButton();
        initEditButton();
    }

    public void initBorderPane(){
        this.getStylesheets().add(getClass().getResource("/CSS/Chat.css").toExternalForm());
        this.setMinWidth(300);
        this.setMaxWidth(300);
        this.setMinHeight(400);
    }

    private void initHeader(){
        HBox hbox = new HBox();
        hbox.getStyleClass().add("header");
        hbox.setAlignment(Pos.CENTER_LEFT);
        hbox.setSpacing(5);
        if(isPrivate) {
            Rectangle profilePicture = new Rectangle(20, 20);
            profilePicture.setFill(Color.BLACK);//todo handle it
            hbox.getChildren().add(profilePicture);
        }

        Label nameLabel = new Label(isPrivate?users.get(1-users.indexOf(self)).getNickname(): chat.getChatName());
        nameLabel.getStyleClass().add("header-font");
        hbox.getChildren().add(nameLabel);
        this.setTop(hbox);
    }

    private void initScrollPane(){
        this.setCenter(scrollPane);
        scrollPane.setContent(vBox);
        scrollPane.getStyleClass().add("scroll-pane");
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.addEventFilter(ScrollEvent.SCROLL,new EventHandler<ScrollEvent>() {
            @Override
            public void handle(ScrollEvent event) {
                if (event.getDeltaX() != 0) {
                    event.consume();
                }
            }
        });
        vBox.getStyleClass().add("v-box");
    }

    private void initVBox(){
        vBox.setPadding(new Insets(3,3,3,3));
        for(Message message: chat.getMessages()){
            vBox.getChildren().add(new MessageView(message, message.getSender() == self, false));
        }
    }

    private void initTextBox(){
        bottomHolder.getChildren().add(textField);
        Font font = new Font("Times New Roman", 13);
        textField.setFont(font);
        textField.getStyleClass().add("text-field");
        textField.setPromptText("Enter your message");
        textField.setOnKeyTyped(keyEvent -> {
            if(textField.getText().length() > 51)
                textField.setText(textField.getText().substring(0,50));
            try {
                int code = (int) keyEvent.getCharacter().charAt(0);
                if (code == 10 || code == 13)
                    if(bottomHolder.getChildren().contains(sendBtn))
                        sendBtn.getOnAction().handle(null);
                    else if(bottomHolder.getChildren().contains(editBtn))
                        editBtn.getOnAction().handle(null);
            }catch(Exception ignored){

            }
        });

        this.setBottom(bottomHolder);
    }

    private void initSendButton(){
        bottomHolder.getChildren().add(sendBtn);
        sendBtn.setOnAction(actionEvent -> {
            String text = textField.getText().trim();
            if(text.length() < 1)
                return;
            textField.setText("");
            Message message = chat.sendMessage(text,self);
            MessageView messageView = new MessageView(message,true, false);
            System.out.println("?????????? " + socketHandler);
            socketHandler.sendMessage(text, chat.getId());
            vBox.getChildren().add(messageView);
            initContextMenu(messageView);
        });
        sendBtn.getStyleClass().add("send-btn");
        Rectangle rect = new Rectangle(25,25);
        rect.setFill(GraphicUtils.getImage("/send_icon.png"));
        sendBtn.setGraphic(rect);
    }

    public void addMessage(User sender, String text,boolean isSender){
        Message message = this.chat.sendMessage(text, sender);
        MessageView messageView = new MessageView(message,isSender, isPrivate);
        vBox.getChildren().add(messageView);
        if(isSender)
            initContextMenu(messageView);
    }

    public void editMessage(String newText, int messageId){
        chat.getMessages().get(messageId).setText(newText);
        ((MessageView)vBox.getChildren().get(messageId)).getTextLabel().setText(newText);
    }

    public void deleteMessage(int messageId){
        chat.getMessages().remove(messageId);
        vBox.getChildren().remove(messageId);
    }

    private void initEditButton(){
        editBtn.getStyleClass().add("send-btn");
        Rectangle rect = new Rectangle(25,25);
        rect.setFill(GraphicUtils.getImage("/chatroom/edit.png"));
        editBtn.setGraphic(rect);
        editBtn.setOnAction(action -> {
            editingMessage.getTextLabel().setText(textField.getText());
            editingMessage.getMessage().setText(textField.getText());
            socketHandler.editMessage(textField.getText(), chat.getMessages().indexOf(editingMessage.getMessage()),chat.getId());
            textField.setText("");
            bottomHolder.getChildren().remove(editBtn);
            bottomHolder.getChildren().add(sendBtn);
        });
    }

    private void initContextMenu(MessageView messageView){
        ContextMenu context = new ContextMenu();
        MenuItem editItem = new MenuItem("Edit");
        editItem.setOnAction(actionEvent -> {
            editingMessage = messageView;
            textField.setText(messageView.getText());
            bottomHolder.getChildren().remove(sendBtn);
            bottomHolder.getChildren().add(editBtn);
        });
        MenuItem deleteItem = new MenuItem("Delete");
        deleteItem.setOnAction(actionEvent -> {
            socketHandler.deleteMessage(chat.getMessages().indexOf(messageView.getMessage()), chat.getId());
            chat.getMessages().remove(messageView.getMessage());
            vBox.getChildren().remove(messageView);
        });
        MenuItem cancelItem = new MenuItem("Cancel");
        context.getItems().addAll(editItem, deleteItem, cancelItem);
        messageView.setOnMouseClicked(mouseEvent -> {
            if(mouseEvent.getButton().equals(MouseButton.SECONDARY))
                context.show(ChatPage.this, mouseEvent.getScreenX(), mouseEvent.getScreenY());
        });
    }

}
