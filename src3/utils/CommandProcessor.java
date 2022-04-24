package utils;

import model.Command;

import java.util.*;
import java.util.regex.*;

public class CommandProcessor {
    private static HashMap<String, String> extractKeyValueFromCommand(String input) {
        System.out.println("first: "+input);
        HashMap<String, String> abbreviatedCommands = new HashMap<String, String>() {
            {
                put(" -p ", " --password ");
                put(" -u ", " --username ");
                put(" -n ", " --nickname ");
                put(" -c ", " --current ");
            }
        };
        // replace ordinary regexes
        for (Map.Entry<String, String> set : abbreviatedCommands.entrySet()) {
            input = input.replaceAll(set.getKey(), set.getValue());
        }
        // replace player regex
        Matcher matcher = Pattern.compile(" -p[\\d+] ").matcher(input);
        while (matcher.find()) {
            String ithPlayer = matcher.group();
            String realIthPlayer = ithPlayer.replace("p", "-player");
            input = input.replace(ithPlayer, realIthPlayer);
        }
        System.out.println("now: " + input);
        input = input.replaceAll("-", "");
        System.out.println("and now: " + input);
        String[] splittedString = input.split(" ");
        HashMap<String, String> out = new HashMap<>();
        for (int i = 0; i < splittedString.length-1; i += 2) {
            out.put(splittedString[i], splittedString[i + 1]);
        }
        return out;
    }

    public static HashMap<String, String> extractCommandFromString(String input, Command command) {
        input = input.trim();
        input = input.replaceAll("\\s+", " ");
        if (!input.startsWith(command.offset))
            return null;
        input = input.substring(command.offset.length()+1);
        System.out.println(input);
        HashMap<String, String> argumans = extractKeyValueFromCommand(input);
        if (command.necessaryArgs != null) {
            // checking input contains all args
            String[] args = command.necessaryArgs;
            for (int i = 0; i < args.length; i++) {
                if (!argumans.containsKey(args[i])) {
                    return null;
                }
            }
        }
        return argumans;
    }
}