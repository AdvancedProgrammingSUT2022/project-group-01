package view;

import controller.*;
import model.User;
import model.technology.TechnologyType;

import java.util.Scanner;


public class Main {
    public static Scanner scanner;
    public static int PORT = 9090;

    public static void main(String[] args) {
        scanner = new Scanner(System.in);
        if(!NetworkController.connect("localhost", PORT)){
            System.out.println("Error in Connection ...");
            return;
        }

//        ProgramController programController = new ProgramController();
//        programController.run();
        ProgramController pc = new ProgramController();
        GUIController guiController = new GUIController();
        guiController.run();
        pc.run();
    }
}
