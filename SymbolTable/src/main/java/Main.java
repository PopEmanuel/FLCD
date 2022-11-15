import entity.FiniteAutomata.FA;
import entity.FiniteAutomata.State;
import entity.PIF.MyScanner;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws FileNotFoundException {
        MyScanner scanner = new MyScanner();

        String str = scanner.readFile("C:\\school\\limbaje\\lab\\FLCD\\SymbolTable\\src\\main\\resources\\p1.txt");

        //scanner.scan(str);

        FA fa = new FA("", new ArrayList<>(), new HashSet<>());

        fa.startScan();

        System.out.println("\n\n\n");

        boolean end = false;

        Scanner scan = new Scanner(System.in);

        while(!end){
            showMenu();

            int resp = scan.nextInt();

            switch (resp){
                case 0:
                    end = true;
                    break;
                case 1:
                    System.out.println(fa.getStates().toString());
                    break;
                case 2:
                    System.out.println(fa.getAlphabet());
                    break;
                case 3:
                    var states = fa.getStates();
                    for(var s : states){
                        System.out.println(s.getTransitions());
                    }
                    break;
                case 4:
                    try{
                        System.out.println("introduce sequence : ");
                        String sequence = scan.next();

                        boolean correct = fa.verifySequence(sequence);

                        if(correct){
                            System.out.println("\nSequence is correct !\n");
                        }else{
                            System.out.println("\nSequence is incorrect !\n");
                        }
                    }catch (Exception e){
                        System.out.println("Exception occured " + e.getMessage());
                    }

                    break;
            }
        }

        //System.out.println(fa.verifySequence("010"));

    }

    private static void showMenu()
    {
        System.out.println("\n0. exit\n1. Display set of states\n2. Display alphabet\n3. display transitions\n4. verify sequence");
    }
}
