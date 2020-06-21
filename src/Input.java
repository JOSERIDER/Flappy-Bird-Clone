import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Input implements MouseListener {

    private boolean mousePressed = false;

    public boolean isMousePressed() {
        boolean s = mousePressed;
        mousePressed = false;
        return s;
    }


    @Override
    public void mouseClicked(MouseEvent e) {
        mousePressed = true;
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {
            mousePressed = true;
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
