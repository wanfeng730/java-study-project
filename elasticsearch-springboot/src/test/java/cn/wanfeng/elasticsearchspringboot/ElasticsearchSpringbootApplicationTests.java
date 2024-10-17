package cn.wanfeng.elasticsearchspringboot;

import cn.wanfeng.elasticsearchspringboot.object.ItemDetailDO;
import cn.wanfeng.elasticsearchspringboot.request.ItemDetailDOIndexRequest;
import jakarta.annotation.Resource;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest(classes = ElasticsearchSpringbootApplicationTests.class)
@RunWith(JUnit4.class)
public class ElasticsearchSpringbootApplicationTests {

    @Resource
    private ItemDetailDOIndexRequest itemDetailDOIndexRequest;


    @Test
    public void test() {
        List<ItemDetailDO> itemDetailDO = itemDetailDOIndexRequest.findItemDetailDO();
        System.out.println();
    }

}
