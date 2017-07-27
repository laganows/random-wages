import java.util.Locale;
import java.util.Scanner;

public class Main {

    private static final int CHANGE_WAGE = 5;
    private static SelectionCommandLogic selectionCommandLogic;

    public static void main(String[] args) {
        selectionCommandLogic = new SelectionCommandLogic(new RandomCollection(), CHANGE_WAGE);
        startUserInputLoop();
    }

    private static void startUserInputLoop() {
        Scanner scanner = new Scanner(System.in).useLocale(Locale.US);
        selectionCommandLogic.showHelp();
        String command;
        do {
            command = scanner.next().trim();
            if ("1".equals(command)) {
                selectionCommandLogic.addEncyToCollection(scanner);
            } else if ("2".equals(command)) {
                selectionCommandLogic.showEncies();
            } else if ("3".equals(command)) {
                selectionCommandLogic.listEnciesWithProbabilities();
            } else if ("4".equals(command)) {
                selectionCommandLogic.selectRandomizeAndCheckEquality(scanner);
            } else if (!"5".equals(command)) {
                selectionCommandLogic.showHelp();
            }
        } while (!"5".equals(command));
    }


}
