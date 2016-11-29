package redis;

import java.util.Date;

import DataAn.common.utils.DateUtil;

public class TokenTest {

	private static volatile Date token = DateUtil.format("2016-11-28 20:00:00");
	
	public static Date getToken(){
		Date nowDate = new Date();
		if((nowDate.getSeconds() - token.getSeconds()) >= 20){
			synchronized (TokenTest.class){
				
			}
		}
		return token;
	}
}
