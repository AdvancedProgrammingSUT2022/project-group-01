package utils;

import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import view.Main;

public class GraphicUtils {

    public static ImagePattern getImage(String path){
        return new ImagePattern(new Image(GraphicUtils.class.getResource("../asset" + path).toExternalForm()));
    }

    public static String getResourcePath(String path){
        return GraphicUtils.class.getResource(path).toExternalForm();
    }

    public static void main(String[] args){
        try {
            ImagePattern img = GraphicUtils.getImage("/popup/ok.png");
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
