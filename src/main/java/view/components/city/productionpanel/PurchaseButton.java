package view.components.city.productionpanel;

//import javafx.scene.control.Button;

import com.jfoenix.controls.JFXButton;

class PurchaseButton extends JFXButton {
    String text;

    public PurchaseButton(String text){
        super(text);
        this.text = text;
        init();
    }

    public void init(){
        this.setHeight(15);

        this.setStyle("-fx-background-color: black");
        this.setStyle("-fx-border-radius: 15");
        this.setStyle("-fx-border-color: #605a1f");
        this.setStyle("-fx-border-width: 5");
        this.setStyle("-fx-text-fill: #ffeea0");
    }

}


