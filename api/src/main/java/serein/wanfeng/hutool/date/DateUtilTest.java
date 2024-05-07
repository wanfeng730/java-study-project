package serein.wanfeng.hutool.date;

import cn.hutool.core.date.*;
import org.junit.jupiter.api.Test;

import java.util.Calendar;

/**
 * @date: 2024-05-07 15:01
 * @author: luozh
 * @description: hutool DateUtil使用
 */
public class DateUtilTest {

    private static final String WANFENG_BIRTHDAY = "2001-07-30 05:00:00";

    @Test
    public void test() {
        //当前时间
        DateTime date = DateUtil.date();

        //当前时间字符串 yyyy-MM-dd HH:mm:ss
        String now = DateUtil.now();

        //当前时间字符串
        String today = DateUtil.today();

        //Calendar转Date
        DateTime date1 = DateUtil.date(Calendar.getInstance());

        //时间戳转Date
        DateTime date2 = DateUtil.date(System.currentTimeMillis());

        //String转Date（固定格式）
        DateTime dateTime = DateUtil.parse("2024-05-07 15:00:03");
        DateTime dateTime1 = DateUtil.parse("2024.05.07 15:00:00");
        DateTime dateTime2 = DateUtil.parse("20240507150006");
        DateTime dateTime3 = DateUtil.parse("2048-11-21T00:00:00.000Z");
        //String转Date（自定义格式）
        DateTime dateTime4 = DateUtil.parse("2024_05_07-10_05_07", "yyyy_MM_dd-HH_mm_ss");

        //Date转String（固定格式）
        String string = DateUtil.formatDate(date);
        String string3 = DateUtil.formatTime(date);
        String string1 = DateUtil.formatDateTime(date);
        //Date转String（自定义格式）
        String string2 = DateUtil.format(date, "[yyyy-MM-dd]HH:mm:ss");

        //获取日期的年月日、时分秒
        int year = DateUtil.year(date);
        int month = DateUtil.month(date) + 1;   //从0开始的
        Month monthEnum = DateUtil.monthEnum(date);
        int dayOfYear = DateUtil.dayOfYear(date);
        int dayOfMonth = DateUtil.dayOfMonth(date);
        int dayOfWeek = DateUtil.dayOfWeek(date);
        Week weekEnum = DateUtil.dayOfWeekEnum(date);
        int hour = DateUtil.hour(date, true);
        int minute = DateUtil.minute(date);
        int second = DateUtil.second(date);

        //某一个时间段的开始和结束时间
        DateTime dateTime5 = DateUtil.beginOfDay(date);
        DateTime dateTime6 = DateUtil.endOfDay(date);
        DateTime dateTime7 = DateUtil.beginOfMonth(date);
        DateTime dateTime8 = DateUtil.endOfYear(date);

        //时间增减
        DateTime offset = DateUtil.offset(date, DateField.HOUR, 4);
        DateTime offset1 = DateUtil.offset(date, DateField.DAY_OF_MONTH, 34);
        DateTime offsetMonth = DateUtil.offsetMonth(date, 10);
        DateTime offsetDay = DateUtil.offsetDay(date, 100000);
        //时间增减（简化）
        DateTime yesterday = DateUtil.yesterday();
        DateTime tomorrow = DateUtil.tomorrow();
        DateTime lastWeek = DateUtil.lastWeek();
        DateTime nextWeek = DateUtil.nextWeek();
        DateTime lastMonth = DateUtil.lastMonth();
        DateTime nextMonth = DateUtil.nextMonth();

        //时间差计算
        long between = DateUtil.between(yesterday, tomorrow, DateUnit.MS);
        //时间差格式化中文输出（差值必须是毫秒）
        String string4 = DateUtil.formatBetween(between, BetweenFormatter.Level.HOUR);

        //星座
        DateTime birthday = DateUtil.parse(WANFENG_BIRTHDAY);
        int birYear = DateUtil.year(birthday);
        int birMonth = DateUtil.month(birthday);
        int birDay = DateUtil.dayOfMonth(birthday);
        String zodiac = DateUtil.getZodiac(birMonth, birDay);

        //属相
        String chineseZodiac = DateUtil.getChineseZodiac(birYear);

        //计算年龄
        int age = DateUtil.ageOfNow(birthday);

        //是否为闰年
        boolean isLeapYear = DateUtil.isLeapYear(birYear);

        System.out.println(date);
    }
}
