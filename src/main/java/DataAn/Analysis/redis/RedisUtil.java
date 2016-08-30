package DataAn.Analysis.redis;

import redis.clients.jedis.Jedis;

public class RedisUtil {

	private RedisUtil(){}
	
	public static  Jedis REIIS = null;
	
	
	public static Jedis   getRedis(){
		
		if(REIIS==null){
			REIIS = new Jedis("192.168.0.44",6379);
		}
		return REIIS;
	}
}
