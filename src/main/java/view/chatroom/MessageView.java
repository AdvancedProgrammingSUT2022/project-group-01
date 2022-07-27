package view.chatroom;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import lombok.Getter;
import lombok.Setter;
import utils.GraphicUtils;

import java.awt.font.FontRenderContext;
import java.awt.geom.AffineTransform;
import java.time.LocalTime;

@Getter @Setter
public class MessageView extends VBox{
    private String text;
    private LocalTime time;

    private Label textLabel;
    private Label timeLabel;
    private final HBox holder = new HBox();
    private Rectangle seenIcon;
    private final boolean isSender;
    private final boolean isPrivate;
    private final Message message;

    public MessageView(Message message,boolean isSender, boolean isPrivate){
        this.message = message;
        this.text = message.getText();
        this.time = message.getTime();
        this.isSender = isSender;
        this.isPrivate = isPrivate;
        init();
    }

    private void init(){
        this.getStylesheets().add(getClass().getResource("/CSS/Message.css").toExternalForm());
        this.setMinWidth(290);
        this.setMaxWidth(290);
        initName();
        initTextLabel();
        initTimeLabel();
        initTextHBox();
        initSeenIcon();
    }

    private void initName(){
        if(isPrivate || isSender)
            return;
        Label label = new Label(message.getSender().getNickname());
        label.setStyle("-fx-text-fill: #333333");
        this.getChildren().add(label);
        Circle cr = new Circle(10);
        holder.getChildren().add(cr);
    }

    private void initTextLabel(){
        textLabel = new Label(text);
        if(isSender) {
            textLabel.setStyle("-fx-text-fill: #ffffff");
            holder.setAlignment(Pos.CENTER_RIGHT);
            this.setAlignment(Pos.CENTER_RIGHT);
        }
        else {
            textLabel.setStyle("-fx-text-fill: #000000");
            holder.setAlignment(Pos.CENTER_LEFT);
            this.setAlignment(Pos.CENTER_LEFT);
        }
        Font font = new Font("Times New Roman", 12);
        textLabel.setFont(font);
        textLabel.setMinWidth(Region.USE_PREF_SIZE);
    }

    private void initTimeLabel(){
        timeLabel = new Label(padding(time.getHour(),2,'0')+":"+padding(time.getMinute(), 2, '0'));
        timeLabel.setStyle("-fx-text-fill: #333333");
        FontRenderContext frc = new FontRenderContext(new AffineTransform(), true, true);
        Font font = new Font("Times New Roman", 8);
        timeLabel.setFont(font);
    }

    private void initTextHBox(){
        holder.setMaxWidth(textLabel.getMinWidth());
        holder.setSpacing(5);
        if(isSender)
            holder.getStyleClass().add("text-hBox-sender");
        else
            holder.getStyleClass().add("text-hBox-receiver");
        holder.getChildren().add(textLabel);
        this.getChildren().add(holder);
        this.getChildren().add(timeLabel);
    }

    private void initSeenIcon(){
        if(!isSender)
            return;
        seenIcon = new Rectangle(10,10);
        seenIcon.setFill(GraphicUtils.getImage("/chatroom/sent.png"));
        holder.getChildren().add(seenIcon);
    }

    public void seen(){
        if(seenIcon == null)
            return;
        seenIcon.setFill(GraphicUtils.getImage("/chatroom/seen.png"));
    }

    private static String padding(int a, int count, char pad){
        StringBuilder sb = new StringBuilder(String.valueOf(a));
        int currentLength = sb.length();
        for(int i=0;i< count - currentLength; i++){
            sb.insert(0,pad);
        }
        return sb.toString();
    }
}
