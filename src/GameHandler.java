public class GameHandler {
    private static int amountOfPlays;

    // decide which player does the move
    public static void makeMove(int fieldIndex) {
        if (amountOfPlays % 2 == 0)
            setChip(fieldIndex, FieldState.BLUE);
        else
            setChip(fieldIndex, FieldState.RED);

        Main.repaint();
    }

    // set index to specific field state
    private static void setChip(int fieldIndex, FieldState fieldState) {
        int[] columnAndRow = Board.indexToColumnAndRow(fieldIndex);
        int column = columnAndRow[0];
        // only place chip in the lowest row
        for (int rowIterator = 0; rowIterator < Board.getRows(); rowIterator++) {
            FieldState field = Board.getField(column, rowIterator);
            FieldState fieldBelow = Board.getField(column, rowIterator + 1);
            if(field == FieldState.EMPTY && fieldBelow != FieldState.EMPTY) {
                Board.setField(column, rowIterator, fieldState);
                amountOfPlays++;
                checkWinner(fieldState);
                break;
            }
        }
    }

    // check if player that occupies field state type won
    private static void checkWinner(FieldState fieldState) {
        // horizontal
        checkLine(fieldState, new int[][]{{0, 0}, {1, 0}, {2, 0}, {3, 0}});
        // vertical
        checkLine(fieldState, new int[][]{{0, 0}, {0, 1}, {0, 2}, {0, 3}});
        // diagonal descending
        checkLine(fieldState, new int[][]{{0, 0}, {1, 1}, {2, 2}, {3, 3}});
        // diagonal ascending
        checkLine(fieldState, new int[][]{{0, 0}, {1, -1}, {2, -2}, {3, -3}});
    }

    // check if there is the correct amount of nearby pieces
    private static void checkLine(FieldState fieldState, int[][] neighbourArray) {
        // iterate through each field in board
        for (int y = 0; y < Board.getRows(); y++) {
            for (int x = 0; x < Board.getColumns(); x++) {
                int matchCounter = 0;
                for (int[] pair : neighbourArray) {
                    int xDiffer = pair[0];
                    int yDiffer = pair[1];
                    if (Board.getField(x + xDiffer, y + yDiffer) == fieldState) {
                        matchCounter++;
                    } else {
                        matchCounter = 0;
                    }
                }
                // call setWinner when every nearby pair got found
                if (matchCounter == neighbourArray.length) {
                    setWinner(fieldState);
                }
            }
        }
    }

    private static void setWinner(FieldState fieldState) {
        System.out.println(fieldState.name() + " won the game!");
        reset();
    }

    private static void reset() {
        amountOfPlays = 0;
        Board.makeEmptyBoard();
    }
}
