package serein.wanfeng.startegy.zombie.attack;

/**
 * @date: 2023-12-20 20:36
 * @author: luozh
 * @description:
 * @since:
 */
public class BiteAttack implements Attackable {

    @Override
    public void attack() {
        System.out.println("咬");
    }
}