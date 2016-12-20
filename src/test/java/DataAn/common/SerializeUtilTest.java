package DataAn.common;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Vector;

import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;

import DataAn.common.utils.DateUtil;
import DataAn.common.utils.SerializeUtil;
import DataAn.jfreechart.chart.ChartUtils;

public class SerializeUtilTest {

	@org.junit.Test
	public void test() {
		TimeSeriesCollection dataset = createDataset();
		TimeSeries timeSeries1 = dataset.getSeries(0);
		TimeSeries timeSeries2 = dataset.getSeries(1);
		System.out.println("createChart Tokyo: " + timeSeries1.getItemCount());
		System.out.println("createChart NewYork: " + timeSeries2.getItemCount());
		byte[] byte1 = SerializeUtil.serialize(timeSeries1);
		System.out.println(byte1.length);
		byte[] byte2 = SerializeUtil.serialize(timeSeries2);
		System.out.println(byte2.length);
	}

	//对象序列化过程  
	@org.junit.Test
	public void test2() throws Exception {
		TimeSeriesCollection dataset = createDataset();
		TimeSeries timeSeries1 = dataset.getSeries(0);
		TimeSeries timeSeries2 = dataset.getSeries(1);
		System.out.println("createChart Tokyo: " + timeSeries1.getItemCount());
		System.out.println("createChart NewYork: " + timeSeries2.getItemCount());
		File file = new File("D:\\timeSeries1.txt");
		FileOutputStream fos = new FileOutputStream(file);
		ObjectOutputStream oos = new ObjectOutputStream(fos);
		oos.writeObject(timeSeries1);
		oos.flush();
		oos.close();
		fos.close();
	}

	//对象反序列化过程
	@org.junit.Test
	public void test3() throws Exception{
		File file = new File("D:\\timeSeries1.txt");
		FileInputStream fis = new FileInputStream(file);  
		ObjectInputStream ois = new ObjectInputStream(fis);
		TimeSeries timeSeries1 = (TimeSeries) ois.readObject();
		System.out.println("createChart Tokyo: " + timeSeries1.getItemCount());
	}
	/**
	 * 创建数据集合
	 *
	 * @return
	 */
	@SuppressWarnings("deprecation")
	public TimeSeriesCollection createDataset() {
		TimeSeriesCollection dataset = new TimeSeriesCollection();
		String[] categories = { "Tokyo", "NewYork" };
		Vector<Object[]> tokyoDateValues = new Vector<Object[]>();
		Vector<Object[]> newYorkDateValues = new Vector<Object[]>();
		Vector<Object[]> londonDateValues = new Vector<Object[]>();
		Vector<Object[]> berlinDateValues = new Vector<Object[]>();

		int year = 2016;
		int month = 1;
		int day = 1;
		Date date1 = new Date(year, month - 1, day, 0, 0, 0);
		Date date2 = new Date(year, month - 1, day + 2, 0, 0, 0);
		Date tempDate = date1;
		long time = tempDate.getTime();
		System.out.println("date1: " + date1);
		System.out.println("time: " + time);
		String format = year + "-MM-dd HH:mm:ss.SSS";
		DecimalFormat df = new DecimalFormat("#.00");
		while (tempDate.before(date2)) {
			tempDate = new Date(time);
			String date = DateUtil.format(tempDate, format);

			Double data = 10 + Math.random() * Math.random() * (month + day)
					* 10;
			Object[] dateValue = { date, df.format(data) };
			tokyoDateValues.add(dateValue);

			data = 5 + Math.random() * Math.random() * (month + day) * 5;
			Object[] newYorkDateValue = { date, df.format(data) };
			newYorkDateValues.add(newYorkDateValue);
			// System.out.println(date + " : " +df.format(data));
			time = time + 6000;
		}
		System.out.println("tokyoDateValues: " + tokyoDateValues.size());
		TimeSeries timeSeries = ChartUtils.createTimeseries("Tokyo",
				tokyoDateValues);
		dataset.addSeries(timeSeries);

		System.out.println("newYorkDateValues: " + newYorkDateValues.size());
		timeSeries = ChartUtils.createTimeseries("NewYork", newYorkDateValues);
		dataset.addSeries(timeSeries);

		return dataset;
	}
}
