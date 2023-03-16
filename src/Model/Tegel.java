package Model;
public class Tegel implements Comparable<Tegel> {
    private final int wurms, number;
    public Tegel(int wurms, int number) {
        this.wurms = wurms;
        this.number = number;
    }
    public int getWurms() {
        return wurms;
    }
    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    public int getNumber() {
        return number;
    }

    @Override
    public int compareTo(Tegel o) {
        return number - o.number;
    }
}
