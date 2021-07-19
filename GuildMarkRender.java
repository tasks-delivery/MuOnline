package provider.service;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class GuildMarkRender {

    /**
     * Split String using substring, you'll have to tell where to split
     * @param src String to split
     * @param len where to split
     * @return
     */
    private static String[] splitMarkerCode(String src, int len) {
        String[] result = new String[(int)Math.ceil((double)src.length()/(double)len)];
        for (int i=0; i<result.length; i++)
            result[i] = src.substring(i*len, Math.min(src.length(), (i+1)*len));
        return result;
    }

    private static void colorMapping(Graphics2D g2d, char charAtPosition){
        switch (charAtPosition){
            case '2':  g2d.setColor(Color.GRAY);
                break;
            case '3':  g2d.setColor(Color.WHITE);
                break;
            case '4':  g2d.setColor(Color.RED);
                break;
            case '5':  g2d.setColor(Color.ORANGE);
                break;
            case '6':  g2d.setColor(Color.YELLOW);
                break;
            case '7':
            case '8':
            case '9':
                g2d.setColor(Color.GREEN);
                break;
            case 'A':  g2d.setColor(Color.CYAN);
                break;
            case 'B':
            case 'C':
                g2d.setColor(Color.BLUE);
                break;
            case 'D':
            case 'E':
                g2d.setColor(Color.MAGENTA);
                break;
            case 'F':  g2d.setColor(Color.PINK);
                break;
            case '1':
            case '0':
            default: g2d.setColor(Color.BLACK);
        }
    }

    private static byte[] renderMark(String markCode){
        int type = BufferedImage.TYPE_INT_ARGB;
        BufferedImage image = new BufferedImage(8, 8, type);
        Graphics2D g2d = image.createGraphics();
        String[] arr = splitMarkerCode(markCode, 8);
        for (int a = 0; a < arr.length; a++){
            for (int z = 0; z < arr[a].length(); z++){
                char charAtPosition = arr[a].charAt(z);
                colorMapping(g2d, charAtPosition);
                g2d.fillRect(z, a, 1, 1);
            }
        }
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try {
            ImageIO.write(image, "png", baos);
        } catch (IOException e) {
            e.printStackTrace();
        }
        g2d.dispose();
        return baos.toByteArray();
    }

}
