package serein.wanfeng.factory;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;

/**
 * @Date: 2023-08-18 14:28
 * @Author: luozh
 * @Description:
 */

public class NumberFactory {

    public static final DateTimeFormatter LONG_DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");


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


    public static String getCurrentDateTimeNumber(){
        return LONG_DATE_TIME_FORMATTER.format(LocalDateTime.now());
    }

}
