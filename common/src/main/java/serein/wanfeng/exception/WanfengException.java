package serein.wanfeng.exception;

/**
 * @Date: 2023-07-12 14:48
 * @Author: luozh
 * @Description: 异常
 */

public class WanfengException extends RuntimeException{
    static final long serialVersionUID = 1;

    public WanfengException(String message){
        super(message);
    }
}
