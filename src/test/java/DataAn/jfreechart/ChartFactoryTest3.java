package DataAn.jfreechart;

import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.Date;
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


public class ChartFactoryTest3 {

	public static void main(String[] args) {
		 final JFrame frame = new JFrame();
	        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	        frame.setSize(1024, 620);
	        frame.setLocationRelativeTo(null);

	        SwingUtilities.invokeLater(new Runnable() {
	            @Override
	            public void run() {
	                // 创建图形
	                ChartPanel chartPanel = new ChartFactoryTest3().createChart();
	                frame.getContentPane().add(chartPanel);
	                frame.setVisible(true);
	            }
	        });
	}
	public ChartPanel createChart() {
		// 2：创建Chart[创建不同图形]
        TimeSeriesCollection dataset = createDataset();
        System.out.println("createChart Tokyo: " + dataset.getItemCount(0));
        System.out.println("createChart NewYork: " + dataset.getItemCount(1));
        JFreeChart chart = ChartFactory.createTimeSeriesChart("天气", "", "", dataset);
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
	public TimeSeriesCollection createDataset() {
        TimeSeriesCollection dataset = new TimeSeriesCollection();
        String[] categories = {"Tokyo", "NewYork"};
        Vector<Object[]> tokyoDateValues = new Vector<Object[]>();
        Vector<Object[]> newYorkDateValues = new Vector<Object[]>();
        Vector<Object[]> londonDateValues = new Vector<Object[]>();
        Vector<Object[]> berlinDateValues = new Vector<Object[]>();
        
        int year = 2016;
		int month = 1;
		int day = 1;
		Date date1 = new Date(year,month-1,day,0,0,0);
		Date date2 = new Date(year,month-1,day+2,0,0,0);
		Date tempDate = date1;
		long time = tempDate.getTime();
		System.out.println("date1: " + date1);
		System.out.println("time: " + time);
		String format = year+ "-MM-dd HH:mm:ss.SSS";
		DecimalFormat df = new DecimalFormat("#.00");
		while(tempDate.before(date2)){
			tempDate = new Date(time);
			String date = DateUtil.format(tempDate, format);
			
			Double data = 10 + Math.random() * Math.random() * (month + day) * 10;
			Object[] dateValue = {date, df.format(data)};
			tokyoDateValues.add(dateValue);
			
			data =  5 + Math.random() * Math.random() * (month + day) * 5;
			Object[] newYorkDateValue = {date, df.format(data)};
			newYorkDateValues.add(newYorkDateValue);
			//System.out.println(date + " : " +df.format(data));
			time = time + 60000;			
		}
		System.out.println("tokyoDateValues: " +tokyoDateValues.size());
		TimeSeries timeSeries = ChartUtils.createTimeseries("Tokyo",tokyoDateValues);
		dataset.addSeries(timeSeries);

		System.out.println("newYorkDateValues: " +newYorkDateValues.size());
		timeSeries = ChartUtils.createTimeseries("NewYork", newYorkDateValues);
		dataset.addSeries(timeSeries);
        
		if(dataset != null && dataset.getSeriesCount() >0){
			Calendar cal = Calendar.getInstance();
			for (int i = 0; i < dataset.getSeriesCount(); i++) {
				timeSeries = dataset.getSeries(i);
				System.out.println("第 " + (i+1) + " 条count: " + timeSeries.getItemCount());
				System.out.println("begin..");
				System.out.println(timeSeries.getDataItem(0).getPeriod());
				System.out.println(timeSeries.getDataItem(0).getPeriod().getStart());
				System.out.println(timeSeries.getDataItem(0).getPeriod().getEnd());
				System.out.println(timeSeries.getDataItem(0).getPeriod().getLastMillisecond());
				cal.setTime(timeSeries.getDataItem(0).getPeriod().getStart());
				System.out.println("DAY_OF_YEAR: " + cal.get(Calendar.DATE));
				System.out.println("end...");
				System.out.println(timeSeries.getDataItem(timeSeries.getItemCount()-1).getPeriod());
				System.out.println(timeSeries.getDataItem(timeSeries.getItemCount()-1).getPeriod().getStart());
				System.out.println(timeSeries.getDataItem(timeSeries.getItemCount()-1).getPeriod().getEnd());
				System.out.println(timeSeries.getDataItem(timeSeries.getItemCount()-1).getPeriod().getLastMillisecond());
				cal.setTime(timeSeries.getDataItem(timeSeries.getItemCount()-1).getPeriod().getEnd());
				System.out.println("DAY_OF_YEAR: " + cal.get(Calendar.DATE));
				long interval = timeSeries.getDataItem(timeSeries.getItemCount()-1).getPeriod().getLastMillisecond() - timeSeries.getDataItem(0).getPeriod().getLastMillisecond();
				System.out.println(interval);
			}
		}
		
        return dataset;
    }
    
}
