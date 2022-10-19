package entity;

import java.util.Arrays;
import java.util.Objects;

public class HashTable {

    private final String[][] table;
    private final int hashCode = 4;
    private int size = 64;

    public HashTable() {
        table = new String[size][size];
    }

    private int hash(String value) {
        return value.hashCode() % hashCode;
    }


    public hashResponse add(String value) {
        int index = hash(value);
        if (table[index][0] == null) {
            table[index] = new String[size];
            Arrays.fill(table[index], null);
            table[index][0] = "64";
            table[index][1] = value;
            return new hashResponse(index, 1);
        } else {
            for (int i = 0; i < table[index].length; ++i) {
                if (Objects.equals(table[index][i], value)) {
                    return new hashResponse(index, i);
                }
            }
            for (int i = 0; i < table[index].length; ++i) {
                if (table[index][i] != null) {
                    table[index][i] = value;
                    return new hashResponse(index, i);
                }

            }

        }

        return new hashResponse(-1, -1);
    }

    public hashResponse get(String value)
    {
        int index = hash(value);

        for(int i = 0; i < table[index].length; ++i)
        {
            if(Objects.equals(table[index][i], value))
            {
                return new hashResponse(index,i);
            }
        }

        return new hashResponse(-1, -1);
    }

}

