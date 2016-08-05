package DataAn.Util;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;

public class EhCache {
	
	 private static final CacheManager cacheManager = CacheManager.create();  ;  
	    private Cache cache;  
	    public EhCache(){  
	        this.cache=cacheManager.getCache("ehcache")  ;
	    }  
	    public EhCache(String cacheName){  
	        this.cache=cacheManager.getCache(cacheName)  ;
	    } 
	    public Cache getCache() {  
	        return cache;  
	    }  
	  
	    public void setCache(Cache cache) {  
	        this.cache = cache;  
	    }  
	  	    
	        /* 
	     * 通过名称从缓存中获取数据 
	     */  
	    public  Object getCacheElement(String cacheKey) throws Exception {  
	            net.sf.ehcache.Element e = cache.get(cacheKey);  
	        if (e == null) {  
	            return null;  
	        }  
	        return e.getValue();  
	    }  
	    /* 
	     * 将对象添加到缓存中 
	     */  
	    public void addToCache(String cacheKey, Object result) throws Exception {  
	        Element element = new Element(cacheKey, result);  
	        cache.put(element);  
	    }  
	  
}
