import entity.MyScanner;
import entity.RegexPatterns;

import java.util.List;

public class Main {

    public static void main(String[] args)
    {
        MyScanner scanner = new MyScanner();

        String str = scanner.readFile("C:\\school\\limbaje\\lab\\FLCD\\SymbolTable\\src\\main\\resources\\p1.txt");

        scanner.scan(str);

    }
}
