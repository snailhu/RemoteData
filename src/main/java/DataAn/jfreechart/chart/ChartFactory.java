package DataAn.jfreechart.chart;

import java.awt.Color;
import java.text.SimpleDateFormat;
import java.util.Vector;

import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.DateAxis;
import org.jfree.chart.axis.DateTickUnit;
import org.jfree.chart.axis.DateTickUnitType;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.block.BlockBorder;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.category.LineAndShapeRenderer;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.ui.RectangleEdge;


/**
 * 
 * 生成JFreeChart图表的工厂类<br/>
 * 
 */
public class ChartFactory {
	
	
	public static JFreeChart createTimeSeriesChart(String title,
			String categoryAxisLabel, String valueAxisLabel,
			TimeSeriesCollection dataset) {
        JFreeChart chart = org.jfree.chart.ChartFactory.createTimeSeriesChart(title, categoryAxisLabel, valueAxisLabel, dataset);
        // 3:设置抗锯齿，防止字体显示不清楚
        ChartUtils.setAntiAlias(chart);// 抗锯齿
        // 4:对柱子进行渲染[创建不同图形]
        ChartUtils.setTimeSeriesRender(chart.getPlot(), false, false);
        // 5:对其他部分进行渲染
        XYPlot xyplot = (XYPlot) chart.getPlot();
        ChartUtils.setXY_XAixs(xyplot);
        ChartUtils.setXY_YAixs(xyplot);
        // 日期X坐标轴
        DateAxis domainAxis = (DateAxis) xyplot.getDomainAxis();
        domainAxis.setAutoTickUnitSelection(false);
        DateTickUnit dateTickUnit = null;
        
//        if (dataset.getItemCount(0) < 16) {
//            //刻度单位月,半年为间隔
//            dateTickUnit = new DateTickUnit(DateTickUnitType.SECOND, 6, new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")); // 第二个参数是时间轴间距
//        } else {// 数据过多,不显示数据
//            XYLineAndShapeRenderer xyRenderer = (XYLineAndShapeRenderer) xyplot.getRenderer();
//            xyRenderer.setBaseItemLabelsVisible(false);
//            dateTickUnit = new DateTickUnit(DateTickUnitType.DAY, 1, new SimpleDateFormat("yyyy-MM-dd")); // 第二个参数是时间轴间距
//        }
        
//        XYLineAndShapeRenderer xyRenderer = (XYLineAndShapeRenderer) xyplot.getRenderer();
//        xyRenderer.setBaseItemLabelsVisible(false);
        dateTickUnit = new DateTickUnit(DateTickUnitType.DAY, 1, new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")); // 第二个参数是时间轴间距

        
        // 设置时间单位
        domainAxis.setTickUnit(dateTickUnit);
        ChartUtils.setLegendEmptyBorder(chart);
		return chart;
	}
	
	public static JFreeChart createLineChartDoubleY(String title,
			String categoryAxisLabel, String valueAxisLabel,
			Vector<Serie> series, Vector<String> categories) {
		Vector<Serie> series1 = new Vector<Serie>();
		Vector<Serie> series2 = new Vector<Serie>();
		
		for (Serie serie : series) {
			//数据分类
			if(serie.isY2Axis()){
				series2.add(serie);
			}else{
				series1.add(serie);
			}
		}
		
		// 1：创建数据集合
		DefaultCategoryDataset dataset1 = ChartUtils.createDefaultCategoryDataset(series1, categories);
		
		JFreeChart chart = org.jfree.chart.ChartFactory.createLineChart(title,
				categoryAxisLabel, valueAxisLabel, dataset1);
		
		// 3:设置抗锯齿，防止字体显示不清楚
		ChartUtils.setAntiAlias(chart);// 抗锯齿
		// 4:对柱子进行渲染[[采用不同渲染]]
		ChartUtils.setLineRender(chart.getCategoryPlot(), false, false);//
		// 5:对其他部分进行渲染
		ChartUtils.setXAixs(chart.getCategoryPlot());// X坐标轴渲染
		ChartUtils.setYAixs(chart.getCategoryPlot());// Y坐标轴渲染
		// 设置标注无边框
		chart.getLegend().setFrame(new BlockBorder(Color.WHITE));
		
		if(series2.size() > 1){
			CategoryPlot plot = chart.getCategoryPlot();
			DefaultCategoryDataset dataset2 = ChartUtils.createDefaultCategoryDataset(series2, categories);
			// 添加第2个Y轴
			NumberAxis axis2 = new NumberAxis(" Second Axis");
			// -- 修改第2个Y轴的显示效果
			axis2.setAxisLinePaint(Color.BLUE);
			axis2.setLabelPaint(Color.BLUE);
			axis2.setTickLabelPaint(Color.BLUE);
			// 显示Y刻度
			axis2.setAxisLineVisible(true);
			axis2.setTickMarksVisible(true);
			
			plot.setRangeAxis(1, axis2);
			plot.setDataset(1, dataset2);
			plot.mapDatasetToRangeAxis(1, 1);
			
			plot.getRangeAxis(1).setUpperMargin(0.1);// 设置顶部Y坐标轴间距,防止数据无法显示
			plot.getRangeAxis(1).setLowerMargin(0.1);// 设置底部Y坐标轴间距
			
			LineAndShapeRenderer renderer1 =  new LineAndShapeRenderer();
			//renderer1.setSeriesPaint(0, ChartUtils.Y_COLORS[0]);
			renderer1.setBaseShapesVisible(false);// 数据点绘制形状
			plot.setRenderer(1, renderer1);
		}
		
		return chart;
	}
	public static JFreeChart createLineChartManyY(String title,
			String categoryAxisLabel, String valueAxisLabel,
			Vector<Serie> series, Vector<String> categories) {
		
		Serie serie0 = series.get(0);
		// 1：创建数据集合
		DefaultCategoryDataset dataset = ChartUtils.createDefaultCategoryDataset(serie0, categories);
		
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
		
		if(series.size() > 1){
			CategoryPlot plot = chart.getCategoryPlot();
			for (int i = 1; i < series.size(); i++) {
				DefaultCategoryDataset dataset1 = ChartUtils.createDefaultCategoryDataset(series.get(i), categories);
				// 添加第i+1个Y轴
				NumberAxis axis2 = new NumberAxis((i + 1) + "Axis");
				// -- 修改第i+1个Y轴的显示效果
				axis2.setAxisLinePaint(ChartUtils.Y_COLORS[i]);
				axis2.setLabelPaint(ChartUtils.Y_COLORS[i]);
				axis2.setTickLabelPaint(ChartUtils.Y_COLORS[i]);
				plot.setRangeAxis(i, axis2);
				plot.setDataset(i, dataset1);
				plot.mapDatasetToRangeAxis(2, 1);
				LineAndShapeRenderer renderer1 =  new LineAndShapeRenderer();
				renderer1.setSeriesPaint(0, ChartUtils.Y_COLORS[i]);
				renderer1.setBaseShapesVisible(false);// 数据点绘制形状
				plot.setRenderer(i, renderer1);
			}
			
		}
		
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
