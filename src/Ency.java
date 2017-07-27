public class Ency<T> {

    private T value;
    private double wage;

    public Ency(T value, double wage) {
        this.value = value;
        this.wage = wage;
    }

    public T getValue() {
        return value;
    }

    public double getWage() {
        return wage;
    }

    public void setWage(double wage) {
        this.wage = wage;
    }
}
