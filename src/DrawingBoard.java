import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.util.ArrayList;

public class DrawingBoard extends JPanel {
    private final ArrayList<Shape> shapes;

    public DrawingBoard() {
        shapes = new ArrayList<>();
        setBackground(Values.backgroundColor);
        Dimension size = new Dimension(
                Board.getColumns() * (Values.circleRadius + Values.circleOffset), Board.getRows() * (Values.circleRadius + Values.circleOffset));
        setPreferredSize(size);

        super.addMouseListener(new ClickHandler(shapes));
    }
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D graphics2D = (Graphics2D) g.create();

        // enable antialiasing: https://stackoverflow.com/a/69271404
        graphics2D.setRenderingHints(new RenderingHints(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON));

        shapes.clear();

        // draw circles
        for (int y = 0; y < Board.getRows(); y++) {
            for (int x = 0; x < Board.getColumns(); x++) {
                fieldStateToColor(Board.getField(x, y), graphics2D);
                drawCircle(graphics2D, x, y);
            }
        }
    }

    // https://www.delftstack.com/howto/java/draw-a-circle-in-java/
    private void drawCircle(Graphics2D graphics2D, int x, int y) {
        // start values for center
        int xStartValue = (getWidth() - Board.getColumns() * (Values.circleRadius + Values.circleOffset) + Values.circleOffset) / 2;
        int yStartValue = (getHeight() - Board.getRows() * (Values.circleRadius + Values.circleOffset) + Values.circleOffset) / 2;
        Shape circle = new Ellipse2D.Double(
                xStartValue + x * (Values.circleRadius + Values.circleOffset),
                yStartValue + y * (Values.circleRadius + Values.circleOffset),
                Values.circleRadius, Values.circleRadius);
        shapes.add(circle);
        graphics2D.fill(circle);
    }

    private void fieldStateToColor(FieldState fieldState, Graphics2D graphics2D) {
        // https://www.w3schools.com/java/java_switch.asp
        switch (fieldState) {
            case EMPTY:
                graphics2D.setColor(Values.emptyField);
                break;
            case RED:
                graphics2D.setColor(Values.redField);
                break;
            case BLUE:
                graphics2D.setColor(Values.blueField);
                break;
        }
    }
}
