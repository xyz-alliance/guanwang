package until;

/**
 * Created by yanglei on 2016/11/7.
 */

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
 * PDF ������
 */
public class PdfUntil {
    /**
     * ��ҳpdfת��ΪpngͼƬ
     * icepdf jar ֧��pdf תͼƬ
     *
     * @param sourcePath  Դ pdf��ַ
     * @param destionPath Ŀ�����ڵ�·��
     * @return
     */
    public boolean simplePdf2Png(String sourcePath, String destionPath) {
        boolean issuccess = false;
        try {
            Document document = new Document();
            document.setFile(sourcePath);
            float scale = 2.5f;//���ű���
            float rotation = 0f;//��ת�Ƕ�
            BufferedImage image = (BufferedImage) document.getPageImage(0, GraphicsRenderingHints.SCREEN,
                    org.icepdf.core.pobjects.Page.BOUNDARY_CROPBOX, rotation, scale);
            RenderedImage rendImage = image;
            File file = new File(destionPath);
            ImageIO.write(rendImage, "png", file);
            image.flush();
            issuccess = true;
        } catch (PDFSecurityException e) {
            e.printStackTrace();
        } catch (PDFException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            return issuccess;
        }
    }
}
