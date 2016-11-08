package until;

import com.itextpdf.io.font.CFFFont;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.pdf.*;

import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by yanglei on 2016/11/8.
 */
public class AddContentToPDF {
    private static String FILE_DIR = "C:\\Users\\yanglei\\Desktop\\";
    public static void main(String[] args) throws IOException, DocumentException {

        //����һ��pdf������
        PdfReader reader = new PdfReader(FILE_DIR+"surrender.pdf");
        //����һ��pdfreader����һ��pdfStamper.���������µ�pdf.
        PdfStamper stamper = new PdfStamper(reader,
                new FileOutputStream(FILE_DIR+"help2.pdf"));
        //���������itext-asian.jar���Դ��� ���Բ��ÿ��ǲ���ϵͳ��������.
       /* BaseFont bf = BaseFont.createFont("STSong-Light",
                "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED); */// set font
        BaseFont bf = BaseFont.createFont();
        //baseFont��֧��������ʽ�趨.����font����Ҫ�����ϵͳ֧�ִ�����������ֲ����.
        Font font = new Font(bf,10);
        font.setStyle(Font.BOLD);
        font.getBaseFont();
        //ҳ���Ǵ�1��ʼ��
        for (int i=1; i<=reader.getNumberOfPages(); i++){

            //���pdfstamper�ڵ�ǰҳ���ϲ��ӡ����.Ҳ����˵ ��Щ���ݻḲ����ԭ�ȵ�pdf����֮��.
            PdfContentByte over = stamper.getOverContent(i);
            //��pdfreader��õ�ǰҳ�ֵ����.�����˸�ҳ��һЩ����.�����ҳ����������Ϣ.
            PdfDictionary p = reader.getPageN(i);
            //�õ�mediaBox ������Ÿ�ҳpdf�Ĵ�С��Ϣ.
            PdfObject po =  p.get(new PdfName("MediaBox"));
            System.out.println(po.isArray());
            //po��һ���������.��������˸�ҳpdf�������᷶Χ.
            PdfArray pa = (PdfArray) po;
            System.out.println(pa.size());
            //����y������ֵ.
            System.out.println(pa.getAsNumber(pa.size()-1));
            //��ʼд���ı�
            over.beginText();
            //��������ʹ�С
            over.setFontAndSize(font.getBaseFont(), 10);
//����������ɫ
            over.setColorFill(BaseColor.RED);
            //������������λ��
            over.setTextMatrix(107, 540);
            //Ҫ�����text
            over.showText("��Ҫ��[�ո�]���� " + i);
            over.endText();
            //����һ��image����.
            Image image = Image.getInstance(FILE_DIR+"6.jpg");
            //����image��������λ��pa.getAsNumber(pa.size()-1).floatValue() �Ǹ�ҳpdf�������y������ֵ
            image.setAbsolutePosition(0,pa.getAsNumber(pa.size()-1).floatValue()-100);//0, 0, 841.92, 595.32
            over.addImage(image);

            //��һ��Ȧ.
            over.setRGBColorStroke(0xFF, 0x00, 0x00);
            over.setLineWidth(5f);
            over.ellipse(250, 450, 350, 550);
            over.stroke();
        }

        stamper.close();

    }
}
