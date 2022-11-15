package entity.FiniteAutomata;

public class WrongInputException extends RuntimeException{
    public WrongInputException(String message) {
        super(message);
    }
}
