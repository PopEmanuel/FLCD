import entity.MyScanner;

public class Main {

    public static void main(String[] args)
    {
        MyScanner scanner = new MyScanner();

        var response = scanner.getIdentifier();
        System.out.println(response.getKey() + "|" + response.getValue());
        response = scanner.getIdentifier();
        System.out.println(response.getKey() + "|" + response.getValue());
        response = scanner.getIdentifier();
        System.out.println(response.getKey() + "|" + response.getValue());
        response = scanner.getIdentifier();
        System.out.println(response.getKey() + "|" + response.getValue());

    }
}
