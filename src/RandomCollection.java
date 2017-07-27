import java.util.NavigableMap;
import java.util.Random;
import java.util.TreeMap;

public class RandomCollection {
    private NavigableMap<Double, Ency> wagesAndEncies = new TreeMap<>();
    private final Random randomGenerator;
    private double totalWagesInverseSum = 0;

    public RandomCollection() {
        this(new Random());
    }

    public RandomCollection(Random random) {
        this.randomGenerator = random;
    }

    public void add(double weight, Ency result) {
        if (weight <= 0) return;
        totalWagesInverseSum += 1 / weight;
        wagesAndEncies.put(totalWagesInverseSum, result);
        System.out.println("Added");
    }

    public RandomCollection add(double weight, Ency result, NavigableMap<Double, Ency> randomCollection) {
        if (weight <= 0) return this;
        totalWagesInverseSum += 1 / weight;
        randomCollection.put(totalWagesInverseSum, result);
        return this;
    }

    public void updateWage(Ency selectedEncyByUser, double changeWage) {
        if (selectedEncyByUser.getWage() + changeWage <= 0) {
            System.out.println("Cannot update wage" + selectedEncyByUser.getWage() + " below 0");
            return;
        }
        System.out.println("Wage updated from " + selectedEncyByUser.getWage() + " to " + (selectedEncyByUser.getWage() + changeWage));
        selectedEncyByUser.setWage(selectedEncyByUser.getWage() + changeWage);
        NavigableMap<Double, Ency> newMap = new TreeMap<>();
        totalWagesInverseSum = 0;
        wagesAndEncies.forEach((k, v) -> add(v.getWage(), v, newMap));
        wagesAndEncies = newMap;
    }

    public Ency next() {
        double value = randomGenerator.nextDouble() * totalWagesInverseSum;
        return wagesAndEncies.higherEntry(value).getValue();
    }

    public NavigableMap<Double, Ency> getWagesAndEncies() {
        return wagesAndEncies;
    }

    public double getTotalWagesInverseSum() {
        return totalWagesInverseSum;
    }
}