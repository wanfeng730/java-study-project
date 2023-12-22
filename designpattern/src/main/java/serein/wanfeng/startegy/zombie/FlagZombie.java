package serein.wanfeng.startegy.zombie;

import serein.wanfeng.startegy.zombie.attack.Attackable;
import serein.wanfeng.startegy.zombie.attack.BiteAttack;
import serein.wanfeng.startegy.zombie.move.Movable;
import serein.wanfeng.startegy.zombie.move.WalkMove;

/**
 * @date: 2023-12-20 20:38
 * @author: luozh
 * @description:
 * @since:
 */
public class FlagZombie extends Zombie{

    public FlagZombie(){
        super(new WalkMove(), new BiteAttack());
    }

    public FlagZombie(Movable movable, Attackable attackable) {
        super(movable, attackable);
    }

    @Override
    public void display() {
        System.out.println("旗手僵尸");
    }

    @Override
    public void move() {
        movable.move();
    }

    @Override
    public void attack() {
        attackable.attack();
    }
}
