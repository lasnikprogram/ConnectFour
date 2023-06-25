import javax.swing.*;

public class Main {
    private static JFrame frame;
    public static void main(String[] args) {
        frame = new JFrame();
        frame.setName("Connect Four");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        Board.makeEmptyBoard();

        DrawingBoard drawingBoard = new DrawingBoard();
        frame.add(drawingBoard);

        frame.pack();
        // center window
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    public static void repaint(){
        frame.repaint();
    }
}
