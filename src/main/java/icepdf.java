import org.icepdf.core.exceptions.PDFException;
import org.icepdf.core.exceptions.PDFSecurityException;
import org.icepdf.core.pobjects.Document;
import org.icepdf.core.util.GraphicsRenderingHints;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.IOException;

/**
 * Created by yanglei on 2016/11/7.
 */
public class icepdf {

    public static void main(String[] args) {
        try {
            new icepdf().test();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (PDFException e) {
            e.printStackTrace();
        } catch (PDFSecurityException e) {
            e.printStackTrace();
        }
    }

    public void test() throws IOException, PDFException, PDFSecurityException {
        String filePath = "C:\\Users\\yanglei\\Desktop\\pdftest\\surrender.pdf";
        Document document = new Document();
        document.setFile(filePath);
        float scale = 2.5f;//缩放比例
        float rotation = 0f;//旋转角度

        for (int i = 0; i < document.getNumberOfPages(); i++) {
            BufferedImage image = (BufferedImage) document.getPageImage(i, GraphicsRenderingHints.SCREEN, org.icepdf.core.pobjects.Page.BOUNDARY_CROPBOX, rotation, scale);
            RenderedImage rendImage = image;
            try {
                File file = new File("C:\\Users\\yanglei\\Desktop\\pdftest\\new\\test" + i + ".png");
                ImageIO.write(rendImage, "png", file);
            } catch (IOException e) {
                e.printStackTrace();
            }
            image.flush();
        }
        document.dispose();
    }
}
