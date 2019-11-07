public class Coordinates implements Comparable<Coordinates> {
    public final int x;
    public final int y;

    public Coordinates(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Coordinates position = (Coordinates) o;
        return compareTo(position) == 0;
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
