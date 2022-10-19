package entity;

public class SymbolTable {
    private final HashTable table;

    public SymbolTable(){
        table = new HashTable();
    }

    public hashResponse add(String value)
    {
        return table.add(value);
    }

    public hashResponse get(String value)
    {
        return table.get(value);
    }

}
