package DataAn.jfreechart.chart;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Vector;

import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.CategoryLabelPositions;
import org.jfree.chart.axis.DateAxis;
import org.jfree.chart.axis.DateTickUnit;
import org.jfree.chart.axis.DateTickUnitType;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.block.BlockBorder;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.category.LineAndShapeRenderer;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
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
        dateTickUnit = new DateTickUnit(DateTickUnitType.HOUR, 4, new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")); // 第二个参数是时间轴间距
        
        // 设置时间单位
        domainAxis.setTickUnit(dateTickUnit);
        ChartUtils.setLegendEmptyBorder(chart);
       
		return chart;
	}
	
	public static JFreeChart createTimeSeriesChart(String title,
			String categoryAxisLabel, String valueAxisLabel,
			List<TimeSeriesCollection> datasetList) {
        JFreeChart chart = org.jfree.chart.ChartFactory.createTimeSeriesChart(title, categoryAxisLabel, valueAxisLabel, datasetList.get(0));
        
        XYPlot xyplot = (XYPlot) chart.getPlot();
        //第二个Y轴的数据构造
        if(datasetList.size() > 1){
        	TimeSeriesCollection dataset2  = datasetList.get(1);
			// 添加第2个Y轴
			NumberAxis axis2 = new NumberAxis();
			// -- 修改第2个Y轴的显示效果
			axis2.setAxisLinePaint(Color.BLUE);
			axis2.setLabelPaint(Color.BLUE);
			axis2.setTickLabelPaint(Color.BLUE);
			// 显示Y刻度
			axis2.setAxisLineVisible(true);
			axis2.setTickMarksVisible(true);
			
			xyplot.setRangeAxis(1, axis2);
			xyplot.setDataset(1, dataset2);
			xyplot.mapDatasetToRangeAxis(1, 1);
			
			xyplot.getRangeAxis(1).setUpperMargin(0.1);// 设置顶部Y坐标轴间距,防止数据无法显示
			xyplot.getRangeAxis(1).setLowerMargin(0.1);// 设置底部Y坐标轴间距
			
			XYLineAndShapeRenderer xyrenderer1 =  new XYLineAndShapeRenderer();
			xyrenderer1.setBaseItemLabelsVisible(false);// 数据点绘制形状
			xyrenderer1.setBaseShapesVisible(false);// 数据点绘制形状
			xyplot.setRenderer(1,xyrenderer1);
			xyrenderer1.setSeriesStroke(0, new BasicStroke(0.5F)); //设置线的大小
			// xyrenderer1.setSeriesPaint(0, Color.RED);//红色
			xyrenderer1.setSeriesStroke(1, new BasicStroke(0.5F));
			// xyrenderer1.setSeriesPaint(1, Color.GREEN);//绿色
			xyrenderer1.setSeriesStroke(3, new BasicStroke(0.5F));
			// xyrenderer1.setSeriesPaint(3, Color.BLUE);//蓝色
			xyrenderer1.setSeriesStroke(4, new BasicStroke(0.5F));
			// xyrenderer1.setSeriesPaint(4, Color.BLACK);//黑色
			xyrenderer1.setSeriesStroke(5, new BasicStroke(0.5F));
			// xyrenderer1.setSeriesPaint(5, Color.CYAN);
		}
     // 3:设置抗锯齿，防止字体显示不清楚
        ChartUtils.setAntiAlias(chart);// 抗锯齿
        // 4:对柱子进行渲染[创建不同图形]
        ChartUtils.setTimeSeriesRender(chart.getPlot(), false, false);
        // 5:对其他部分进行渲染
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
        dateTickUnit = new DateTickUnit(DateTickUnitType.HOUR, 4, new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")); // 第二个参数是时间轴间距

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
