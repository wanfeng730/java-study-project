package serein.wanfeng.pdf;

import org.icepdf.core.pobjects.Document;
import org.icepdf.core.pobjects.Page;
import org.icepdf.core.util.GraphicsRenderingHints;
import serein.wanfeng.util.FileUtil;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @date: 2024-06-05 14:10
 * @author: luozh
 * @description: pdf文件格式转换工具
 */
public class PdfConvertUtil {
    private static final String JPG_FORMAT = "jpg";
    private static final String JPG_SUFFIX = ".jpg";

    public static List<File> pdfToJpgFiles(String pdfFilePath, String jpgFolderPath) {
        Document iceDocument = new Document();
        try {
            iceDocument.setFile(pdfFilePath);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        //缩放比例
        float scale = 2.5f;
        //旋转角度
        float rotation = 0f;

        List<File> jpgFileList = new ArrayList<>();
        String pdfFilenameNoSuffix = FileUtil.getFilenameNoSuffix(pdfFilePath);
        for (int i = 0; i < iceDocument.getNumberOfPages(); i++) {
            String jpgFilePath = jpgFolderPath + File.separator + pdfFilenameNoSuffix + "_img_" + i + JPG_SUFFIX;
            BufferedImage bufferedImage = null;
            try {
                bufferedImage = (BufferedImage) iceDocument.getPageImage(i, GraphicsRenderingHints.SCREEN, Page.BOUNDARY_CROPBOX, rotation, scale);
                File jpgFile = new File(jpgFilePath);
                ImageIO.write(bufferedImage, JPG_FORMAT, jpgFile);
                if (jpgFile.isFile() && jpgFile.exists()) {
                    jpgFileList.add(jpgFile);
                }
            } catch (IOException e) {
                System.out.println("pdf转jpg时写入文件失败！异常信息：");
            } catch (InterruptedException e) {
                System.out.println("pdf读取文件异常");
            } catch (Exception e) {
                System.out.println("未知异常");
            }

            if (Objects.nonNull(bufferedImage)) {
                bufferedImage.flush();
            }
        }
        iceDocument.dispose();
        return jpgFileList;
    }
}
