package serein.wanfeng;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * @date: 2024-06-13 15:49
 * @author: luozh
 * @description:
 * @since:
 */
public class WanfengTest {

    @Test
    public void testNull() {
        Map<String, Object> map = new HashMap<>();
        map.put("TOTAL", 1L);
        Long total = (Long) map.get("total");
        if (total > 0) {
            System.out.println("》0");
        }
    }

}
