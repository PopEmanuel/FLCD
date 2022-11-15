package entity.FiniteAutomata;

import java.util.List;

public class State {
    private String symbol;
    private List<Transition> transitions;
    private boolean isFinal;
    private boolean visited;

    public State() {
    }

    public State(String symbol, List<Transition> transitions, boolean isFinal, boolean visited) {
        this.symbol = symbol;
        this.transitions = transitions;
        this.isFinal = isFinal;
        this.visited = visited;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public List<Transition> getTransitions() {
        return transitions;
    }

    public void setTransitions(List<Transition> transitions) {
        this.transitions = transitions;
    }

    public boolean isFinal() {
        return isFinal;
    }

    public void setFinal(boolean aFinal) {
        isFinal = aFinal;
    }

    @Override
    public String toString() {
        return "State{" +
                "symbol='" + symbol + '\'' +
                ", transitions=" + transitions +
                ", isFinal=" + isFinal +
                '}';
    }

    public boolean isVisited() {
        return visited;
    }

    public void setVisited(boolean visited) {
        this.visited = visited;
    }
}
