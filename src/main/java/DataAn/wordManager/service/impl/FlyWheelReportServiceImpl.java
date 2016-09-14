package DataAn.wordManager.service.impl;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.aspose.words.Document;
import com.aspose.words.SaveFormat;

import DataAn.wordManager.config.OptionConfig;
import DataAn.wordManager.service.IFlyWheelReoportService;
import DataAn.wordManager.util.MapMailMergeDataSource;

import DataAn.wordManager.util.AsposeLicenseManage;
@Service
public class FlyWheelReportServiceImpl implements IFlyWheelReoportService {
	@Override
	public void CreateReport(String filename) throws Exception {
		
		// 验证License		
        if (!AsposeLicenseManage.getAsposeLicense()) {
            return;
        }
		//1 读取模板  
		Document doc = new Document(OptionConfig.getWebPath() + "\\report\\wordtemplate\\卫星状态报告.doc");  
		String imagePath = OptionConfig.getWebPath() + "\\report\\wordtemplate\\satellite.jpg";  
        //2 填充数据源  
        doc.getMailMerge().executeWithRegions(new MapMailMergeDataSource(getMapList(imagePath), "Employees"));  
		//3生成报告
        doc.save(OptionConfig.getWebPath() + "\\report\\飞轮报告1.doc", SaveFormat.DOC); 
	}
	
	private List<Map<String, Object>> getMapList(String imagePath) throws Exception {  
        List<Map<String, Object>> dataList = new ArrayList<Map<String,Object>>();  
          
        //读取一个二进制图片  
        FileInputStream fis = new FileInputStream(imagePath);  
        byte[] image = new byte[fis.available()];  
        fis.read(image);  
        fis.close();  
          
        for (int i = 0; i < 1; i++) {  
            Map<String, Object> record = new HashMap<String, Object>();  
            //这里的key要与模板中的<<xxxxx>>对应  
            /*record.put("FirstName", "");  
            record.put("LastName", "夏丹");  
            record.put("Title", "j9-2卫星飞轮健康状态报告");  
            record.put("Address", "上海");  
            record.put("City", "上海");  
            record.put("Country", "上海");*/
            record.put("reporttitle", "卫星状态");
            record.put("parts", "飞轮状态");
            record.put("healthcondition", "飞轮运行正常");           
            //二进制数据  
            record.put("PhotoBLOB", image);  
            dataList.add(record);  
        }  
        return dataList;  
    } 
}
