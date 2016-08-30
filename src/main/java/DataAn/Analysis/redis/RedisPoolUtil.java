package DataAn.Analysis.redis;

import java.util.ArrayList;
import java.util.List;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.io.*; 

public class RedisPoolUtil {
	
	private static JedisPool jedisPool = null;

	
	public static JedisPool buildJedisPool(){  
		if(jedisPool ==null){
			 JedisPoolConfig config = new JedisPoolConfig();  
			  	config.setMaxTotal(-1);  
		        config.setMinIdle(50);  
		        config.setMaxIdle(3000);  
		        config.setMaxWaitMillis(10000); 
		        config.setTestOnBorrow(true);  
		        jedisPool = new JedisPool(config, "127.0.0.1", 6379);  
		}
        return jedisPool;  
    }
	
	 public static void returnResource(JedisPool pool, Jedis redis) {  
	        if (redis != null) {  
	            pool.returnResource(redis);  
	        }  
	    }  
	
	
	public static <T>  void saveList(String key ,List<T> value){
		Jedis jedis = null;
		 try {
			jedisPool = buildJedisPool();
			jedis = jedisPool.getResource();  	        
			jedis.set(key.getBytes(), ObjectsTranscoder.serialize(value));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			jedisPool.returnBrokenResource(jedis);  
	            e.printStackTrace();  
		}finally {  
            //返还到连接池  
            returnResource(jedisPool, jedis);  
        }    
		
	}
	
	
	
	public static <T> List<T> getList(String key){
		Jedis jedis = null;
		List<T> list = null;
		 try {
			jedisPool = buildJedisPool();
			jedis = jedisPool.getResource();  	        
			byte[] in = jedis.get(key.getBytes());  
			list= ObjectsTranscoder.deserialize(in); 
			
		} catch (Exception e) {
			jedisPool.returnBrokenResource(jedis);  
            e.printStackTrace();  
		}finally{
			 returnResource(jedisPool, jedis); 
		}
		 return list;
		
	}
	
	
	public static void close(Closeable closeable) {  
        if (closeable != null) {  
            try {  
                closeable.close();  
            } catch (Exception e) {  
                System.out.println(e);
            }  
        }  
    }  
	
	static class ObjectsTranscoder{  
		//list序列化		
		public static <T> byte[] serialize(List<T> value) {  
            if (value == null) {  
                throw new NullPointerException("Can't serialize null");  
            }  
            byte[] rv=null;  
            ByteArrayOutputStream bos = null;  
            ObjectOutputStream os = null;  
            try {  
                bos = new ByteArrayOutputStream();  
                os = new ObjectOutputStream(bos);  
                for(T t : value){  
                    os.writeObject(t);  
                }  
                os.writeObject(null);  
                os.close();  
                bos.close();  
                rv = bos.toByteArray();  
            } catch (IOException e) {  
                throw new IllegalArgumentException("Non-serializable object", e);  
            } finally {  
                close(os);  
                close(bos);  
            }  
            return rv;  
        }  
			
		//反序列化
		
		  public static <T> List<T> deserialize(byte[] in) {  
	            List<T> list = new ArrayList<T>();  
	            ByteArrayInputStream bis = null;  
	            ObjectInputStream is = null;  
	            try {  
	                if(in != null) {  
	                    bis=new ByteArrayInputStream(in);  
	                    is=new ObjectInputStream(bis);  
	                    while (true) {  
	                        T t = (T) is.readObject();  
	                        if(t == null){  
	                            break;   	
	                        }else{  
	                            list.add(t);  
	                        }  
	                    }  
	                    is.close();  
	                    bis.close();  
	                }  
	            } catch (IOException e) {  
	            	System.out.println(e);  
	            } catch (ClassNotFoundException e) {  
	            	System.out.println(e);
	            } finally {  
	                close(is);  
	                close(bis);  
	            }  
	            return list;  
	        }  	   	   		
	}
}
