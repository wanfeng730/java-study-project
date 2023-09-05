package serein.wanfeng.loadbalance;

import serein.wanfeng.common.URL;

import java.util.List;
import java.util.Random;

/**
 * @Date: 2023-09-05 20:32
 * @Author: luozh
 * @Description: 负载均衡策略
 */

public class LoadBalanceStrategy {

    public static URL random(List<URL> urlList){
        Random random = new Random();
        int i = random.nextInt(urlList.size());
        return urlList.get(i);
    }
}
