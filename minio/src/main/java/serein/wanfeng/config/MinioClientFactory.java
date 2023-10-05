package serein.wanfeng.config;

import io.minio.MinioClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;

/**
 * @Date: 2023-10-05 19:20
 * @Author: luozh
 * @Description:
 */
@Configuration
public class MinioClientFactory {

    @Resource
    private MinioProperties minioProperties;

    @Bean
    public MinioClient getDefaultMinioClient(){
        return MinioClient.builder()
                .endpoint(minioProperties.getEndpoint())
                .credentials(minioProperties.getRootUserName(), minioProperties.getRootPassword())
                .build();
    }
}
