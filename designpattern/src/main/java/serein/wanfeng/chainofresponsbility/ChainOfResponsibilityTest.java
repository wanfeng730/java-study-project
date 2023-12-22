package serein.wanfeng.chainofresponsbility;

import lombok.Builder;
import lombok.Data;

/**
 * @date: 2023-12-20 21:42
 * @author: luozh
 * @description: 责任链模式
 * @since:
 */
public class ChainOfResponsibilityTest {
    public static void main(String[] args) {
        UserRequest request = new UserRequest.UserRequestBuilder()
                .frequentOk(true)
                .loginSuccess(true)
                .build();
        FrequentHandler frequentHandler = new FrequentHandler(new LoginHandler(null));
        frequentHandler.process(request);
    }
}



@Data
abstract class UserRequestHandler {
    UserRequestHandler next;

    public UserRequestHandler(UserRequestHandler next){
        this.next = next;
    }

    abstract boolean process(UserRequest request);
}

class FrequentHandler extends UserRequestHandler{

    public FrequentHandler(UserRequestHandler next) {
        super(next);
    }

    @Override
    boolean process(UserRequest request) {
        //若处理通过，进行下一个节点的处理，若没有下一个节点返回true
        if(request.isFrequentOk()){
            UserRequestHandler next1 = getNext();
            if(next1 == null){
                System.out.println("请求成功");
                return true;
            }
            return next1.process(request);
        }
        System.out.println("访问频率验证失败");
        return false;
    }
}

class LoginHandler extends UserRequestHandler{

    public LoginHandler(UserRequestHandler next) {
        super(next);
    }

    @Override
    boolean process(UserRequest request) {
        //若处理通过，进行下一个节点的处理，若没有下一个节点返回true
        if(request.isLoginSuccess()){
            UserRequestHandler next1 = getNext();
            if(next1 == null){
                System.out.println("请求成功～");
                return true;
            }
            return next1.process(request);
        }
        System.out.println("登录验证失败");
        return false;
    }
}

@Data
@Builder
class UserRequest{
    private boolean frequentOk;
    private boolean loginSuccess;
}