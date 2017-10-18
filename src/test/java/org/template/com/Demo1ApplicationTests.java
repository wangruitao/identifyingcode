package org.template.com;

import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;




@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Demo1Application.class)
@WebAppConfiguration
public class Demo1ApplicationTests {

	/*@Test
	public void contextLoads() {
	}

	@Autowired
	private StringRedisTemplate stringRedisTemplate;

	@Autowired
	private RedisTemplate redisTemplate;

	@Test
	public void test() throws Exception {
		stringRedisTemplate.opsForValue().set("aaa", "111");
		RedisOperations<String, String> redis = redisTemplate.opsForList().getOperations();
		Set<String> keys = redis.keys("*");
		for(String key : keys) {
			redis.delete(key);
		}
		Assert.assertEquals("111", stringRedisTemplate.opsForValue().get("aaa"));
	}
	
    @Test
    public void testObj() throws Exception {
        Users user=new Users();
        user.setLogName("ceshi");
        user.setName("测试");
        user.setUserLevel(1);
        user.setUserLoc(0);
        user.setUserPassword("asdfsafsdf");
        ValueOperations<String, Users> operations=redisTemplate.opsForValue();
        operations.set("com.neox", user);
        operations.set("com.neo.f", user,1,TimeUnit.SECONDS);
        Thread.sleep(1000);
        //redisTemplate.delete("com.neo.f");
        boolean exists=redisTemplate.hasKey("com.neo.f");
        if(exists){
            System.out.println("exists is true");
        }else{
            System.out.println("exists is false");
        }
       // Assert.assertEquals("aa", operations.get("com.neo.f").getUserName());
    }*/

}
