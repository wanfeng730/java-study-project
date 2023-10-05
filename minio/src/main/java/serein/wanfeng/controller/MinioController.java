package serein.wanfeng.controller;

import io.minio.ListBucketsArgs;
import io.minio.ListObjectsArgs;
import io.minio.MinioClient;
import io.minio.Result;
import io.minio.errors.*;
import io.minio.messages.Item;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @Date: 2023-10-05 19:35
 * @Author: luozh
 * @Description:
 */
@RestController
@Slf4j
@RequestMapping("/minio")
public class MinioController {

    @Resource
    private MinioClient minioClient;

    @Value("${minio.bucketName}")
    private String bucketName;

    @GetMapping("/list_object_name")
    public List<String> listObject() throws Exception {
        Iterable<Result<Item>> objectResults = minioClient.listObjects(ListObjectsArgs.builder()
                .bucket(bucketName).build());

        List<String> objectNameList = new ArrayList<>();
        for (Result<Item> objectResult : objectResults) {
            Item item = objectResult.get();
            objectNameList.add(item.objectName());
        }
        return objectNameList;
    }
}
