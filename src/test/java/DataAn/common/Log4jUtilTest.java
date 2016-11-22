package DataAn.common;

import DataAn.common.utils.Log4jUtil;

public class Log4jUtilTest {

	@org.junit.Test
	public void testString() {
        String debug = "debug信息";
        Log4jUtil.getInstance().getLogger(Log4jUtilTest.class).debug(debug);
        String warn = "warn信息";
        Log4jUtil.getInstance().getLogger(Log4jUtilTest.class).warn(warn);
        String info = "info信息";
        Log4jUtil.getInstance().getLogger(Log4jUtilTest.class).info(info);
        String error = "error信息";
        Log4jUtil.getInstance().getLogger(Log4jUtilTest.class).error(error);
        String fatal = "fatal信息";
    }
	public static void main(String[] args) {
		String debug = "debug信息";
        Log4jUtil.getInstance().getLogger(Log4jUtilTest.class).debug(debug);
        String warn = "warn信息";
        Log4jUtil.getInstance().getLogger(Log4jUtilTest.class).warn(warn);
        String info = "info信息";
        Log4jUtil.getInstance().getLogger(Log4jUtilTest.class).info(info);
        String error = "error信息";
        Log4jUtil.getInstance().getLogger(Log4jUtilTest.class).error(error);
        String fatal = "fatal信息";
	}
}
