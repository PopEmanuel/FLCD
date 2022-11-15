package entity.FiniteAutomata;

public class Transition {
    private State parentState;
    private State childState;
    private Integer etiquette;

    public Transition() {
    }

    public Transition(State parentState, State childState, Integer etiquette) {
        this.parentState = parentState;
        this.childState = childState;
        this.etiquette = etiquette;
    }

    public State getParentState() {
        return parentState;
    }

    public void setParentState(State parentState) {
        this.parentState = parentState;
    }

    public State getChildState() {
        return childState;
    }

    public void setChildState(State childState) {
        this.childState = childState;
    }

    public Integer getEtiquette() {
        return etiquette;
    }

    public void setEtiquette(Integer etiquette) {
        this.etiquette = etiquette;
    }

    @Override
    public String toString() {
        return "Transition{" +
                "parentState=" + parentState.getSymbol() +
                ", childState=" + childState.getSymbol() +
                ", etiquette=" + etiquette +
                '}';
    }
}
