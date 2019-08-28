import javax.swing.plaf.synth.SynthStyleFactory;

/**
 * Creates the book objects and stores them in a stack, also manipulates the stack.
 */
public class BookStack {

    private Book[] book;
    private int manyItems;
    private String choice;                                                          //Only used for sorting.
    private int minimumCapacity;


    private Book[] getBook() {
        return book;
    }

    /**
     * Gets the amount of Books in the stack.
     *
     * @return The number of Books in a Stack.
     */
    public int getManyItems() {
        return manyItems;
    }

    /**
     * Sets the String instance variable choice equals to a given to the provided String choice.
     *
     * @param choice Any of the following: (N),(A),(G),(Y),(C),(I).
     */
    public void setChoice(String choice) {
        this.choice = choice;
    }

    /**
     * BookStack constructor with creates an Array/Stack with a Size of 10.
     *
     * This Constructor creates a Book stack with a maximum size of 10, and
     * sets manyItems to 0.
     */
    public BookStack(){                                                                  //Constructor
        manyItems = 0;
        book = new Book[10];
        minimumCapacity = 10;
    }

    /**
     * BookStack constructor with creates an Array/Stack with a Size of provided by the user.
     *
     * This Constructor creates a stack with a maximum size provided by the User, it sets
     * manyItems to 0, meaning that they are no book in the stack.
     *
     * @param inititalCapacity The Size of the Stack/Array.
     */
    public BookStack(int inititalCapacity){                                               //Constructor
        manyItems = 0;
        book = new Book[inititalCapacity];
        minimumCapacity = inititalCapacity;
    }

    /**
     * Adds a book object into the Stack.
     *
     * This method adds a book object to the top of the stack, book objects can only be
     * added to the top of the stack. The book add cannot be equals to null.
     *
     * @param newBook Book object to be added.
     * @throws BookAlreadyExistException thrown if Book with the same details as newBook exists in the stack.
     * @throws BookIsNullException thrown if Book is equal to null.
     */
    public void push(Book newBook) throws BookAlreadyExistException, BookIsNullException{

        if(newBook == null)                                                             //Precondition
            throw new BookIsNullException();

        if(checkDuplicates(newBook) == true)                                            //If book with the same
            throw new BookAlreadyExistException();                                      //information exist throw exception

        if(manyItems == book.length)                                                    //if the stack reaches its capacity
            ensureCapacity(manyItems*2+1);

        int sizeCheck = manyItems;                                                      //Size check makes sure an element
        //was added
        book[manyItems] = newBook;
        manyItems++;

        assert sizeCheck == manyItems - 1;                                              //Post condition.
    }

    /**
     * Removes the top Book object in the stack.
     *
     * This method can only removes objects from the top of the stack, any object
     * bellow the top is inaccessible until it becomes the top.
     *
     * @return The Book object that was removed.
     * @throws EmptyStackException thrown if the stack is empty.
     */
    public Book pop() throws EmptyStackException{

        if(manyItems == 0)                                                               //Checks is there is any items
            throw new EmptyStackException();                                              //to pop.


        int sizeCheck = manyItems;

        Book bookHolder =  new Book(book[manyItems - 1].getISBN(),book[manyItems - 1].getYearPublished(),
                book[manyItems - 1].getName(), book[manyItems - 1].getAuthor(), book[manyItems - 1].getGenre(), book[manyItems - 1].getCondition());

        book[manyItems - 1] = null;                                                       //Removes the Object.
        manyItems--;

        assert sizeCheck - 1 == manyItems;                                                //Makes sure an Item was removed.

        return bookHolder;
    }

    /**
     * Looks at the book at the top of the stack without removing it.
     *
     * This method can only return the information of the object at the top of the stack
     * any other object bellow it is inaccessible until such object becomes the top. In
     * addition the method makes sure no item was removed from the stack.
     *
     * @return The Book Object at the top of the stack.
     * @throws EmptyStackException thrown is the stack is empty.
     */
    public Book peek() throws EmptyStackException{

        int sizeCheck = manyItems;                                                      //Keeps tracks of the amount of objects.

        if(manyItems == 0)                                                              //Checks is there is any items
            throw new EmptyStackException();                                             //to pop.

        assert sizeCheck == manyItems;                                                  //Post condition makes sure
        //no items has been removed.
        return book[manyItems - 1];
    }

