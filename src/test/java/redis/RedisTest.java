package redis;

import redis.clients.jedis.Jedis;

public class RedisTest {
	 public static void main(String[] args) {
	      //连接本地的 Redis 服务
	      Jedis jedis = new Jedis("192.168.0.61",6379);
	      System.out.println("Connection to server sucessfully");
	      //查看服务是否运行
	      System.out.println("Server is running: "+jedis.ping());
	      jedis.set("w3ckey", "Redis tutorial");
	      // 获取存储的数据并输出
	      System.out.println("Stored string in redis:: "+ jedis.get("w3ckey"));
	 }
}
