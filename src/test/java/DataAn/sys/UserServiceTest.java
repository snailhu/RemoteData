package DataAn.sys;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.junit.Test;

public class UserServiceTest {

	@Test
	public void test(){
		Map<String,String> map = new HashMap<String,String>();
		map.put("shenwp", "shenwp");
		map.put("admin", "admin");
		map.put("root", "root");
		Set<String> set = map.keySet();
		System.out.println(set.contains(null));
	}
}
