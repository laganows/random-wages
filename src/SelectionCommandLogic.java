import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;

public class SelectionCommandLogic {
    private RandomCollection randomCollection;
    private int changeWage;

    public SelectionCommandLogic(RandomCollection randomCollection, int changeWage) {
        this.randomCollection = randomCollection;
        this.changeWage = changeWage;
    }

    void selectRandomizeAndCheckEquality(Scanner scanner) {
        if (randomCollection.getWagesAndEncies().size() == 0) {
            System.out.println("No elements to list, please first add new number");
            return;
        }
        System.out.println("Please pass index of record you want to select and compare with random selected one:");
        listEnciesWithProbabilities();

        int index;
        do {
            index = scanner.nextInt();
        } while (index <= 0 || index > randomCollection.getWagesAndEncies().size());

        Ency selectedEncyByUser = (Ency) randomCollection.getWagesAndEncies().values().toArray()[index - 1];
        Ency randomEncy = randomCollection.next();

        if (selectedEncyByUser.equals(randomEncy)) {
            randomCollection.updateWage(selectedEncyByUser, changeWage);
        } else {
            randomCollection.updateWage(selectedEncyByUser, -1 * changeWage);
        }
    }

    void addEncyToCollection(Scanner scanner) {
        System.out.println("Please pass wage:");
        Double wage;
        do {
            wage = scanner.nextDouble();
            if (wage <= 0) {
                System.out.println("Please pass wage bigger than 0");
            }
        } while (wage <= 0);
        System.out.println("Please pass number:");
        Double number = scanner.nextDouble();
        randomCollection.add(wage, new Ency<>(number, wage));
    }

    void showEncies() {
        if (randomCollection.getWagesAndEncies().size() == 0) {
            System.out.println("No elements to list, please first add new number");
            return;
        }
        final AtomicInteger count = new AtomicInteger(0);
        randomCollection.getWagesAndEncies().forEach((wage, ency) -> System.out.println(count.incrementAndGet() + ".Wage: " + ency.getWage() + ", Value: " + ency.getValue()));
    }

    void listEnciesWithProbabilities() {
        if (randomCollection.getWagesAndEncies().size() == 0) {
            System.out.println("No elements to list, please first add new number");
            return;
        }
        final AtomicInteger count = new AtomicInteger(0);
        randomCollection.getWagesAndEncies().forEach((wage, ency) ->
                System.out.println(count.incrementAndGet() + ". Wage: " + ency.getWage() + ", Value: " + ency.getValue() +
                        ", Probability: " + (1 / ency.getWage()) / randomCollection.getTotalWagesInverseSum() * 100));
    }

    void showHelp() {
        System.out.println("SelectionCommandLogic:");
        System.out.println("\t1 - add new number with wage");
        System.out.println("\t2 - show numbers and theirs wages");
        System.out.println("\t3 - show numbers, theirs wages and probabilities to get");
        System.out.println("\t4 - selection and compare with random number process");
        System.out.println("\t5 - exit the program");
    }

}
