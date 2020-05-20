import org.junit.Assert;
import org.junit.Test;

public class Test_Image {

    private String PATH_TO_PICTURE = "/home/ivan/Pictures/LH1-Menu-icon.png"; // 128x128
    private String PATH_TO_SMALL_PICTURE = "/home/ivan/Pictures/very_small_pic.png";

    @Test
    public void test_printResolution(){
        Image img = new Image(PATH_TO_PICTURE);
        img.printResolution();
        Assert.assertEquals(128, img.getHeight());
        Assert.assertEquals(128, img.getWidth());
    }

    @Test
    public void test_printTwoDimenionalMatrixOfPixels(){
        Image img = new Image(PATH_TO_SMALL_PICTURE);
        img.printTwoDimentionalRGB();
    }

    @Test
    public void test_printTwoDimentionalMatrixInts(){
        Image img = new Image(PATH_TO_SMALL_PICTURE);
        img.printTwoDimentionalIntColor();
    }
}
