package utils;

import java.util.*;

public class CommandProcessor {

    private static String[] mergeTwoArray(String[] first, String[] second) {
        String[] out = new String[first.length + second.length];
        System.arraycopy(first, 0, out, 0, first.length);
        System.arraycopy(second, 0, out, first.length, second.length);
        return out;
    }

    private static HashMap<String, String> replaceAbbreviatedArgs(HashMap<String, String> hashMap, String[] keys) {
        HashMap<String, String> out = new HashMap<>();
        for (Map.Entry<String, String> set : hashMap.entrySet()) {
            for (String i : keys) {
                if (set.getKey().equals("-" + i.charAt(0))) {
                    out.put(i, set.getValue());
                } else {
                    out.put(set.getKey(), set.getValue());
                }
            }
        }
        HashMap<String, String> realOut = new HashMap<>();
        for(Map.Entry<String, String> set : out.entrySet()){
            realOut.put(set.getKey().replaceAll("-",""), set.getValue());
        }
        out = realOut;
        return out;
    }

    private static HashMap<String, String> addKeyValues(HashMap<String, String> in, String[] args, int startIndex) {
        for (int i = startIndex; i < args.length - 1; i += 2) {
            in.put(args[i], args[i + 1]);
        }
        return in;
    }

    private static boolean doesContainRequiredKeys(HashMap<String, String> in, String[] requiredKeys){
        List listOfKeys = Arrays.asList(requiredKeys);
        for(Map.Entry<String, String> set: in.entrySet())
            if(!listOfKeys.contains(set.getKey()))
                return false;
        return true;
    }

    public static HashMap<String, String> extractCommand(String input, Commands command) {
        input = input.toLowerCase();
        input = input.trim();
        input = input.replaceAll("\\s+", " ");
        if (!input.startsWith(command.offset))
            return null;
        String[] required = command.requiredKeys;
        String[] optional = command.optionalKeys;
        int singleArgsCount = command.singleArgsCount;

        input = input.substring(command.offset.length() + 1);
        String[] splittedCommand = input.split(" ");
        HashMap<String, String> out = new HashMap<>();
        if (singleArgsCount >= 1) {
            out.put("section", splittedCommand[0]);
        }
        if (singleArgsCount >= 2) {
            out.put("subsection", splittedCommand[1]);
        }
        addKeyValues(out, splittedCommand, singleArgsCount);
        replaceAbbreviatedArgs(out,mergeTwoArray(required,optional));
        if(!doesContainRequiredKeys(out, required))
            return null;
        return out;
    }
}
