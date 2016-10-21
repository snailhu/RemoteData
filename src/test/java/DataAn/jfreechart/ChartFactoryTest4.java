package DataAn.jfreechart;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;

import DataAn.common.utils.DateUtil;
import DataAn.jfreechart.chart.ChartFactory;
import DataAn.jfreechart.chart.ChartUtils;


public class ChartFactoryTest4 {

	public static void main(String[] args) {
		 final JFrame frame = new JFrame();
	        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	        frame.setSize(1024, 620);
	        frame.setLocationRelativeTo(null);

	        SwingUtilities.invokeLater(new Runnable() {
	            @Override
	            public void run() {
	                // 创建图形
	                ChartPanel chartPanel = new ChartFactoryTest4().createChart();
	                frame.getContentPane().add(chartPanel);
	                frame.setVisible(true);
	            }
	        });
	}
	public ChartPanel createChart() {
		// 2：创建Chart[创建不同图形]
		List<TimeSeriesCollection> datasetList = createDataset();
        System.out.println("createChart Tokyo: " + datasetList.get(0).getItemCount(0));
        System.out.println("createChart NewYork: " + datasetList.get(1).getItemCount(0));
        JFreeChart chart = ChartFactory.createTimeSeriesChart("天气", "", "", datasetList);
        // 设置图例位置
        // 6:使用chartPanel接收
        ChartPanel chartPanel = new ChartPanel(chart);
        return chartPanel;
	}
	/**
     * 创建数据集合
     *
     * @return
     */
    @SuppressWarnings("deprecation")
	public List<TimeSeriesCollection> createDataset() {
        String[] categories = {"Tokyo", "NewYork"};
        Vector<Object[]> tokyoDateValues = new Vector<Object[]>();
        Vector<Object[]> newYorkDateValues = new Vector<Object[]>();
        Vector<Object[]> londonDateValues = new Vector<Object[]>();
        Vector<Object[]> berlinDateValues = new Vector<Object[]>();
        
        int year = 2016;
		int month = 1;
		int day = 1;
		Date date1 = new Date(year,month-1,day,0,0,0);
		Date date2 = new Date(year,month-1,day+1,0,0,0);
		Date tempDate = date1;
		long time = tempDate.getTime();
		String format = year+ "-MM-dd HH:mm:ss.SSS";
		DecimalFormat df = new DecimalFormat("#.00");
		while(tempDate.before(date2)){
			tempDate = new Date(time);
			String date = DateUtil.format(tempDate, format);
			
			Double data = 20 + Math.random() * Math.random() * (month + day) * 10;
			Object[] dateValue = {date, df.format(data)};
			tokyoDateValues.add(dateValue);
			
			data =  1 + Math.random() * Math.random() * (month + day) * 5;
			Object[] newYorkDateValue = {date, df.format(data)};
			newYorkDateValues.add(newYorkDateValue);
			//System.out.println(date + " : " +df.format(data));
			time = time + 60000;			
		}
		List<TimeSeriesCollection> datasetList = new ArrayList<TimeSeriesCollection>();
		
        TimeSeriesCollection dataset1 = new TimeSeriesCollection();
		System.out.println("tokyoDateValues: " +tokyoDateValues.size());
		TimeSeries timeSeries1 = ChartUtils.createTimeseries("Tokyo",tokyoDateValues);
		dataset1.addSeries(timeSeries1);
		datasetList.add(dataset1);
		
		TimeSeriesCollection dataset2 = new TimeSeriesCollection();
		System.out.println("newYorkDateValues: " +newYorkDateValues.size());
		TimeSeries timeSeries2 = ChartUtils.createTimeseries("NewYork", newYorkDateValues);
		dataset2.addSeries(timeSeries2);
		datasetList.add(dataset2);
		
        return datasetList;
    }
    
}
