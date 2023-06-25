import java.util.Arrays;

public class Board {
    // https://en.wikipedia.org/wiki/Connect_Four
    // seven columns, six rows
    private static final int columns = 7;
    private static final int rows = 6;
    //  https://stackoverflow.com/q/34970923
    private static final FieldState[] board = new FieldState[getColumns() * getRows()];
    public static void makeEmptyBoard() {
        // make every field of board empty
        Arrays.fill(board, FieldState.EMPTY);
    }

    public static void setField(int column, int row, FieldState fieldState){
        setField(columnAndRowToIndex(column, row), fieldState);
    }
    public static void setField(int index, FieldState fieldState){
        board[index] = fieldState;
    }

    public static FieldState getField(int column, int row){
        int index = columnAndRowToIndex(column, row);
        if (index >= board.length || index < 0)
            return FieldState.NOT_DEFINED;
        else
            return board[index];
    }

    public static int[] indexToColumnAndRow(int index) {
        return new int[]{index % columns, index / columns};
    }

    public static int columnAndRowToIndex (int column, int row) {
        return columns * row + column;
    }

    public static int getColumns() {
        return columns;
    }

    public static int getRows() {
        return rows;
    }
}
