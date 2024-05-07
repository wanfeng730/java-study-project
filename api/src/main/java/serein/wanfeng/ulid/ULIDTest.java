package serein.wanfeng.ulid;

import org.junit.jupiter.api.Test;

/**
 * @date: 2024-05-07 14:35
 * @author: luozh
 * @description:
 * @since:
 */
public class ULIDTest {

    @Test
    public void test() {
        String ulid = ULIDGenerator.generateULID();
        System.out.println(ulid);
    }
}
