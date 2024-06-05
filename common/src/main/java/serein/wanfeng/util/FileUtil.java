package serein.wanfeng.util;

/**
 * @date: 2024-06-05 14:18
 * @author: luozh
 * @description:
 * @since:
 */
public class FileUtil {

    public static String getFilenameNoSuffix(String filePath) {
        String filename = cn.hutool.core.io.FileUtil.getName(filePath);
        return filename.substring(0, filename.indexOf("."));
    }
}
