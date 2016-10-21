package DataAn.Api;

import java.io.InputStream;


public interface DataWashing {
	
	/**
	 * 读取文件流过滤异常点
	 * @author snailHU
	 * @param
	 */
	public void CSVFileDataWash(InputStream in) throws Exception;
	
}
