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

    public Boolean isPresent(String value){
        return table.isPresent(value);
    }

    public String toString()
    {
        return table.toString();
    }

}