    /**
     * Checks if the stack is empty.
     *
     * This method check is the first element in the array is null, which means
     * the stack is empty.
     *
     * @return true if the stack is empty, false if is not.
     */
    public boolean isEmpty(){

        if(book[manyItems -1] == null)                                                  //Checks if the Array contains
            return true;                                                                //An object.

        else return false;
    }

    /**
     * Looks for the amount of Books objects in the stack at a Given time.
     *
     * @return The number of Books objects in the Stack.
     */
    public int size(){
        return manyItems;
    }


    /**
     * Sorts the books in the stack by one of the given option.
     *
     * The options for sorting are: Name, Author, Genre, Year, Condition, or ISBN Number.
     */
    public void sort(){

        switch (choice){                                                                  //Select in what order to sort
            //the books.

            case "C":                                                                     //Condition
                for(int i = 0; i < manyItems; i++)                                        //Bubble Sort.
                    for(int j = 0; j < manyItems - 1 - i; j++){
                        if(book[j].getCondition().charAt(0) > book[j+1].getCondition().charAt(0)){
                            Book temp = new Book(book[j].getISBN(),book[j].getYearPublished(),      //Temporary Book Object
                                    book[j].getName(),book[j].getAuthor(), book[j].getGenre()
                                    , book[j].getCondition());

                            book[j] = book[j + 1];
                            book[j+1] = temp;
                        }
                    }
                break;


            case "G":                                                                              //Sort by Genre.


                for(int i = 0; i < manyItems; i++)                                                 //Bubble Sort.
                    for(int j = 0; j < manyItems - 1 - i; j++){
                        if(book[j].getGenre().charAt(0) > book[j+1].getGenre().charAt(0)){         //Determine position of books base on their first Letter.
                            Book temp = new Book(book[j].getISBN(),book[j].getYearPublished(),     //Temporary Book Object.
                                    book[j].getName(),book[j].getAuthor(), book[j].getGenre(),
                                    book[j].getCondition());


                            book[j] = book[j + 1];
                            book[j+1] = temp;
                        }
                        if(book[j].getGenre().charAt(0) == book[j+1].getGenre().charAt(0)) {     //If the books start with the same
                            //letter, check the other ones.
                            if (book[j].getGenre().length() < book[j+1].getGenre().length()) {
                                for (int k = 1; k < book[j].getGenre().length(); k++) {
                                    if (book[j].getGenre().charAt(k) > book[j + 1].getGenre().charAt(k)) {          //Which word to use for the size of the loop.
                                        Book temp = new Book(book[j].getISBN(), book[j].getYearPublished(), book[j].getName(), book[j].getAuthor(), book[j].getGenre(), book[j].getCondition());
                                        book[j] = book[j + 1];
                                        book[j + 1] = temp;
                                        break;
                                    }
                                    if (book[j].getGenre().charAt(k) != book[j + 1].getGenre().charAt(k))
                                        break;

                                }
                            }
                            else if(book[j].getGenre().length() > book[j+1].getGenre().length() || book[j].getGenre().length() == book[j+1].getGenre().length()){
                                for (int k1 = 1; k1 < book[j+1].getGenre().length(); k1++) {
                                    if (book[j].getGenre().charAt(k1) > book[j + 1].getGenre().charAt(k1)) {
                                        Book temp = new Book(book[j].getISBN(), book[j].getYearPublished(), book[j].getName(), book[j].getAuthor(), book[j].getGenre(), book[j].getCondition());
                                        book[j] = book[j + 1];
                                        book[j + 1] = temp;
                                        break;

                                    }
                                    if (book[j].getGenre().charAt(k1) != book[j + 1].getGenre().charAt(k1))
                                        break;

                                }
                            }



                        }
                    }
                break;

            case  "A":                                                                         //Sort by Author name.
                for(int i = 0; i < manyItems; i++)                                             //Bubble Sort.
                    for(int j = 0; j < manyItems - 1 - i; j++){
                        if(book[j].getAuthor().charAt(0) > book[j+1].getAuthor().charAt(0)){    //Determine position of books base on their first Letter.
                            Book temp = new Book(book[j].getISBN(),book[j].getYearPublished(),  //Temporary Book object.
                                    book[j].getName(),book[j].getAuthor(), book[j].getGenre(),
                                    book[j].getCondition());
                            book[j] = book[j + 1];
                            book[j+1] = temp;
                        }

                        if(book[j].getAuthor().charAt(0) == book[j+1].getAuthor().charAt(0)) {    //If the books start with the same
                            //letter, check the other ones.

                            if (book[j].getAuthor().length() < book[j + 1].getAuthor().length()) {
                                for (int k = 1; k < book[j].getAuthor().length(); k++) {           //Which word to use for the size of the loop.
                                    if (book[j].getAuthor().charAt(k) > book[j + 1].getAuthor().charAt(k)) {
                                        Book temp = new Book(book[j].getISBN(), book[j].getYearPublished(), book[j].getName(), book[j].getAuthor(), book[j].getGenre(), book[j].getCondition());
                                        book[j] = book[j + 1];
                                        book[j + 1] = temp;
                                        break;
                                    }
                                    if (book[j].getAuthor().charAt(k) != book[j + 1].getAuthor().charAt(k))
                                        break;
                                }
                            }

                            else if(book[j].getAuthor().length() > book[j + 1].getAuthor().length() || book[j].getAuthor().length() == book[j + 1].getAuthor().length()){
                                for (int k1 = 1; k1 < book[j+1].getAuthor().length(); k1++) {
                                    if (book[j].getAuthor().charAt(k1) > book[j + 1].getAuthor().charAt(k1)) {
                                        Book temp = new Book(book[j].getISBN(), book[j].getYearPublished(), book[j].getName(), book[j].getAuthor(), book[j].getGenre(), book[j].getCondition());
                                        book[j] = book[j + 1];
                                        book[j + 1] = temp;
                                        break;
                                    }

                                    if (book[j].getAuthor().charAt(k1) != book[j + 1].getAuthor().charAt(k1))
                                        break;

                                }


                            }



                        }



                    }
                break;

            case "Y":                                                                         //Sort by year.
                for(int i = 0; i < manyItems; i++)                                            //Bubble Sort.
                    for(int j = 0; j < manyItems - 1 - i; j++){

                        if(book[j].getYearPublished() > book[j+1].getYearPublished()){       //Determine position of books base on the year published.
                            Book temp = new Book(book[j].getISBN(),book[j].getYearPublished(),     //Temporary Book.
                                    book[j].getName(),book[j].getAuthor(), book[j].getGenre(),
                                    book[j].getCondition());
                            book[j] = book[j + 1];
                            book[j+1] = temp;
                        }
                    }
                break;

            case "I":                                                                         //Sort by ISBN Number.
                for(int i = 0; i < manyItems; i++)                                            //Bubble Sort.
                    for(int j = 0; j < manyItems - 1 - i; j++){


                        if(book[j].getISBN() > book[j+1].getISBN()){                          //Determine Position of Books based on ISBN Number.
                            Book temp = new Book(book[j].getISBN(),book[j].getYearPublished(),     //Temporary Book.
                                    book[j].getName(),book[j].getAuthor(), book[j].getGenre(),
                                    book[j].getCondition());
                            book[j] = book[j + 1];
                            book[j+1] = temp;

                        }
                    }
                break;

            case "N":                                                                          //Sort by tittle of the book.
                for(int i = 0; i < manyItems; i++)                                             //Bubble sort.

                    for(int j = 0; j < manyItems - 1 - i; j++){
                        if(book[j].getName().charAt(0) > book[j+1].getName().charAt(0)){       //Determine position of books base on their first Letter.
                            Book temp = new Book(book[j].getISBN(),book[j].getYearPublished(),     //Temporary Book.
                                    book[j].getName(),book[j].getAuthor(), book[j].getGenre(),
                                    book[j].getCondition());
                            book[j] = book[j + 1];
                            book[j+1] = temp;
                        }
                        if(book[j].getName().charAt(0) == book[j+1].getName().charAt(0)) {       //If the books start with the same
                            //letter, check the other ones.

                            if (book[j].getName().length() < book[j + 1].getName().length()) {    //Which word to use for the size of the loop.
                                for (int k = 1; k < book[j].getName().length(); k++) {
                                    if (book[j].getName().charAt(k) > book[j + 1].getName().charAt(k)) {
                                        Book temp = new Book(book[j].getISBN(), book[j].getYearPublished(), book[j].getName(), book[j].getAuthor(), book[j].getGenre(), book[j].getCondition());
                                        book[j] = book[j + 1];
                                        book[j + 1] = temp;
                                        break;
                                    }
                                    if (book[j].getName().charAt(k) != book[j + 1].getName().charAt(k))
                                        break;
                                }
                            }

                            else if(book[j].getName().length() > book[j + 1].getName().length() || book[j].getName().length() == book[j + 1].getName().length()) {
                                for (int k1 = 1; k1 < book[j + 1].getName().length(); k1++) {
                                    if (book[j].getName().charAt(k1) > book[j + 1].getName().charAt(k1)) {
                                        Book temp = new Book(book[j].getISBN(), book[j].getYearPublished(), book[j].getName(), book[j].getAuthor(), book[j].getGenre(), book[j].getCondition());
                                        book[j] = book[j + 1];
                                        book[j + 1] = temp;
                                        break;
                                    }

                                    if (book[j].getName().charAt(k1) != book[j + 1].getName().charAt(k1))
                                        break;

                                }

                            }

                        }
                    }

                break;

        }

    }


