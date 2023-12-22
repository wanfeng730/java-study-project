package serein.wanfeng.startegy;

import serein.wanfeng.startegy.zombie.NormalZombie;
import serein.wanfeng.startegy.zombie.attack.HitAttack;

/**
 * @date: 2023-12-20 20:24
 * @author: luozh
 * @description: 策略模式
 * @since:
 */
public class StrategyTest {
    public static void main(String[] args) {
        NormalZombie normalZombie = new NormalZombie();
        normalZombie.display();
        normalZombie.move();
        normalZombie.attack();

        normalZombie.setAttackable(new HitAttack());
        normalZombie.attack();
    }
}









