package DataAn.common;

import DataAn.common.utils.MailUtil;

public class MailTest {
	public static void main(String[] args) {
		MailUtil.sendSimpleMail("smtp.163.com", "wjzy2752@163.com", "wj@fengyuntec.com", "wjzy2752@163.com",
				"xiaoli19881213", " 测试我的简单邮件发送机制！！ ", " 测试简单文本邮件发送！ ");
	}
}
