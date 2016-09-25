public class Field {

    private Coordinate[][] coordinates;
    private int fieldVerticalSize;
    private int fieldHorizontalSize;
    private int[] shipsCount;
    public static enum Hardness {
        EASY, MEDIUM, HARD
    }


    Field (int verticalSize, int horizontalSize, Hardness hardness) {
        this.fieldVerticalSize = verticalSize;
        this.fieldHorizontalSize = horizontalSize;
        if (this.fieldVerticalSize < 2) {
            this.fieldVerticalSize = 2;
        }
        if (this.fieldHorizontalSize < 2) {
            this.fieldHorizontalSize = 2;
        }

        this.coordinates = new Coordinate[this.fieldVerticalSize][this.fieldHorizontalSize];
        this.shipsCount = GetShipsCount(
                Math.min(this.fieldVerticalSize, this.fieldHorizontalSize),
                this.fieldVerticalSize * this.fieldHorizontalSize / 5,
                hardness
        );

        System.out.print("[");
        for (int i = 0; i < this.shipsCount.length; i++) {
            System.out.print(this.shipsCount[i]);
            if (i != this.shipsCount.length - 1) {
                System.out.print(",");
            }
        }
        System.out.print("]");
    }

    private void FillField () {

    }

    private int GetHardnessCoefficient (Hardness hardness) {
        switch (hardness) {
            case EASY:
                return 0;
            case MEDIUM:
                return 1;
            case HARD:
                return 2;
            default:
                return 1;
        }
    }

    private int[] GetShipsCount (int maxShipSize, int coordinatesCount, Hardness hardness) {

        int[] shipsArray = new int[maxShipSize];
        int hardnessCoefficient = GetHardnessCoefficient(hardness);
        int realShipsArrayLength = 1;
        shipsArray[0] = coordinatesCount;

        while (shipsArray[0] - shipsArray[1] > hardnessCoefficient) {
            for (int i = realShipsArrayLength; i >= 1; i--) {
                if (shipsArray[i-1]-shipsArray[i]-1 >= hardnessCoefficient && shipsArray[0]-i-1-shipsArray[1] >= hardnessCoefficient) {
                    shipsArray[i]++;
                    shipsArray[0] = shipsArray[0]-i-1;
                    if (realShipsArrayLength < i+1) {
                        realShipsArrayLength = i+1;
                    }
                    break;
                }
            }
        }

        int [] realShipsArray = new int[realShipsArrayLength];
        for (int i = 0; i < realShipsArrayLength; i++ ) {
            realShipsArray[i] = shipsArray[i];
        }

        return realShipsArray;
    }
}
