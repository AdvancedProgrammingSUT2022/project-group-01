package utils;

import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;

public class GraphicUtils {

    public static ImagePattern getImage(String path){
        return new ImagePattern(new Image(GraphicUtils.class.getResource("/asset" + path).toExternalForm()));
    }

    public static String getResourcePath(String path){
        return GraphicUtils.class.getResource(path).toExternalForm();
    }
}
