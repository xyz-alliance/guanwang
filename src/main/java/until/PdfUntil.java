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
 * PDF 处理类
 */
public class PdfUntil {
    /**
     * 单页pdf转换为png图片
     * icepdf jar 支持pdf 转图片
     *
     * @param sourcePath  源 pdf地址
     * @param destionPath 目的所在的路径
     * @return
     */
    public boolean simplePdf2Png(String sourcePath, String destionPath) {
        boolean issuccess = false;
        try {
            Document document = new Document();
            document.setFile(sourcePath);
            float scale = 2.5f;//缩放比例
            float rotation = 0f;//旋转角度
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
