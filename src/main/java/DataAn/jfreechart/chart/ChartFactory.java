package DataAn.jfreechart.chart;

import java.awt.Color;
import java.util.Vector;

import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.block.BlockBorder;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.ui.RectangleEdge;

/**
 * 
 * 生成JFreeChart图表的工厂类<br/>
 * 
 */
public class ChartFactory {
	
	public static JFreeChart createLineChartManyY(String title,
			String categoryAxisLabel, String valueAxisLabel,
			Vector<Serie> series, Vector<String> categories) {
		Serie serie0 = series.get(0);
		// 1：创建数据集合
		DefaultCategoryDataset dataset = ChartUtils.createDefaultCategoryDataset(serie0, categories);
		JFreeChart chart = org.jfree.chart.ChartFactory.createLineChart(title,
				categoryAxisLabel, valueAxisLabel, dataset);
		
		CategoryPlot plot = chart.getCategoryPlot();
		if(series.size() > 1){
			for (int i = 1; i < series.size(); i++) {
				// 添加第2个Y轴
				NumberAxis axis2 = new NumberAxis("Second Axis");
				// -- 修改第2个Y轴的显示效果
				axis2.setAxisLinePaint(Color.BLUE);
				axis2.setLabelPaint(Color.BLUE);
				axis2.setTickLabelPaint(Color.BLUE);
				
				dataset = ChartUtils.createDefaultCategoryDataset(series.get(i), categories);
				
				plot.setRangeAxis(1, axis2);
				plot.setDataset(1, dataset);
				plot.mapDatasetToRangeAxis(1, 1);
			}
		}
		// 3:设置抗锯齿，防止字体显示不清楚
		ChartUtils.setAntiAlias(chart);// 抗锯齿
		// 4:对柱子进行渲染[[采用不同渲染]]
		ChartUtils.setLineRender(chart.getCategoryPlot(), false, true);//
		// 5:对其他部分进行渲染
		ChartUtils.setXAixs(chart.getCategoryPlot());// X坐标轴渲染
		ChartUtils.setYAixs(chart.getCategoryPlot());// Y坐标轴渲染
		// 设置标注无边框
		chart.getLegend().setFrame(new BlockBorder(Color.WHITE));
		
		return chart;
	}
	
	/**
	 * 生成折线图
	 * 
	 * @param title
	 *            折线图的标题
	 * @param categoryAxisLabel
	 *            x轴标题
	 * @param valueAxisLabel
	 *            y轴标题
	 * @param series
	 *            数据
	 * @param categories
	 *            类别
	 * @return
	 */
	public static JFreeChart createLineChartOneY(String title,
			String categoryAxisLabel, String valueAxisLabel,
			Vector<Serie> series, Vector<String> categories) {
		// 1：创建数据集合
		DefaultCategoryDataset dataset = ChartUtils.createDefaultCategoryDataset(series, categories);
		
		JFreeChart chart = org.jfree.chart.ChartFactory.createLineChart(title,
				categoryAxisLabel, valueAxisLabel, dataset);
		// 3:设置抗锯齿，防止字体显示不清楚
		ChartUtils.setAntiAlias(chart);// 抗锯齿
		// 4:对柱子进行渲染[[采用不同渲染]]
		ChartUtils.setLineRender(chart.getCategoryPlot(), false, false);//
		// 5:对其他部分进行渲染
		ChartUtils.setXAixs(chart.getCategoryPlot());// X坐标轴渲染
		ChartUtils.setYAixs(chart.getCategoryPlot());// Y坐标轴渲染
		// 设置标注无边框
		chart.getLegend().setFrame(new BlockBorder(Color.WHITE));
		
		return chart;
	}
	
	public static JFreeChart createLineChartOneY(String title,
			String categoryAxisLabel, String valueAxisLabel,
			Vector<Serie> series, String[] categories) {
		// 1：创建数据集合
		DefaultCategoryDataset dataset = ChartUtils.createDefaultCategoryDataset(series, categories);
		
		JFreeChart chart = org.jfree.chart.ChartFactory.createLineChart(title,
				categoryAxisLabel, valueAxisLabel, dataset);
		// 3:设置抗锯齿，防止字体显示不清楚
		ChartUtils.setAntiAlias(chart);// 抗锯齿
		// 4:对柱子进行渲染[[采用不同渲染]]
		ChartUtils.setLineRender(chart.getCategoryPlot(), false, false);//
		// 5:对其他部分进行渲染
		ChartUtils.setXAixs(chart.getCategoryPlot());// X坐标轴渲染
		ChartUtils.setYAixs(chart.getCategoryPlot());// Y坐标轴渲染
		// 设置标注无边框
		chart.getLegend().setFrame(new BlockBorder(Color.WHITE));
		
		return chart;
	}

