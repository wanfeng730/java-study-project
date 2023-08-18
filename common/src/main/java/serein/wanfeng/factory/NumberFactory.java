package serein.wanfeng.factory;

import java.util.Random;

/**
 * @Date: 2023-08-18 14:28
 * @Author: luozh
 * @Description:
 */

public class NumberFactory {

    /**
     * 生成指定位数的随机数字符串
     * @param length
     * @return
     */
    public static String getRandomNumber(int length)
    {
        Random random = new Random();
        return String.valueOf(random.nextLong()).substring(1, length + 1);
    }

}
