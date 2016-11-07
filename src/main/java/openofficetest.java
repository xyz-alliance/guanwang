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
        File inputFile = new File(docPath);//Ԥת�ļ�
        File outputFile = new File(pdfPath);//pdf�ļ�
        OpenOfficeConnection connection = new SocketOpenOfficeConnection(8100);
        connection.connect();//��������
        DocumentConverter converter = new OpenOfficeDocumentConverter(connection);
        DefaultDocumentFormatRegistry formatReg = new DefaultDocumentFormatRegistry();
        DocumentFormat txt = formatReg.getFormatByFileExtension("odt") ;//�趨�ļ���ʽ
        DocumentFormat pdf = formatReg.getFormatByFileExtension("pdf") ;//�趨�ļ���ʽ
        converter.convert(inputFile, txt, outputFile, pdf);//�ļ�ת��
        connection.disconnect();//�ر�����
    }
}
