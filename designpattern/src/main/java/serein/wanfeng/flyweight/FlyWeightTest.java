package serein.wanfeng.flyweight;

import com.sun.org.glassfish.external.statistics.Stats;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @date: 2023-12-20 15:34
 * @author: luozh
 * @description: 享元模式
 * @since:
 */
public class FlyWeightTest {
    public static void main(String[] args) {

            TreeNode node1 = new TreeNode(1, 2, TreeContainer.getTree("red"));
            TreeNode node2 = new TreeNode(2, 2, TreeContainer.getTree("red"));

    }
}

class TreeContainer{
    private static Map<String, Tree> treeMap  =new ConcurrentHashMap<>();

    public static Tree getTree(String type){
        if(treeMap.containsKey(type)){
            return treeMap.get(type);
        }
        System.out.println("创建一个树，类型为"+type);
        AppleTree tree = new AppleTree(type + "_type", "1");
        putTree(type, tree);
        return tree;
    }

    private static void putTree(String type, Tree tree){
        treeMap.put(type, tree);
    }
}

@Data
@AllArgsConstructor
class TreeNode{
    private int x;
    private int y;
    private Tree tree;
}

@Data
@AllArgsConstructor
class AppleTree implements Tree {
    private final String color;
    private final String height;
}

interface Tree{
}
