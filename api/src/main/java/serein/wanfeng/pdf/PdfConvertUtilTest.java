package serein.wanfeng.pdf;

import org.junit.Test;

import java.io.File;
import java.util.List;

/**
 * @date: 2024-06-05 14:25
 * @author: luozh
 * @description:
 * @since:
 */
public class PdfConvertUtilTest {

    @Test
    public void testConvertJpg() {
        String pdfFilePath = "/Users/wanfeng/Desktop/多页花名册.pdf";
        String jpgFolderPath = "/Users/wanfeng/Desktop/0605pdf转jpg文件目录";
        List<File> files = PdfConvertUtil.pdfToJpgFiles(pdfFilePath, jpgFolderPath);
        for (int i = 0; i < files.size(); i++) {
            System.out.println("pdf识别出文件" + i + ": " + files.get(i).getPath());
        }
    }

}
