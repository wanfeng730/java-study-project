package serein.wanfeng.minio;

import io.minio.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import serein.wanfeng.constant.LoginConstant;
import serein.wanfeng.constant.ServerConstant;
import serein.wanfeng.factory.NumberFactory;

import java.io.File;

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


}
