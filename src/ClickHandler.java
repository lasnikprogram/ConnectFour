import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

public class ClickHandler implements MouseListener {
    private final ArrayList<Shape> shapes;

    // constructor takes the shapes it has to handle
    public ClickHandler(ArrayList<Shape> shapes) {
        this.shapes = shapes;
    }

    // https://stackoverflow.com/a/24035204
    @Override
    public void mouseClicked(MouseEvent mouseEvent) {
        for(int i = 0; i < shapes.size(); i++) {
            Shape shape = shapes.get(i);
            // when shape is clicked, call makeMove with index of shape
            if(shape.contains(mouseEvent.getPoint())) {
                GameHandler.makeMove(i);
            }
        }
    }

    @Override
    public void mousePressed(MouseEvent mouseEvent) {}

    @Override
    public void mouseReleased(MouseEvent mouseEvent) {}

    @Override
    public void mouseEntered(MouseEvent mouseEvent) {}

    @Override
    public void mouseExited(MouseEvent mouseEvent) {}
}
