package view.components.popup;

import javafx.scene.paint.ImagePattern;
import lombok.Getter;
import utils.GraphicUtils;

@Getter
public enum PopUpStates {
    OK("/popup/ok.png"),
    WARNING("/popup/warning.png"),
    ERROR("/popup/error.png");

    ImagePattern image;
    PopUpStates(String path){
        this.image = GraphicUtils.getImage(path);
    }


}
