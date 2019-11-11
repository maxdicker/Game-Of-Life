package core;

public class Coordinates implements Comparable<Coordinates> {
    public final int x;
    public final int y;

    public Coordinates(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public int compareTo(Coordinates o) {
        int cmp = Integer.compare(y, o.y);
        if (cmp == 0) {
            cmp = Integer.compare(x, o.x);
        }
        return cmp;
    }

}
