package entity;

import java.util.Scanner;

public class MyScanner {
    SymbolTable symbolTable;

    public MyScanner() {
        this.symbolTable = new SymbolTable();
    }

    public hashResponse register(String value)
    {
        return symbolTable.add(value);
    }

    public hashResponse getIdentifier()
    {
        Scanner scanner = new Scanner(System.in);
        String next = scanner.next();
        return register(next);
    }

}
