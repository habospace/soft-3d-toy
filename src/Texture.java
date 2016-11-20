import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

class Texture {

    private final int height;
    private final int width;
    private final Color[][] colorArray;

    Texture (String path) {
        this.colorArray = loadImage(path);
        this.height = colorArray.length;
        this.width = colorArray[0].length;
    }

    private static Color[][] loadImage (String path) {

        Color[][] colorArray;
        int height;
        int width;

        try {
            BufferedImage img;
            img = ImageIO.read(new File(path));
            height = img.getHeight();
            width = img.getWidth();
            colorArray = new Color[height][width];

            for (int y = 0; y < height; y++) {
                for (int x = 0; x < width; x++) {
                    colorArray[y][x] = new Color(img.getRGB(x, y));
                }
            }
            return colorArray;
        }
        catch (IOException e) {
            height = 100;
            width = 100;
            colorArray = new Color[height][width];
            for (int y = 0; y < height; y++) {
                for (int x = 0; x < width; x++) {
                    colorArray[y][x] = new Color(150, 150, 150);
                }
            }
            return colorArray;
        }
    }

    Color map (double uPos, double vPos) {

        final int x = Math.abs((int) (uPos * width) % width);
        final int y = Math.abs((int) (vPos * height) % height);

        return new Color(colorArray[y][x].getRGB());
    }

}
