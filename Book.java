/**
 * Necessary methods to manipulate books, and store information.
 */
public class Book {
    private long ISBN;
    private int yearPublished;
    private String name;
    private  String author;
    private String genre;
    private enum condition {OLD, NEW}
    private condition conditionVariable;

    /**
     * Book class constructor, creates a book object with the following parameters.
     *
     * @param ISBN The ISBN Number of the Book.
     * @param yearPublished The year the book was published.
     * @param name The Title of the Book.
     * @param author The name of the author of the Book.
     * @param genre The Genre of the book.
     * @param condicion The condition of the book (Old, New).
     */
    public Book(long ISBN, int yearPublished, String name, String author, String genre, String condicion){
        this.ISBN = ISBN;
        this.yearPublished = yearPublished;
        this.name = name;
        this.author = author;
        this.genre = genre;
        if(condicion.equalsIgnoreCase("New"))                                    //If condicion is "New" then condition is NEW,
            this.conditionVariable = condition.NEW;                                          //OLD if condicion is "Old".
        else if(condicion.equalsIgnoreCase("Old"))
            this.conditionVariable = condition.OLD;
    }

    /**
     * Gets the ISBN number of a Given Book.
     * @return ISBN Number.
     */
    public long getISBN() {
        return ISBN;
    }

    /**
     * Gets the Year a Given Book was published.
     * @return The Year the Book was published.
     */
    public int getYearPublished() {
        return yearPublished;
    }

    /**
     * Gets the condition of the book from enum condition.
     * @return Returns the condition as a String.
     */
    public String getCondition() {
        if(conditionVariable == condition.NEW)
            return "New";
        return "Old";
    }

    public void setConditionVariable(condition conditionVariable) {
        this.conditionVariable = conditionVariable;
    }

    /**
     * Gets the Title of a given Book.
     * @return Title of the book.
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the name of the Author of a given Book.
     * @return The name of the Author.
     */
    public String getAuthor() {
        return author;
    }

    /**
     * Gets the Genre of Given Book.
     * @return The Genre of the Book.
     */
    public String getGenre() {
        return genre;
    }

    /**
     * Sets the ISBN Number.
     *
     * @param ISBN The ISBN Number of a given book.
     */
    public void setISBN(long ISBN) {
        this.ISBN = ISBN;
    }

    /**
     * Sets the year a given Book was published.
     *
     * @param yearPublished The year the Book was published.
     */
    public void setYearPublished(int yearPublished) {
        this.yearPublished = yearPublished;
    }

    /**
     * Sets the Tittle of a given book.
     *
     * @param name The tittle of the book.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Sets the name of the author of a given Book.
     *
     * @param author The author of the Book.
     */
    public void setAuthor(String author) {
        this.author = author;
    }

    /**
     * Set the genre of a given book object.
     *
     * @param genre The genre of the book object.
     */
    public void setGenre(String genre) {
        this.genre = genre;
    }

    /**
     * Overrides the toString method to return a description of a given Book object.
     *
     * @return String of data members in tubular form.
     */
    public String toString(){

        return name + " was written by " + author + " in the year " + yearPublished + ". It is of the " + genre +" genre." +
                " The ISBN Number is " + ISBN + " and it is " + getCondition();

    }
}


