package serein.wanfeng.video;

import org.junit.Test;
import serein.wanfeng.video.ffmpeg.FFMPEGUtils;

import java.io.File;
import java.io.IOException;

/**
 * @date: 2024-01-18 15:35
 * @author: luozh
 * @description:
 * @since:
 */
public class VideoTest {

    @Test
    public void test1() throws IOException {
        String videoFilePath = "/Users/wanfeng/Desktop/file/Java/MyProjects/java-study-project/api/src/main/resources/videofile/v14-兰溪数据修复接口-卷门类.mp4";

        File videoFile = new File(videoFilePath);

        String base64 = FFMPEGUtils.fetchVideoFirstFrame2Base64(videoFile);
        System.out.println();
    }
}
