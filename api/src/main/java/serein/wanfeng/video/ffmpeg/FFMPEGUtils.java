package serein.wanfeng.video.ffmpeg;

import org.bytedeco.javacv.FFmpegFrameGrabber;
import org.bytedeco.javacv.Frame;
import org.bytedeco.javacv.FrameGrabber;
import org.bytedeco.javacv.Java2DFrameConverter;
import sun.misc.BASE64Encoder;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.Optional;

/**
 * @date: 2024-01-18 15:36
 * @author: luozh
 * @description:
 * @since:
 */
public class FFMPEGUtils {

    /**
     * 截取视频获得指定帧的图片
     *
     * @param videoFile   源视频文件（可用inputStream替代）
     * @return base64
     */
    public static String fetchVideoFirstFrame2Base64(File videoFile) {
        //若无视频文件或该文件为一个文件夹，直接返回
        if(videoFile == null || videoFile.isDirectory()){
            return null;
        }
        //FFmpegFrameGrabber 是可自动关闭的
        try(FFmpegFrameGrabber frameGrabber = new FFmpegFrameGrabber(videoFile)) {
            frameGrabber.start();
            int length = frameGrabber.getLengthInFrames();

            int i = 0;
            Frame frame = null;
            while (i < length) {
                // 过滤前5帧，避免出现全黑的图片，依自己情况而定
                frame = frameGrabber.grabFrame();
                if ((i > 5) && (frame.image != null)) {
                    break;
                }
                i++;
            }

            //默认图片格式jpg
            String imgSuffix = "jpg";

            Java2DFrameConverter converter =new Java2DFrameConverter();
            BufferedImage srcBi =converter.getBufferedImage(frame);
            int owidth = srcBi.getWidth();
            int oheight = srcBi.getHeight();
            // 对截取的帧进行等比例缩放
            int width = 800;
            int height = (int) (((double) width / owidth) * oheight);

            BufferedImage bufferImage = new BufferedImage(width, height, BufferedImage.TYPE_3BYTE_BGR);
            bufferImage.getGraphics().drawImage(srcBi.getScaledInstance(width, height, Image.SCALE_SMOOTH),0, 0, null);
            ByteArrayOutputStream imageByteOutPutStream = new ByteArrayOutputStream();
            //将图片写入到字节流/目标文件中
            ImageIO.write(bufferImage, imgSuffix, imageByteOutPutStream);
            //字节流提取字节数组
            byte[] bytes = imageByteOutPutStream.toByteArray();
            //字节数组转换为base64编码
            BASE64Encoder base64Encoder = new BASE64Encoder();
            String base64 = base64Encoder.encodeBuffer(bytes).trim();
            //删除无用转义符
            return base64.replaceAll("\n", "").replaceAll("\r", "");
        }catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
