package utils;

import java.util.*;

public class CommandProcessor {

    private static String[] mergeTwoArray(String[] first, String[] second) {
        String[] out;
        if(first != null && second != null){
            out = new String[first.length + second.length];
            System.arraycopy(first, 0, out, 0, first.length);
            System.arraycopy(second, 0, out, first.length, second.length);
        }else if(first != null){
            out = new String[first.length];
            System.arraycopy(first, 0, out, 0, first.length);
        }else if(second != null){
            out = new String[second.length];
            System.arraycopy(second, 0, out, 0, second.length);
        }else{
            out = null;
        }
        return out;
    }

    private static HashMap<String, String> replaceAbbreviatedArgs(HashMap<String, String> hashMap, String[] keys) {
        if(keys == null)
            return hashMap;
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
        return realOut;
    }

    private static HashMap<String, String> addKeyValues(HashMap<String, String> in, String[] args, int startIndex) {
        for (int i = startIndex; i < args.length - 1; i += 2) {
            in.put(args[i], args[i + 1]);
        }
        return in;
    }

    private static boolean doesContainRequiredKeys(HashMap<String, String> in, String[] requiredKeys){
        if(requiredKeys == null)
            return true;
        List<String> listOfKeys = Arrays.asList(requiredKeys);
        for(String key : listOfKeys)
            if(!in.containsKey(key))
                return false;
        return true;
    }

    private static boolean extractSingleArg(HashMap<String, String> in, String[] splittedCommand, int singleArgsCount){
        if(splittedCommand.length < singleArgsCount)
            return false;
        if (singleArgsCount >= 1) {
            if(splittedCommand[0].equals(""))
                return false;
            in.put("section", splittedCommand[0]);
        }
        if (singleArgsCount >= 2) {
            if(splittedCommand[1].equals(""))
                return false;
            in.put("subsection", splittedCommand[1]);
        }
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

        input = input.substring(command.offset.length());
        input = input.trim();
        String[] splittedCommand = input.split(" ");
        HashMap<String, String> out = new HashMap<>();
        if(!extractSingleArg(out, splittedCommand, singleArgsCount))
            return null;
        addKeyValues(out, splittedCommand, singleArgsCount);
        out = replaceAbbreviatedArgs(out,mergeTwoArray(required,optional));
        if(!doesContainRequiredKeys(out, required))
            return null;
        return out;
    }

    public static void main(String[] args){
        HashMap<String, String> out = extractCommand("INFO CITY", Commands.INFO);
        System.out.println(out);
    }
}
