public class Coordinate {

    private boolean containShip;
    private boolean wasChecked;
    public static enum State {
        EMPTY, FILLED, SHOOTED, MISSED
    }

    Coordinate () {
        this.containShip = false;
        this.wasChecked = false;
    }

    State checkState () {
        if (!this.containShip && !this.wasChecked) {
            return State.EMPTY;
        } else if (this.containShip && !this.wasChecked) {
            return State.FILLED;
        } else if (!this.containShip) {
            return State.MISSED;
        } else {
            return State.SHOOTED;
        }
    }

}
