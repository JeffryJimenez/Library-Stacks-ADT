import java.util.Scanner;
/**
 * Allows the User to interact with the Stack.
 */
public class LibraryManager {
    public static  void main(String[] args){

    BookStack stakingBook = new BookStack();
    String choice = "S";


    Scanner info = new Scanner(System.in);
    Scanner in = new Scanner(System.in);

    while(!choice.equals("Q")) {
        try {
            System.out.println("A. Add book \nR. Remove Book \nG. Get Book \nP. Print Books \nS. Sort Books \nQ. Quit");
            System.out.print("Please select an option: ");

            choice = info.nextLine();


            switch (choice) {
                case "Q":
                    System.out.println("FETUS DELETUS! -Harry Potter");
                    break;
                case "S":
                    System.out.println("Please select by what: ");
                    System.out.println("N. Name \nA. Author \nG. Genre \nY. Year \nC. condition \nI. ISBN Number");
                    System.out.print("Please select an option: ");
                    String sortingChoice = info.nextLine();
                    switch (sortingChoice) {                                               //different types of sorting
                        case "C":
                            stakingBook.setChoice(sortingChoice);
                            stakingBook.sort();
                            System.out.println("The stack has been sorted by Condition.");
                            break;
                        case "I":
                            stakingBook.setChoice(sortingChoice);
                            stakingBook.sort();
                            System.out.println("The stack has been sorted by ISBN Number.");
                            break;
                        case "Y":
                            stakingBook.setChoice(sortingChoice);
                            stakingBook.sort();
                            System.out.println("The stack has been sorted by year.");
                            break;
                        case "G":
                            stakingBook.setChoice(sortingChoice);
                            stakingBook.sort();
                            System.out.println("The stack has been sorted by genre.");
                            break;
                        case "A":
                            stakingBook.setChoice(sortingChoice);
                            stakingBook.sort();
                            System.out.println("The stack has been sorted by author.");
                            break;

                        case "N":
                            stakingBook.setChoice(sortingChoice);
                            stakingBook.sort();
                            System.out.println("The stack has been sorted by name.");
                            break;
                    }

                    break;
                case "P":
                    BookStack.print(stakingBook);                                     //print the books in table form.
                    break;

                case "G":
                    System.out.print("Please enter the name of the book: ");
                    String nameG = info.nextLine();                                   //name of the book.

                    int indexG = 0;
                    Book[] holderG = new Book[stakingBook.getManyItems()];
                    int manyItemsG = stakingBook.getManyItems();

                    for (int i = 0; i < manyItemsG; i++) {

                        if (stakingBook.peek().getName().equals(nameG)) {             //Checks if book at the top
                            System.out.println(stakingBook.peek().toString());        //matches the given information.
                            break;
                        } else {
                            holderG[i] = stakingBook.pop();                          //places all the books removed
                            indexG++;                                                //into a temporary array.
                        }
                    }

                    for (int i = indexG - 1; i >= 0; i--)                            //places all the books back in the stack.
                        stakingBook.push(holderG[i]);
                    break;

                case "R":
                    System.out.print("Please enter the name of the book: ");
                    String nameR = info.nextLine();                                  //Name of the book being removed.
                    int index = 0;
                    Book[] holder = new Book[stakingBook.getManyItems()];
                    int manyItems = stakingBook.getManyItems();

                    if(stakingBook.getManyItems() == 0)                              //checks if the stack is empty.
                        throw new EmptyStackException();

                    for (int i = 0; i < manyItems; i++) {

                        if (stakingBook.peek().getName().equals(nameR)) {            //Looks for a book object with
                            stakingBook.pop();                                       //the given information
                            System.out.println(nameR + " has been removed!");
                            break;
                        } else {
                            holder[i] = stakingBook.pop();                           //All the Books poped are put into
                            index++;                                                 //a temporary array.
                        }
                    }

                    for (int i = index - 1; i >= 0; i--)                            //The Books are put back into
                        stakingBook.push(holder[i]);                                //the stack.

                    break;

                case "A":

                    System.out.print("Please enter the name: ");
                    String name = info.nextLine();

                    System.out.print("Please enter the author: ");
                    String author = info.nextLine();

                    System.out.print("Enter genre: ");
                    String genre = info.nextLine();

                    System.out.print("Please enter the year it was published: ");
                    String yearS = info.nextLine();
                    int year = Integer.parseInt(yearS);                                //chances year string to integer.

                    System.out.print("Please enter ISBN number: ");
                    String ISBNS = info.nextLine();
                    long ISBN = Long.parseLong(ISBNS);                                //chances ISBN string to long.

                    System.out.print("enter the condition: ");
                    String condition = info.nextLine();

                    Book newBook = new Book(ISBN, year, name, author, genre, condition);       //Creates a book object.

                    stakingBook.push(newBook);

                    System.out.println(name + " has been added!");
                    break;
            }
        }catch (BookAlreadyExistException e){
            System.out.println("This Book Already Exists!");
        }

        catch (EmptyStackException e){
            System.out.println("Sorry but the Stack is Empty");
        }

        catch (BookIsNullException e){
            System.out.println("This Book has missing information sorry :(");
        }

        catch (java.lang.NumberFormatException e){
            System.out.println("YEAR OR ISBN NUMBER WAS INCORRECT! THEY SHOULD BE NUMBERS");
        }
    }

}
}
