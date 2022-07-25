import controller.*;
import lombok.SneakyThrows;
import model.Game;
import model.User;
import model.unit.Unit;
import model.unit.UnitType;
import network.Request;
import network.Response;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import utils.StringUtils;
import view.components.ImagesAddress;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Vector;


public class Main {
    public static Scanner scanner;
    public static int PORT = 9090;

    @SneakyThrows
    public static void main(String[] args)   {
        scanner = new Scanner(System.in);
        if(!NetworkController.connect("localhost", PORT)){
            System.out.println("Error in Connection ...");
            return;
        }

        ProgramController programController = new ProgramController();
        programController.run();
    }

}
