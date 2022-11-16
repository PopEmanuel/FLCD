package entity.FiniteAutomata;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class FA {
    private String initialState;
    private List<String> finalStates;
    private List<Transition> transitions;
    private List<State> states;
    private Set<Integer> alphabet;

    public FA() {
    }

    public FA(String initialState, List<State> states, Set<Integer> alphabet) {
        this.initialState = initialState;
        this.states = states;
        this.alphabet = alphabet;
        this.transitions = new ArrayList<>();
        this.finalStates = new ArrayList<>();
    }

    public void startScan() throws FileNotFoundException {
        File file = new File("C:\\school\\limbaje\\lab\\FLCD\\SymbolTable\\src\\main\\java\\entity\\FiniteAutomata\\FA.in");
        Scanner scanner = new Scanner(file);

        this.initialState = scanner.next();

        try {
            int statesNumber = scanner.nextInt();

            while (statesNumber > 0) {
                statesNumber--;
                String state = scanner.next();
                String finish = scanner.next();
                if (finish.equals("f")) {
                    this.states.add(new State(state, true, false));
                    this.finalStates.add(state);
                } else {
                    this.states.add(new State(state, false, false));
                }
            }

            int transitions = scanner.nextInt();

            while (transitions > 0) {
                transitions--;
                String state1 = scanner.next();
                int etiquette = scanner.nextInt();
                String state2 = scanner.next();
                var from = this.states.stream()
                        .filter(st -> st.getSymbol().equals(state1))
                        .findFirst();

                State fromState;
                if (from.isPresent()) {
                    fromState = from.get();
                } else {
                    throw new WrongInputException("Incorrect from state");
                }

                var to = this.states.stream()
                        .filter(st -> st.getSymbol().equals(state2))
                        .findFirst();

                State toState;
                if (to.isPresent()) {
                    toState = to.get();
                } else {
                    throw new WrongInputException("Incorrect to state");
                }

                this.transitions.add(new Transition(fromState, toState, etiquette));
                alphabet.add(etiquette);
            }
        } catch (Exception e) {
            throw new WrongInputException("Input in incorrect" + e.getMessage());
        }

        System.out.println(this.toString());
    }

    public boolean verifySequence(String sequence) {
        var array = sequence.toCharArray();

        var optionalInitial = this.states.stream().filter(s -> s.getSymbol().equals(this.initialState))
                .findFirst();
        State initial;

        if (optionalInitial.isPresent()) {
            initial = optionalInitial.get();
        } else {
            throw new WrongInputException("Input of initialState is incorrect");
        }

        initial.setVisited(true);
        for (char element : array) {
            System.out.println("I am on " + initial.getSymbol());
            Transition transition;
            Integer el = (int) element - 48;
            System.out.println("etiquette " + el);

            State finalInitial = initial;
            var optional = this.transitions.stream()
                    .filter(t -> t.getEtiquette().equals(el) && t.getParentState().getSymbol().equals(finalInitial.getSymbol()))
                    .findFirst();
            if (optional.isPresent()) {
                transition = optional.get();
            } else {
                throw new WrongInputException("Transition with this ettiquete not existent");
            }
            initial = transition.getChildState();
            initial.setVisited(true);
        }

        if (!initial.isFinal()) {
            return false;
        }

//        long count = this.states.stream().filter(State::isVisited).toList().size();
//        if (count != this.states.size()) {
//            return false;
//        }

        return true;
    }

    public String getInitialState() {
        return initialState;
    }

    public void setInitialState(String initialState) {
        this.initialState = initialState;
    }

    public List<State> getStates() {
        return states;
    }

    public void setStates(List<State> states) {
        this.states = states;
    }

    public Set<Integer> getAlphabet() {
        return alphabet;
    }

    public void setAlphabet(Set<Integer> alphabet) {
        this.alphabet = alphabet;
    }

    public List<Transition> getTransitions() {
        return transitions;
    }

    public void setTransitions(List<Transition> transitions) {
        this.transitions = transitions;
    }

    @Override
    public String toString() {
        return "FA{" +
                "initialState=" + initialState +
                ", states=" + states +
                '}';
    }
}
