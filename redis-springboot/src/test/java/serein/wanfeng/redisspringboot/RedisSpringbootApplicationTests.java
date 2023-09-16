package serein.wanfeng.redisspringboot;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

import javax.annotation.Resource;
import java.util.List;
import java.util.Set;

@SpringBootTest
class RedisSpringbootApplicationTests {

	@Resource
	private RedisTemplate<String, String> redisTemplate;

	@Test
	public void test_redis_String(){
		redisTemplate.opsForValue().set("name", "wanfeng");

		System.out.println(redisTemplate.opsForValue().get("name"));

		redisTemplate.delete("name");

		System.out.println(redisTemplate.opsForValue().get("name"));

		redisTemplate.opsForValue().set("20230917key", "redis终于连接上了，原来是bind没有修改为0000");
	}

	@Test
	public void test_redis_List(){
		redisTemplate.opsForList().leftPush("list1", "1");
		redisTemplate.opsForList().leftPush("list1", "2");
		redisTemplate.opsForList().leftPush("list1", "3");

		List<String> list = redisTemplate.opsForList().range("list1", 0, -1);
		System.out.println(list);

		assert list != null;
		for (String value : list) {
			System.out.println(redisTemplate.opsForList().leftPop("list1"));
		}
	}

	@Test
	public void test_redis_Set(){
		redisTemplate.opsForSet().add("set1", "1", "2", "2");

		Set<String> set1 = redisTemplate.opsForSet().members("set1");
		System.out.println(set1);
	}

	@Test
	public void test_redis_Hash(){
		redisTemplate.opsForHash().put("hash1", "archiveNo", "A001");
		redisTemplate.opsForHash().put("hash1", "title", "hangzhouyayunhui");

		System.out.println(redisTemplate.opsForHash().get("hash1", "title"));
	}

	@Test
	public void test_redis_ZSet(){
		redisTemplate.opsForZSet().add("zset1", "wanfeng", 178);
		redisTemplate.opsForZSet().add("zset1", "lzh", 65);
		redisTemplate.opsForZSet().add("zset1", "serein", 90);

		Long rank = redisTemplate.opsForZSet().rank("zset1", "serein");
		System.out.println(rank);

		Long reverseRank = redisTemplate.opsForZSet().reverseRank("zset1", "wanfeng");
		System.out.println(reverseRank);

	}

}
