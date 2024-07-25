package serein.wanfeng;

import cn.hutool.core.io.FileUtil;
import org.junit.Test;

import java.io.File;

/**
 * @date: 2024-06-13 15:49
 * @author: luozh
 * @description:
 * @since:
 */
public class WanfengTest {

    @Test
    public void testSeas() {
        System.out.println(getFilePathByBlobId(103513582L));
        System.out.println(getFilePathByBlobId(103513583L));
    }

    private static String getFilePathByBlobId(long blobId) {
        return (blobId >>> 56 & 0xFF) +
                "/" + (blobId >>> 48 & 0xFF) +
                "/" + (blobId >>> 40 & 0xFF) +
                "/" + (blobId >>> 32 & 0xFF) +
                "/" + (blobId >>> 24 & 0xFF) +
                "/" + (blobId >>> 16 & 0xFF) +
                "/" + (blobId >>> 8 & 0xFF) +
                "/" + (blobId & 0xFF);
    }

    @Test
    public void test0704() {
        try {
            System.out.println("try");
            int c = 1 / 0;
        } catch (Exception e) {
            System.out.println("catch");
            int c = 1 / 0;
            throw new RuntimeException(e);
        } finally {
            System.out.println("finally");
        }
    }

    @Test
    public void test0709() {
        System.out.printf("%03d", 12);
    }

    @Test
    public void test() {
        File file = new File("/Users/wanfeng/Desktop/new-file");
        File newFile = FileUtil.rename(file, "new-file.txt", true);
        System.out.println();
    }

}
