import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class main {
    //create variables
    public static List<String> todo = new ArrayList<String>();

    public static void callAnEntry(){
        //update the amount of entries
        int noEntries = todo.size();

        //check if there are actually any entries first
        if (noEntries == 0){
            System.out.println("There are currently no entries.");
        }

        //ask which entry to recall
        System.out.println("Which number entry would you like to recall?");
        System.out.println("There are currently " + noEntries + " Entries.");
        Scanner callEntryObj = new Scanner(System.in);
        String callEntry = callEntryObj.nextLine();

        //convert from a string to an INT
        //will ask you to try again if the thing you put in isn't an int
        try{
            int callEntryInt  = Integer.parseInt(callEntry);
            callEntryInt = callEntryInt - 1;
        }
        catch(Exception e){
            System.out.println("Something went wrong. Please try again.");
            callAnEntry();
        }

        int callEntryInt  = Integer.parseInt(callEntry);
        callEntryInt = callEntryInt - 1;

        try{
            System.out.println(todo.get(callEntryInt));
        }
        catch(Exception e){
            System.out.println("Something went wrong. Please try again.");
            callAnEntry();
        }

        //always end with an actionmenu, so that it always goes back to the main menu.
        //otherwise the program would quit, and we don't want that
        actionMenu();


    }

    public static void makeNewEntry(){
        //this method makes a new entry in the arraylist
        System.out.println("What would you like to add?");
        Scanner toAdd = new Scanner(System.in);
        String thingToAdd = toAdd.nextLine();

        todo.add(thingToAdd);

        actionMenu();
    }

    public static void callAllEntries(){
        //this method will list all entries
        int noEntries = todo.size();

        if (noEntries == 0){
            System.out.println("There are currently no entries.");
            actionMenu();
        }

        for (int i = 0; i < noEntries; i++){
            int b = i+1;
            System.out.println("[ENTRY NO."+b+"] "+todo.get(i));
        }

        actionMenu();
    }

    public static void deleteEntry(){
        //just deletes an entry
        //you can also cancel the deletion
        System.out.println("Which entry would you like to delete? Type the number.");
        System.out.println("Type in \"CANCEL\" if you would like to cancel the operation.");
        Scanner toDelete = new Scanner(System.in);
        String numToDelete = toDelete.nextLine();

        //to cancel operation
        if (numToDelete.equalsIgnoreCase("CANCEL")){
            System.out.println("Ok. Cancelling.");
            actionMenu();
        }

        try{
            int intNumToDelete = Integer.parseInt(numToDelete);
        }
        catch (Exception e){
            System.out.println("You did not enter an integer. Please try again.");
            deleteEntry();
        }

        int intNumToDelete = Integer.parseInt(numToDelete)-1;

        todo.remove(intNumToDelete);

        actionMenu();
    }

    public static void deleteAllEntries(){
        //literally just deletes all entries, with a confirmation
        int noEntries = todo.size();
        System.out.println("Are you SURE you want to delete all entries? (Y/N)");
        System.out.println("This cannot be undone!");
        Scanner isUserSure = new Scanner(System.in);
        String isUserSureString = isUserSure.nextLine();

        if (isUserSureString.equalsIgnoreCase("y")){
            System.out.println("Ok. Deleting all entries.");
            todo.clear();
            if (noEntries == 0){
                System.out.println("However, there weren't any entries in the first place.");
                System.out.println("Just thought i'd let you know.");
            }
            actionMenu();
        }

        else if (isUserSureString.equalsIgnoreCase("n")){
            System.out.println("Ok. No entries were deleted.");
            actionMenu();
        }

        else{
            System.out.println("That's not a valid answer. Please try again.");
            deleteAllEntries();
        }
    }

    public static void quitProgram(){
        System.out.println("Are you sure you want to quit? (Y/N)");
        Scanner quitConfScanner = new Scanner(System.in);
        String quitConf = quitConfScanner.nextLine();

        if (quitConf.equalsIgnoreCase("y")){
            System.out.println("Ok. The program will close in 3 seconds.");
            try{
                Thread.sleep(3000);
            }
            catch (Exception e){
                System.out.println("Something went wrong. Returning to main menu.");
                actionMenu();
            }

            System.out.println("Exiting program with status 0.");
            System.exit(0);
        }
        if (quitConf.equalsIgnoreCase("n")){
            System.out.println("Ok. The program will not terminate.");
            actionMenu();
        }
        else{
            System.out.println("That input was invalid. Please try again.");
            quitProgram();
        }

    }

    public static void actionMenu() {
        //this is the main menu where all the other methods will be called
        //the asterisks are a separator so the output looks cleaner
        System.out.println("***");
        System.out.println("What would you like to do now? Type the number next to the action.");
        System.out.println("1) Call an entry");
        System.out.println("2) Create a new entry");
        System.out.println("3) Call all entries");
        System.out.println("4) Delete an entry");
        System.out.println("5) Delete all entries");
        System.out.println("6) Exit the program");
        Scanner userInput = new Scanner(System.in);
        String TheInput = userInput.nextLine();

        //prevents program from dying if the input is bad
        int TheInputInt = 0;
        try {
            TheInputInt = Integer.parseInt(TheInput);
        } catch (Exception e) {
            System.out.println("That input was invalid and something went wrong. Please try again.");
            actionMenu();
        }

        //check for what the user has put
        //i could have used if statements but i wanted to be cool
        switch (TheInputInt){
            case 1:
                callAnEntry();
            case 2:
                makeNewEntry();
            case 3:
                callAllEntries();
            case 4:
                deleteEntry();
            case 5:
                deleteAllEntries();
            case 6:
                quitProgram();
            default:
                System.out.println("That's not a valid answer. Please try again.");
                actionMenu();
        }

    }

    //starts the program basically
    public static void main(String[] args){
        actionMenu();
    }
}
