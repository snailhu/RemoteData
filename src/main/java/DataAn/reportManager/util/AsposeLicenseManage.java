package DataAn.reportManager.util;

import java.io.InputStream;

import DataAn.wordManager.config.OptionConfig;

import com.aspose.words.License;

public class AsposeLicenseManage {
    /**
     * 获取Aspose插件的license
     * 
     * @return
     */
    public static boolean getAsposeLicense() {
        boolean result = false;
        try {
        	InputStream license;
            //license = AsposeLicenseManage.class.getClassLoader().getResourceAsStream("license.xml");// license路径
        	license = AsposeLicenseManage.class.getResourceAsStream("license.xml");
        	License aposeLic = new License();
            aposeLic.setLicense(license);
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}
