package serein.wanfeng.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @Date: 2023-09-05 17:03
 * @Author: luozh
 * @Description:
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Invocation implements Serializable {
    /**
     * 调用接口名
     */
    private String interfaceName;
    /**
     * 调用方法名
     */
    private String methodName;
    /**
     * 参数类型列表
     */
    private Class[] parameterTypes;
    /**
     * 参数列表
     */
    private Object[] parameters;

}
