package serein.wanfeng.bignumber;

import org.junit.Test;

import java.math.BigInteger;

/**
 * @date: 2023-11-29 20:06
 * @author: luozh
 * @description:
 * @since:
 */
public class BigIntegerAPITest {

    @Test
    public void testBigInteger(){
        BigInteger number = BigInteger.valueOf(6);

        //加法
        BigInteger addNumber = number.add(BigInteger.valueOf(2));
        System.out.println(addNumber);

        //减法
        BigInteger subNumber = number.subtract(BigInteger.valueOf(1));
        System.out.println(subNumber);

        //乘法
        BigInteger mulNumber = number.multiply(BigInteger.valueOf(4));
        System.out.println(mulNumber);

        //除法
        BigInteger divNumber = number.divide(BigInteger.valueOf(4));
        System.out.println(divNumber);
    }
}
