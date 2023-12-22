package serein.wanfeng.prototype;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @date: 2023-12-20 14:53
 * @author: luozh
 * @description: 原型模式
 * @since:
 */
public class ProtoTypeTest {

    public static void main(String[] args) {
        User u1 = new User(1L, "wanfeng", new Role(2L, "role_type1"));
        User u2 = u1.clone();
        System.out.println();
    }

}

/**
 * 实现Cloneable接口的clone方法，可以通过该方法拿到该对象的拷贝
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
class User implements Cloneable{
    //基本数据类型和String只需要浅拷贝
    private long id;
    private String name;
    //对象类型（可变类型）需要深拷贝，即递归拷贝
    private Role role;

    @Override
    public User clone() {
        try {
            User cloneUser = (User) super.clone();
            // User已经克隆成功，但是属性中的对象仍然是未被克隆（共享同一个地址）
            //需要深层次的拷贝
            cloneUser.setRole(role.clone());
            return cloneUser;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }
}

@Data
@AllArgsConstructor
@NoArgsConstructor
class Role implements Cloneable{
    private long id;
    private String roleName;


    @Override
    public Role clone() {
        try {
            Role cloneRole = (Role) super.clone();
            // TODO: copy mutable state here, so the clone can't change the internals of the original
            return cloneRole;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }
}
