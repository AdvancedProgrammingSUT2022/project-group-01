import utils.Commands;

public class Main {
    public static void main(String[] args){
        Commands cmd = Commands.INFO;
        System.out.println(cmd.requiredKeys == null);
    }
}
