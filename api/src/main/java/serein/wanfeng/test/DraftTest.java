package serein.wanfeng.test;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Date: 2023-09-06 16:49
 * @Author: luozh
 * @Description:
 */

public class DraftTest {
    @Test
    public void test(){
        List<String> idList = Arrays.asList("1", "3", "2");
        List<String> sortIdList = idList.stream().sorted((o1, o2) -> - o1.compareTo(o2)).collect(Collectors.toList());
        System.out.println(sortIdList);
    }
}
