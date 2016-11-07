package until;

import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.usermodel.Range;

import java.io.*;
import java.util.Map;

/**
 * Created by yanglei on 2016/11/7.
 */
public class wordUntil {


    /**
     * word doc 内容替换
     *
     * @param sourcePath     doc文件
     * @param distiationPath doc 替换后的doc
     * @param map
     * @return
     */
    public boolean replaceMarkInDoc(String sourcePath, String distiationPath, Map<String, String> map) {
        boolean issuccess = false;
        try {
            InputStream is = new FileInputStream(sourcePath);
            HWPFDocument doc = new HWPFDocument(is);
            Range range = doc.getRange();
            for (Map.Entry<String, String> entry : map.entrySet()) {
                range.replaceText(entry.getKey(), entry.getValue());
            }
            OutputStream os = new FileOutputStream(distiationPath);
            doc.write(os);
            if (os != null) {
                os.close();
            }
            if (is != null) {
                is.close();
            }
            issuccess = true;
        } catch (IOException ex) {
        } finally {
            return issuccess;
        }
    }

    public boolean doc2Pdf() {
        boolean issuccess = false;
        try {

        } catch (Exception ex) {

        } finally {
            return issuccess;
        }
    }
}
