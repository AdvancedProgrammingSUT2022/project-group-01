package view.components;

import javafx.scene.image.Image;
import view.Main;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Objects;

public enum ImagesAddress {
    GAME_BACKGROUND("MainMenuBG", ".JPEG"),
    MAIL("mail",".png"),
    MAILED("mailSent",".png"),
    SEARCH("search",".png");


    private final String partA;
    private final String partB;

    ImagesAddress(String partA, String partB) {
        this.partA = partA;
        this.partB = partB;
    }

    public String getAddress(String i) {
        URL address = null;
        try {
            address = new URL(Objects.requireNonNull(Main.class.getResource("/asset/"+ partA + i + partB)).toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return Objects.requireNonNull(address).toString();
    }

    public String getAddress() {
        URL address = null;
        try {
            address = new URL(Objects.requireNonNull(Main.class.getResource("/asset/" + partA + partB)).toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return Objects.requireNonNull(address).toString();
    }

    public Image getImage(int i, String... specifiers) {
        StringBuilder output = new StringBuilder(String.valueOf(i));
        for (String string : specifiers) output.append(string);
        return new Image(getAddress(output.toString()));
    }

    public Image getImage() {
        return new Image(getAddress());
    }
}
