import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.usermodel.Range;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by yanglei on 2016/11/6.
 */
public class HwpfTest {
    public static void main(String[] args) throws Exception {
        HwpfTest t=new HwpfTest();
        t.testWrite();
    }
    public void testWrite() throws Exception {
        String templatePath = "C:\\Users\\yanglei\\Desktop\\aa.doc";
        InputStream is = new FileInputStream(templatePath);
        HWPFDocument doc = new HWPFDocument(is);
        Range range = doc.getRange();
        //��range��Χ�ڵ�${reportDate}�滻Ϊ��ǰ������
        range.replaceText("${a}", new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
        range.replaceText("${b}", "100.00");
        range.replaceText("${c}", "200.00");
        range.replaceText("${d}", "300.00");
        OutputStream os = new FileOutputStream("C:\\Users\\yanglei\\Desktop\\test2.doc");
        //��doc������������
        doc.write(os);
        this.closeStream(os);
        this.closeStream(is);
    }

    /**
     * �ر�������
     * @param is
     */
    private void closeStream(InputStream is) {
        if (is != null) {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * �ر������
     * @param os
     */
    private void closeStream(OutputStream os) {
        if (os != null) {
            try {
                os.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