	/**
	 * 生成柱状图
	 * 
	 * @param title
	 *            柱状图的标题
	 * @param categoryAxisLabel
	 *            x轴标题
	 * @param valueAxisLabel
	 *            y轴标题
	 * @param series
	 *            数据
	 * @param categories
	 *            类别
	 * @return
	 */
	public static JFreeChart createBarChart(String title,
			String categoryAxisLabel, String valueAxisLabel,
			Vector<Serie> series, String[] categories) {
		// 1：创建数据集合
		DefaultCategoryDataset dataset = ChartUtils
				.createDefaultCategoryDataset(series, categories);
		JFreeChart chart = org.jfree.chart.ChartFactory.createBarChart(title,
				categoryAxisLabel, valueAxisLabel, dataset);
		// 3:设置抗锯齿，防止字体显示不清楚
		ChartUtils.setAntiAlias(chart);// 抗锯齿
		// 4:对柱子进行渲染
		ChartUtils.setBarRenderer(chart.getCategoryPlot(), false);//
		// 5:对其他部分进行渲染
		ChartUtils.setXAixs(chart.getCategoryPlot());// X坐标轴渲染
		ChartUtils.setYAixs(chart.getCategoryPlot());// Y坐标轴渲染
		// 设置标注无边框
		chart.getLegend().setFrame(new BlockBorder(Color.WHITE));
		return chart;
	}

	/**
	 * 生成饼图
	 * 
	 * @param title
	 *            饼图的标题
	 * @param categories
	 *            类别
	 * @param datas
	 *            数据
	 * @return
	 */
	public static JFreeChart createPieChart(String title, String[] categories,
			Object[] datas) {
		// 1：创建数据集合
		DefaultPieDataset dataset = ChartUtils.createDefaultPieDataset(
				categories, datas);
		JFreeChart chart = org.jfree.chart.ChartFactory.createPieChart(title,
				dataset);
		// 3:设置抗锯齿，防止字体显示不清楚
		ChartUtils.setAntiAlias(chart);// 抗锯齿
		// 4:对柱子进行渲染[创建不同图形]
		ChartUtils.setPieRender(chart.getPlot());
		/**
		 * 可以注释测试
		 */
		// plot.setSimpleLabels(true);//简单标签,不绘制线条
		// plot.setLabelGenerator(null);//不显示数字
		// 设置标注无边框
		chart.getLegend().setFrame(new BlockBorder(Color.WHITE));
		// 标注位于右侧
		chart.getLegend().setPosition(RectangleEdge.RIGHT);
		return chart;
	}
	
	/**
	 * 生成StackedBarChart
	 * 
	 * @param title
	 *            StackedBarChart的标题
	 * @param domainAxisLabel
	 * @param rangeAxisLabel
	 * @param series
	 *            数据
	 * @param categories
	 *            类别
	 * @return
	 */
	public static JFreeChart createStackedBarChart(String title,
			String domainAxisLabel, String rangeAxisLabel,
			Vector<Serie> series, String[] categories) {
		// 1：创建数据集合
		DefaultCategoryDataset dataset = ChartUtils
				.createDefaultCategoryDataset(series, categories);
		JFreeChart chart = org.jfree.chart.ChartFactory.createStackedBarChart(
				title, domainAxisLabel, rangeAxisLabel, dataset);
		// 3:设置抗锯齿，防止字体显示不清楚
		ChartUtils.setAntiAlias(chart);// 抗锯齿
		// 4:对柱子进行渲染[创建不同图形]
		ChartUtils.setStackBarRender(chart.getCategoryPlot());
		// 5:对其他部分进行渲染
		ChartUtils.setXAixs(chart.getCategoryPlot());// X坐标轴渲染
		ChartUtils.setYAixs(chart.getCategoryPlot());// Y坐标轴渲染
		// 设置标注无边框
		chart.getLegend().setFrame(new BlockBorder(Color.WHITE));
		return chart;
	}
}