    /**
     * Prints the data form a stack in a table format.
     *
     * @param stack The stack being printed.
     */
    public static void print(BookStack stack) throws BookAlreadyExistException, EmptyStackException, BookIsNullException{

        String format = "|%1$-30s|%2$-30s|%3$-30s|%4$-30s|%5$-30s|%6$-30s|\n";           //Format for the Tittle
        String formatData = "|%1$-30s|%2$-30s|%3$-30s|%4$-30s|%5$-30s|%6$-30s|\n";       //Format for the Data.

        boolean title = false;
        while (!title) {                                                                 //When to print the tittle.
            System.out.format(format,"Name","Author", "Genre","Year","ISBN","Condition");
            System.out.println("==============================================================" +
                    "============================================================================" +
                    "=================================================");
            title = true;
        }
        Book[] holder = new Book[stack.getManyItems()];                                   //A book Array to hold Book Objects

        for(int i = stack.getManyItems() - 1; i >= 0; i--){                                       //Sends all the object the
            holder[i] = new Book(stack.peek().getISBN(), stack.peek().getYearPublished(),         //to the book Array, and
                    stack.peek().getName(), stack.peek().getAuthor(),stack.peek().getGenre(),     //then pops the object
                    stack.peek().getCondition());                                                 //from the stack
            stack.pop();
        }

        for(int i = 0; i < holder.length; i++){
            System.out.format(formatData,holder[i].getName(), holder[i].getAuthor(),holder[i].getGenre()   //Prints the books
                    ,holder[i].getYearPublished(),holder[i].getISBN(),holder[i].getCondition());           //in the Array.

            Book ref = new Book(holder[i].getISBN(),holder[i].getYearPublished(),                        //Temporary reference
                    holder[i].getName(),holder[i].getAuthor(),holder[i].getGenre(),
                    holder[i].getCondition());
            stack.push(ref);                                                                             //Pushes the reference
        }                                                                                                //to the stack.


    }


    /**
     * Check is their is book with the same data in the stack.
     *
     * @param stack Book containing a set of data.
     * @return true, if book exist in the stack, false if not.
     */
    public Boolean checkDuplicates(Book stack){
        for(int i = 0; i < manyItems; i++)
            if(book[i].getName().equals(stack.getName()) && book[i].getGenre().equals(stack.getGenre())
                    && book[i].getAuthor().equals(stack.getAuthor()) && book[i].getISBN() == stack.getISBN()
                    && book[i].getYearPublished() == stack.getYearPublished() && book[i].getCondition().equals(stack.getCondition()))
                return true;

        return false;


    }

    /**
     * Creates a new stack for the books with a higher capacity.
     *
     * @param minimumCapacity the minimum capacity for the newly created stack.
     */
    public void ensureCapacity(int minimumCapacity){

        Book[]biggerStack;

        if(book.length < minimumCapacity){
            biggerStack = new Book[minimumCapacity];

            for (int i = 0; i < manyItems; i++) {
                biggerStack[i] = new Book(book[i].getISBN(), book[i].getYearPublished(),book[i].getName(),book[i].getAuthor()
                        ,book[i].getGenre(),book[i].getCondition());

            }
            book = biggerStack;
        }
    }


}
