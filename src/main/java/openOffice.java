import com.artofsolving.jodconverter.DocumentConverter;
import com.artofsolving.jodconverter.openoffice.connection.OpenOfficeConnection;
import com.artofsolving.jodconverter.openoffice.connection.SocketOpenOfficeConnection;
import com.artofsolving.jodconverter.openoffice.converter.OpenOfficeDocumentConverter;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.ConnectException;

/**
 * Created by yanglei on 2016/11/8.
 */
public class openOffice {
    /**
     * ��Office�ĵ�ת��ΪPDF. ���иú�����Ҫ�õ�OpenOffice, OpenOffice���ص�ַΪ
     * http://www.openoffice.org/
     *
     * <pre>
     * ����ʾ��:
     * String sourcePath = "F:\\office\\source.doc";
     * String destFile = "F:\\pdf\\dest.pdf";
     * Converter.office2PDF(sourcePath, destFile);
     * </pre>
     *
     * @param sourceFile
     *            Դ�ļ�, ����·��. ������Office2003-2007ȫ����ʽ���ĵ�, Office2010��û����. ����.doc,
     *            .docx, .xls, .xlsx, .ppt, .pptx��. ʾ��: F:\\office\\source.doc
     * @param destFile
     *            Ŀ���ļ�. ����·��. ʾ��: F:\\pdf\\dest.pdf
     * @return �����ɹ�������ʾ��Ϣ. ������� -1, ��ʾ�Ҳ���Դ�ļ�, ��url.properties���ô���; ������� 0,
     *         ���ʾ�����ɹ�; ����1, ���ʾת��ʧ��
     */
    public static int office2PDF(String sourceFile, String destFile) {
        try {
            File inputFile = new File(sourceFile);
            if (!inputFile.exists()) {
                return -1;// �Ҳ���Դ�ļ�, �򷵻�-1
            }

            // ���Ŀ��·��������, ���½���·��
            File outputFile = new File(destFile);
            if (!outputFile.getParentFile().exists()) {
                outputFile.getParentFile().mkdirs();
            }

            String OpenOffice_HOME = "D:\\Program Files\\OpenOffice.org 3";//������OpenOffice�İ�װĿ¼, ���ҵ���Ŀ��,Ϊ�˱�����չ�ӿ�,û��ֱ��д���������,���������Ǿ���û�����
            // ������ļ��ж�ȡ��URL��ַ���һ���ַ����� '\'�������'\'
            if (OpenOffice_HOME.charAt(OpenOffice_HOME.length() - 1) != '\\') {
                OpenOffice_HOME += "\\";
            }
            // ����OpenOffice�ķ���
            String command = OpenOffice_HOME
                    + "program\\soffice.exe -headless -accept=\"socket,host=127.0.0.1,port=8100;urp;\"";
            Process pro = Runtime.getRuntime().exec(command);
            // connect to an OpenOffice.org instance running on port 8100
            OpenOfficeConnection connection = new SocketOpenOfficeConnection(
                    "127.0.0.1", 8100);
            connection.connect();

            // convert
            DocumentConverter converter = new OpenOfficeDocumentConverter(
                    connection);
            converter.convert(inputFile, outputFile);

            // close the connection
            connection.disconnect();
            // �ر�OpenOffice����Ľ���
            pro.destroy();

            return 0;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return -1;
        } catch (ConnectException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return 1;
    }
}
