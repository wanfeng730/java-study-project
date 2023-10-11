package serein.wanfeng.minio;

import ch.qos.logback.core.util.FileUtil;
import io.minio.*;
import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import serein.wanfeng.constant.LoginConstant;
import serein.wanfeng.constant.ServerConstant;
import serein.wanfeng.factory.NumberFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

@SpringBootTest
public class MinioApplicationTests {

    private MinioClient minioClient;

    public static final String IMAGE_FILE_NAME = "wallhaven01.jpg";
    public static final String TEXT_FILE_NAME = "笔记.txt";

    public static final String UPLOAD_FOLDER_PATH = "src/test/resources/upload";
    public static final String DOWNLOAD_FOLDER_PATH = "src/test/resources/download";

    @BeforeEach
    public void connectMinio(){
        minioClient = MinioClient.builder().endpoint(ServerConstant.CENTOS_IP_ADDRESS, 9000, false)
                .credentials(LoginConstant.MINIO_ROOT_USERNAME, LoginConstant.MINIO_ROOT_PASSWORD)
                .build();
    }

    /**
     * 本地文件上传
     */
    @Test
    public void uploadFile(){
        try {
            //判断bucket是否存在
            boolean bucketExists = minioClient.bucketExists(BucketExistsArgs.builder().bucket("ectest").build());
            if(!bucketExists){
                minioClient.makeBucket(MakeBucketArgs.builder().bucket("ectest").build());
            }else{
                System.out.println("Bucket[ectest] already exists");
            }

            /**
             * bucket：指定上传的bucket
             * object：上传到minio的路径（到文件名）
             * filename：上传的本地文件路径（到文件名）
             */
            minioClient.uploadObject(UploadObjectArgs.builder()
                    .bucket("ectest")
                    .object(IMAGE_FILE_NAME)
                    .filename(UPLOAD_FOLDER_PATH + File.separator + IMAGE_FILE_NAME).build());

        } catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * 上传流
     * @throws Exception
     */
    @Test
    public void uploadInputStream() throws Exception {
        File file = new File(UPLOAD_FOLDER_PATH + File.separator + IMAGE_FILE_NAME);
        FileInputStream fileInputStream = new FileInputStream(file);

        minioClient.putObject(PutObjectArgs.builder()
                .bucket("ectest")
                .object("image_" + NumberFactory.getRandomNumber(5) + ".jpg" )
                .stream(fileInputStream, fileInputStream.available(), -1)
                .build());

        fileInputStream.close();
    }

    /**
     * 下载文件
     */
    @Test
    public void downloadFile(){
        try {
            //判断bucket是否存在
            boolean bucketExists = minioClient.bucketExists(BucketExistsArgs.builder().bucket("ectest").build());
            if(!bucketExists){
                minioClient.makeBucket(MakeBucketArgs.builder().bucket("ectest").build());
            }else{
                System.out.println("Bucket[ectest] already exists");
            }

            /**
             * bucket：指定上传的bucket
             * object：minio中的文件路径
             * filename：下载到本地的路径（到文件名）
             */
            minioClient.downloadObject(DownloadObjectArgs.builder()
                    .bucket("ectest")
                    .object(IMAGE_FILE_NAME)
                    .filename(DOWNLOAD_FOLDER_PATH + File.separator + "minio_image_" + NumberFactory.getRandomNumber(8) + ".jpg").build());
        }catch (Exception e){
            e.printStackTrace();
        }
    }


    /**
     * 下载流
     */
    @Test
    public void downloadInputStream() throws Exception{
        //获取流
        InputStream inputStream = minioClient.getObject(GetObjectArgs.builder()
                .bucket("ectest")
                .object(IMAGE_FILE_NAME)
                .build());
        File file = new File(DOWNLOAD_FOLDER_PATH + File.separator + "image_aaa.jpg");
        FileUtils.copyInputStreamToFile(inputStream, file);
        inputStream.close();

        //直接把流保存到本地文件
        minioClient.downloadObject(DownloadObjectArgs.builder()
                .bucket("ectest")
                .object(IMAGE_FILE_NAME)
                .filename(DOWNLOAD_FOLDER_PATH + File.separator + "image_bbb.jpg").build());
    }


}
