import com.artofsolving.jodconverter.DefaultDocumentFormatRegistry;
import com.artofsolving.jodconverter.DocumentConverter;
import com.artofsolving.jodconverter.DocumentFormat;
import com.artofsolving.jodconverter.openoffice.connection.OpenOfficeConnection;
import com.artofsolving.jodconverter.openoffice.connection.SocketOpenOfficeConnection;
import com.artofsolving.jodconverter.openoffice.converter.OpenOfficeDocumentConverter;

import java.io.File;
import java.net.ConnectException;

/**
 * Created by yanglei on 2016/11/6.
 */
public class openofficetest {

    public static void doc2Pdf(String docPath, String pdfPath) throws ConnectException {
        File inputFile = new File(docPath);//预转文件
        File outputFile = new File(pdfPath);//pdf文件
        OpenOfficeConnection connection = new SocketOpenOfficeConnection(8100);
        connection.connect();//建立连接
        DocumentConverter converter = new OpenOfficeDocumentConverter(connection);
        DefaultDocumentFormatRegistry formatReg = new DefaultDocumentFormatRegistry();
        DocumentFormat txt = formatReg.getFormatByFileExtension("odt") ;//设定文件格式
        DocumentFormat pdf = formatReg.getFormatByFileExtension("pdf") ;//设定文件格式
        converter.convert(inputFile, txt, outputFile, pdf);//文件转换
        connection.disconnect();//关闭连接
    }
}
