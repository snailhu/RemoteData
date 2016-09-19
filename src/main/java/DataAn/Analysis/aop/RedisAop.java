package DataAn.Analysis.aop;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import DataAn.Analysis.dto.YearAndParamDataDto;
import DataAn.Analysis.redis.RedisPoolUtil;
import redis.clients.jedis.Jedis;

@Component
public class RedisAop {


	private final static Log log =  LogFactory.getLog(RedisAop.class);
	
//	@Pointcut("execution(* DataAn.mongo.db.MongodbUtil.get*(..))")
//	public void aspect(){}
	
	public YearAndParamDataDto getData(ProceedingJoinPoint joinPoint) throws Throwable{
		Object[] args = joinPoint.getArgs();
	    if (args != null && args.length > 0 ) {
	    	Jedis jedis = RedisPoolUtil.buildJedisPool().getResource(); 
			String  start_year = ((String[])args[1])[0];
			String  end_year = ((String[])args[1])[1];
			String key = start_year+end_year;
			String paramName = ((String[])args[1])[2];
			if(jedis.exists((key+"year"+paramName).getBytes()) && jedis.exists((key+"param"+paramName).getBytes())){
				List<String> year_list = RedisPoolUtil.getList(key+"year"+paramName);
				List<String> parm_list = RedisPoolUtil.getList(key+"param"+paramName);
				YearAndParamDataDto result =  new YearAndParamDataDto();
				result.setParamValue(parm_list);
				result.setYearValue(year_list);
				return result;	
			}   
        }
		
	        YearAndParamDataDto returnValue = (YearAndParamDataDto) joinPoint.proceed(args);

			return returnValue;
	
		
	}
}
