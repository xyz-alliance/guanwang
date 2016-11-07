
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.tool.xml.XMLWorkerHelper;

/**
 * Created by yanglei on 2016/11/7.
 */
public class html2pdf {
    public static void main(String[] args) throws IOException, DocumentException {
        // step 1
        com.itextpdf.text.Document document = new com.itextpdf.text.Document();
        // step 2
        PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream("C:\\Users\\yanglei\\Desktop\\pdftest\\ttt.pdf"));
        // step 3
        document.open();
        // step 4
        XMLWorkerHelper.getInstance().parseXHtml(writer, document,new FileInputStream("C:\\Users\\yanglei\\Desktop\\pdftest\\789465451321.html"));
        //step 5
        document.close();

        System.out.println( "PDF Created!" );
    }
}
