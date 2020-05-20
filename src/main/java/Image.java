import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Image {
    private BufferedImage image = null;
    private int width = 0;
    private int height = 0;

    public Image(String path){
        try {
            image = ImageIO.read(new File(path));
            width = image.getWidth();
            height = image.getHeight();
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    public int getWidth(){
        return width;
    }

    public int getHeight() {
        return height;
    }

    public ArrayList<ArrayList<int[]>> getAllPixelsRGB(){
        return parseAllPixelsRGB();
    }

    public int[][] getAllPixelsInt(){
        return parseAllPixelsInt();
    }

    public void printResolution(){
        System.out.println(String.format("Image size: %d x %d", width, height));
    }

    public void printTwoDimentionalRGB(){
        ArrayList<ArrayList<int[]>> pixels = getAllPixelsRGB();
        for (ArrayList<int[]> row : pixels){
            System.out.print("[ ");
            for (int[] pixelRGB : row) {
                System.out.print(String.format("(%d, %d, %d), ", pixelRGB[0], pixelRGB[1], pixelRGB[2]));
            }
            System.out.println("]");
        }
    }

    public void printTwoDimentionalIntColor(){
        int[][] pixels = getAllPixelsInt();
        for(int[] row : pixels){
            System.out.print("[ ");
            for(int pixel : row){
                System.out.print(String.format("%d, ", pixel));
            }
            System.out.println("]");
        }
    }


    private int[] parsePixelRGB(int pixelARGB){
        int alpha = (pixelARGB >> 24) & 0xff; // actually I guess we don't need this
        int red = (pixelARGB >> 16) & 0xff;
        int green = (pixelARGB >> 8) & 0xff;
        int blue = (pixelARGB) & 0xff;
        return new int[] {red, green, blue};
    }

    private ArrayList<ArrayList<int[]>> parseAllPixelsRGB(){
        ArrayList<ArrayList<int[]>> pixels  = new ArrayList<ArrayList<int[]>>();
        for(int i = 0; i < height; i++){
            ArrayList<int[]> row = new ArrayList<int[]>();
            for(int j = 0; j < width; j++){
                int argb = image.getRGB(j,i);
                int[] pixel = parsePixelRGB(argb);
                row.add(pixel);
            }
            pixels.add(row);
        }
        return pixels;
    }

    private int[][] parseAllPixelsInt(){
        int[][] pixelsInt = new int[height][width];
        ArrayList<ArrayList<int[]>> pixelsRGB = this.parseAllPixelsRGB();

        for(int i = 0; i < height; i++) {
            int[] row = new int[width];
            for(int j = 0; j < width; j++){
                int[] pixelRGB = pixelsRGB.get(i).get(j);
                int pixelInt = (int) (pixelRGB[0] + pixelRGB[1] + pixelRGB[2]) / 3;
                row[j] = pixelInt;
            }
            pixelsInt[i] = row;
        }
        return pixelsInt;
    }
}
