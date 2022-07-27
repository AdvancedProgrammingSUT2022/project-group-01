package view;

import java.util.HashMap;

public interface CommandAction {
    String action(HashMap<String, String> args);
}
