package entity;

import java.util.*;
import java.util.regex.Pattern;

public class RegexPatterns {
    Map<String, String> patterns;
    List<String> reservedWords;
    List<String> types;

    public RegexPatterns() {
        reservedWords = new ArrayList<>();
        reservedWords.addAll(List.of("main", "let", "const", "char", "do", "else", "if", "readFromKb", "writeToScr",
                "readFromFile", "writeToFile", "for", "while", "func", "true", "false"));

        patterns = new HashMap<>();
        patterns.put("^\".\"", "string");
        patterns.put("[0-9]{1}|[1-9]+[0-9]*", "int");
        patterns.put("[_a-zA-Z][_a-zA-Z0-9]{0,30}", "identifier");
        patterns.put("[_a-zA-Z][_a-zA-Z0-9]{0,30}\\[[_a-zA-Z0-9]{0,20}\\]", "identifier");
        patterns.put("[+|-|*|/|>|<]", "arithmetic");
        patterns.put("\\|\\|", "logical");
        patterns.put("&&", "logical");
        patterns.put(":=", "arithmetic");
        patterns.put("%", "arithmetic");
        patterns.put(":==", "arithmetic");
        patterns.put("<:=", "arithmetic");
        patterns.put(">:=", "arithmetic");
        patterns.put("[{|}|\\(|\\)|\\(\\)|;|: ]", "separator");

        types = new ArrayList<>();
        types.add("int");
        types.add("int[]");
        types.add("string");
        types.add("double");
        types.add("double[]");
        types.add("float");
        types.add("float[]");
        types.add("bool");
        types.add("bool[]");
    }

    public String evaluate(String str) {
        if (reservedWords.contains(str))
            return "reserved";
        if(types.contains(str))
            return "type";
        var result = patterns.entrySet().stream().filter(p -> Pattern.compile(p.getKey()).matcher(str).matches())
                .map(Map.Entry::getValue)
                .findFirst();
       // System.out.println("result is " + result);
        if (result.equals(Optional.empty())) {
            return "error";
        }
        return result.get();
    }

}
