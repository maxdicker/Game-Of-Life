public class Cell {
    private boolean alive;

    public Cell(boolean alive) {
        this.alive = alive;
    }

    public boolean isAlive() {
        return alive;
    }

    public void kill() {
        this.alive = false;
    }

    public void revive() {
        this.alive = true;
    }

    @Override
    public String toString() {
        return alive ? "\u25FC" : "\u25FB";
    }
}
