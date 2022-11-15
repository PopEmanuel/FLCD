package entity.PIF;

import java.util.ArrayList;
import java.util.List;

public class PIF {
    List<pifEntity> pif;

    public PIF() {
        pif = new ArrayList<>();
    }

    public void add(String key, Integer value) {
        pif.add(new pifEntity(key, value));
    }

    public List<pifEntity> getPif() {
        return pif;
    }

    public void setPif(List<pifEntity> pif) {
        this.pif = pif;
    }

    public String toString()
    {
        StringBuilder builder = new StringBuilder("");

        pif.stream().filter(p -> !p.getKey().contains(" "))
                        .forEach(p -> builder.append(p.getKey())
                                .append(" : ")
                                .append(p.getValue())
                                .append("\n"));
        return builder.toString();
    }
}
