package serein.wanfeng.startegy.zombie;


import lombok.Data;
import serein.wanfeng.startegy.zombie.attack.Attackable;
import serein.wanfeng.startegy.zombie.move.Movable;

/**
 * @date: 2023-12-20 20:33
 * @author: luozh
 * @description:
 * @since:
 */
@Data
public abstract class Zombie {

    Movable movable;
    Attackable attackable;

    public Zombie(Movable movable, Attackable attackable) {
        this.movable = movable;
        this.attackable = attackable;
    }

    abstract public void display();

    abstract public void move();

    abstract public void attack();
}
