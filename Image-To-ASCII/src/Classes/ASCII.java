package Classes;

import java.awt.Color;
import java.awt.image.BufferedImage;

public class ASCII {

    private BufferedImage Image;
    public ASCII(BufferedImage Image) {
        setBuffredImage(Image);
    }

    private void setBuffredImage(BufferedImage Image) {
        this.Image = Image;
    }

    public String convert() {
        StringBuilder sBuilder = new StringBuilder(Image.getWidth() * Image.getHeight());
        for (int height = 0; height < Image.getHeight(); height++) {
            if (sBuilder.length() != 0) {
                sBuilder.append("\n");
            }
            for (int width = 0; width < Image.getWidth(); width++) {
                Color pixelColor = new Color(Image.getRGB(width, height));

                double grayScaleVal = (double) (pixelColor.getRed() * 0.299 + pixelColor.getGreen() * 0.587 + pixelColor.getBlue() * 0.114);
                char gscaletoChar = colorToChar(grayScaleVal);
                sBuilder.append(gscaletoChar);
            }
        }
        return sBuilder.toString();
    }

    private char colorToChar(double value) {
        if (value < 25) {
            return '@';
        } else if (value >= 25 && value < 51) {
            return '#';
        } else if (value >= 51 && value < 76) {
            return '8';
        } else if (value >= 76 && value < 102) {
            return '&';
        } else if (value >= 102 && value < 127) {
            return 'o';
        } else if (value >= 127 && value < 153) {
            return '*';
        } else if (value >= 153 && value < 178) {
            return '/';
        } else if (value >= 178 && value < 204) {
            return '\'';
        } else if (value >= 204 && value < 229) {
            return '.';
        }
        return ' ';
    }

    public void dispose() {
        Image = null;
    }
}
