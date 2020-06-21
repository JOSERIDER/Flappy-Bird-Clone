import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Input implements MouseListener {

    private boolean spacePressed= false;
    private boolean spaceReleased = true;

    public boolean isSpacePressed() {
        boolean s = spacePressed;
        spacePressed = false;
        return s;
    }


    @Override
    public void mouseClicked(MouseEvent e) {
            spaceReleased = false;
            spacePressed = true;
    }

    @Override
    public void mousePressed(MouseEvent e) {
            spaceReleased = true;

    }

    @Override
    public void mouseReleased(MouseEvent e) {
            spacePressed = true;
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
