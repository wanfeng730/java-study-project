package serein.wanfeng.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @Date: 2023-09-05 20:22
 * @Author: luozh
 * @Description:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class URL implements Serializable {
    static final long serialVersionUID = 77766621L;

    private String hostname;
    private Integer port;
}
