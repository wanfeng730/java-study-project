package serein.wanfeng.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @Date: 2023-10-05 19:18
 * @Author: luozh
 * @Description:
 */
@Data
@Component
@ConfigurationProperties(prefix = "minio")
public class MinioProperties {

    private String endpoint;
    private String rootUserName;
    private String rootPassword;
    private String bucketName;
}
