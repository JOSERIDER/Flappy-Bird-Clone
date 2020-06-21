import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Stripe {

    public static BufferedImage getStripe(String fileName) throws IOException {
        return ImageIO.read(Stripe.class.getResourceAsStream(fileName));
    }
}
