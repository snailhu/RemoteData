package DataAn.jfreechart.service;

import java.util.Map;
import java.util.Vector;

import DataAn.jfreechart.chart.Serie;
import DataAn.jfreechart.dto.LineChartDto;


public interface IJfreechartServcie {

	
	/**
	* Description: 生成一张图片，只有一个y轴
	* @param series 系列 j9
	* @param star 星 01、02、03...
	* @param paramType flywheel、top
	* @param date 某一天：2015-01-01
	* @param params 参数： 英文-中文
	* @return 图片路径
	* @throws Exception
	* @author Shenwp
	* @date 2016年10月11日
	* @version 1.0
	*/
	public LineChartDto createLineChart(String series,
			String star, String paramType, String date,
			Map<String,String> params) throws Exception;
	
	/**
	* Description: 生成一张图片，只有一个y轴
	* @param title 图片标题
	* @param categoryAxisLabel x轴标题
	* @param valueAxisLabel y轴标题
	* @param series 系列:名字和数据集合 构成一条曲线</br> 可以将serie看作一根线或者一根柱子：
	* @param categories 类型： x轴数值
	* @return 图片路径
	* @throws Exception
	* @author Shenwp
	* @date 2016年10月11日
	* @version 1.0
	*/
	public String createLineChart(String title,
			String categoryAxisLabel, String valueAxisLabel,
			Vector<Serie> series, Vector<String> categories) throws Exception;
}
