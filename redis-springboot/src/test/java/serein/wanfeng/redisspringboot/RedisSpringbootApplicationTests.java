package serein.wanfeng.redisspringboot;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

import javax.annotation.Resource;

@SpringBootTest
class RedisSpringbootApplicationTests {

	@Resource
	private RedisTemplate<String, String> redisTemplate;

	@Test
	public void test1(){
		redisTemplate.opsForValue().set("name", "wanfeng");

		System.out.println(redisTemplate.opsForValue().get("name"));

		redisTemplate.delete("name");

		System.out.println(redisTemplate.opsForValue().get("name"));

		redisTemplate.opsForValue().set("20230917key", "redis终于连接上了，原来是bind没有修改为0000");
	}

}
