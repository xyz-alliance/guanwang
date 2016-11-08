import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by yanglei on 2016/11/8.
 */
public class itext2 {
    private static String FILE_DIR = "C:\\Users\\yanglei\\Desktop\\";


/*    int bytesWritten = 0;
    06
    int byteCount = 0;
    07

            08
    byte[] bytes = new byte[1024];
    09

            10
            while ((byteCount = inputStream.read(bytes)) != -1)
            11
    {
        12
        outputStream.write(bytes, bytesWritten, byteCount);
        13
        bytesWritten += byteCount;
        14
    }*/

    public void test() throws IOException, DocumentException {
        FileInputStream in = new FileInputStream(FILE_DIR + "surrender.pdf");
        FileOutputStream out = new FileOutputStream(FILE_DIR + "surrender2.pdf");
        byte[] bytes = new byte[1024];
        while (in.read(bytes) != -1) {
            out.write(bytes);
            out.flush();
        }
        Document document = new Document();
        document.open();
        Font font = new Font(Font.FontFamily.HELVETICA, 6, Font.BOLD, BaseColor.WHITE);
        Chunk id = new Chunk("chifadfadsfadsfadsfnese", font);
        id.setBackground(BaseColor.RED, 1f, 0.5f, 1f, 1.5f);
        id.setTextRise(6);
        document.add(id);
        document.add(Chunk.NEWLINE);
    }

    public void test2() throws IOException, DocumentException {
        //Í¼Æ¬Ë®Ó¡
        PdfReader reader = new PdfReader(FILE_DIR + "surrender.pdf");
        PdfStamper stamp = new PdfStamper(reader, new FileOutputStream(FILE_DIR
                + "setWatermark2.pdf"));

       /* Image img = Image.getInstance(FILE_DIR+"6.jpg");
        img.setAbsolutePosition(200, 400);
        PdfContentByte under = stamp.getUnderContent(1);
        under.addImage(img);*/

//ÎÄ×ÖË®Ó¡
        PdfContentByte over = stamp.getOverContent(0);
        over.beginText();
        BaseFont bf = BaseFont.createFont(BaseFont.HELVETICA, BaseFont.WINANSI,
                BaseFont.EMBEDDED);
        over.setFontAndSize(bf, 18);
        over.setTextMatrix(30, 30);
        over.showTextAligned(Element.ALIGN_LEFT, "DUPLICATE", 230, 430, 45);
        over.endText();

/*//±³¾°Í¼
        Image img2 = Image.getInstance("resource/test.jpg");
        img2.setAbsolutePosition(0, 0);
        PdfContentByte under2 = stamp.getUnderContent(3);
        under2.addImage(img2);*/

        stamp.close();
        reader.close();
    }

    //surrender.pdf
    public static void main(String[] args) throws IOException, DocumentException {
        new itext2().test2();
    }
}
