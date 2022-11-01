package entity;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class MyScanner {
    SymbolTable symbolTable;
    PIF pif;
    RegexPatterns regex;

    public MyScanner() {
        this.symbolTable = new SymbolTable();
        pif = new PIF();
        regex = new RegexPatterns();
    }

    public hashResponse register(String value) {
        return symbolTable.add(value);
    }

    public hashResponse getIdentifier() {
        Scanner scanner = new Scanner(System.in);
        String next = scanner.next();
        return register(next);
    }

    public String readFile(String path) {
        Path fileName = Path.of(path);

        String str = null;
        try {
            str = Files.readString(fileName);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return str;
    }

    public List<String> parseProgram(String program) {
        return Arrays.stream(program.split("[;,(){} ]")).toList();
    }

    public void scan(String program) {
        var parsed = parseProgram(program);

        for (var p : parsed) {
            p = p.replaceAll("\\s+", "");
            if (!Objects.equals(p, "")) {
                String type = regex.evaluate(p);
                if (type.equals("error")) {
                    try {
                        FileWriter myWriter = new FileWriter("result.out");

                        hashResponse response = findLocation(p, program);
                        myWriter.write("Error occured : unknown token at line " + response.getKey() + " and column " + response.getValue() + ". Token is " + p);
                        myWriter.close();
                    } catch (IOException e) {
                        System.out.println("An error occurred.");
                        e.printStackTrace();
                    }
                    return;
                }
                System.out.println(p + " is " + type);

                if (type.equals("identifier")) {
                    if (symbolTable.isPresent(p)) {
                        pif.add(p, symbolTable.get(p).getKey());
                        // System.out.println();
                    } else {
                        System.out.println("response from adding symbol is " + symbolTable.add(p).getValue());
                        pif.add(p, symbolTable.get(p).getKey());
                    }
                } else {
                    //System.out.println("adding -1");
                    pif.add(p, -1);
                }
            }
        }

        printResults();
    }

    private hashResponse findLocation(String p, String program) {
        int line = 1;

        int index = program.indexOf(p);
        var prog2 = program.toCharArray();

        int column = 1;
        for (int i = 0; i < index; ++i) {
            if (prog2[i] == '\n') {
                line++;
                column = 1;
            }
            column++;
        }

        return new hashResponse(line, column);
    }

    public void clear() {
        pif = new PIF();
        symbolTable = new SymbolTable();
    }

    public void printResults() {
        System.out.println("PIF is : \n");
        System.out.println(pif.toString());
        System.out.println("symbolTable is : \n");
        System.out.println(symbolTable.toString());

        try {
            FileWriter myWriter = new FileWriter("PIF.out");
            myWriter.write(pif.toString());
            myWriter.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        try {
            FileWriter myWriter = new FileWriter("ST.out");
            myWriter.write(symbolTable.toString());
            myWriter.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        try {
            FileWriter myWriter = new FileWriter("result.out");

            myWriter.write("Succes!");
            myWriter.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

}
