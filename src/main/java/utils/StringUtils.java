package utils;

public class StringUtils {
    public static String convertToPascalCase(String input){
//		input = input.replaceAll("_", " ");
        StringBuilder res = new StringBuilder();
        for(int i = 0; i < input.length(); i++){
            if(!(i == 0 || input.charAt(i - 1) == '_'))
                res.append(Character.toLowerCase(input.charAt(i)));
            else
                res.append(input.charAt(i));
        }
        return res.toString();
    }
}