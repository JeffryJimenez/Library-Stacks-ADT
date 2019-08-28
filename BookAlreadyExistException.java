import java.io.IOException;

public class BookAlreadyExistException extends IOException {

    /**
     * Constructor thrown if a Book object been added already exists in a given stack.
     */
    public BookAlreadyExistException(){}

    /**
     * Constructor thrown if a Book object been added already exists in a given stack.
     *
     * This constructor prints out a message.
     *
     * @param message Message to be Printed.
     */
    public BookAlreadyExistException(String message){
        super(message);
    }
}

class EmptyStackException extends IOException{

    /**
     * Constructor thrown if the stack is empty.
     */
    public EmptyStackException(){}

    /**
     * This Constructor thrown if the stack is empty.
     *
     * This constructor also prints out a message.
     *
     * @param message Message to be printed.
     */
    public EmptyStackException(String message){
        super(message);
    }

}

class BookIsNullException extends IOException{

    /**
     * Constructor thrown if a book being added has null elements in it.
     */
    public BookIsNullException(){}

    /**
     * This Constructor is thrown if a book being added has null elements in it.
     *
     * This Constructor also prints out a message.
     *
     * @param message Message to be printed.
     */
    public BookIsNullException(String message){
        super(message);
    }
}

