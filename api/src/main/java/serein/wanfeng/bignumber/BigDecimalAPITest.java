package serein.wanfeng.bignumber;

import org.junit.Test;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.NumberFormat;

/**
 * @date: 2023-11-29 20:12
 * @author: luozh
 * @description:
 * @since:
 */
public class BigDecimalAPITest {

    @Test
    public void testBigDecimal(){
        //构造
        BigDecimal number = BigDecimal.valueOf(7);
        BigDecimal number2 = new BigDecimal("3.224");

        //加法
        BigDecimal addNumber = number.add(BigDecimal.valueOf(3));

        //减法
        BigDecimal subNumber = number.subtract(BigDecimal.valueOf(6));

        //乘法
        BigDecimal mulNumber = number.multiply(BigDecimal.valueOf(2));

        //除法
        BigDecimal divNumber = number.divide(BigDecimal.valueOf(3), 2, RoundingMode.HALF_UP);

        //绝对值
        BigDecimal absNumber = number.abs();

        //转换成数据类型
        int i = number.intValue();
        long l = number.longValue();
        float v = number.floatValue();
        double d = number.doubleValue();

        //比较（0相等、1大于、-1小于）
        int comp = number.compareTo(number2);

        //字符输出
        String numberString = number.toPlainString();

        //返回较大值
        BigDecimal maxNumber = number.max(number2);

        //相反数
        BigDecimal negateNumber = number.negate();

        //乘方
        BigDecimal powNumber = number.pow(3);

        //小数舍入模式
        BigDecimal bigDecimal = divNumber.setScale(2);//保留两位小数，默认四舍五入
        BigDecimal bigDecimal1 = divNumber.setScale(2, RoundingMode.DOWN);  //删除多余小数位  2.236 -> 2.23
        BigDecimal bigDecimal2 = divNumber.setScale(2, RoundingMode.UP);    //直接进位  2.236 -> 2.24
        BigDecimal bigDecimal3 = divNumber.setScale(2, RoundingMode.HALF_UP);   //四舍五入 5向上进位   2.235 -> 2.24
        BigDecimal bigDecimal4 = divNumber.setScale(2, RoundingMode.HALF_DOWN); //四舍五入 5向下舍去   2.235 -> 2.23

        //金额格式化
        NumberFormat currencyFormat = NumberFormat.getCurrencyInstance();
        currencyFormat.setMinimumFractionDigits(1);
        currencyFormat.setMaximumFractionDigits(1); //设置固定小数位 四舍五入
        BigDecimal moneyNumber = new BigDecimal("2345862834.15");
        System.out.println(currencyFormat.format(moneyNumber));


        //百分比格式化
        NumberFormat percentFormat = NumberFormat.getPercentInstance();
        percentFormat.setMinimumFractionDigits(2);
        percentFormat.setMaximumFractionDigits(2);  //设置固定小数位 五舍六入
        BigDecimal percentNumber = new BigDecimal("0.64526");
        System.out.println(percentFormat.format(percentNumber));
    }
}
