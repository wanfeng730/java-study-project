package serein.wanfeng.startegy.zombie.attack;

/**
 * @date: 2023-12-20 20:42
 * @author: luozh
 * @description:
 * @since:
 */
public class HitAttack implements Attackable{

    @Override
    public void attack() {
        System.out.println("撞");
    }
}
