package DataAn.fileSystem.dao;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import DataAn.common.pageModel.Pager;
import DataAn.fileSystem.domain.VirtualFileSystem;
import DataAn.galaxy.option.J9Series_Star_ParameterType;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext-hibernate.xml")
public class VirtualFileSystemDaoTest {

	@Resource
	private IVirtualFileSystemDao fileDao;
	
	@Test
	public void selectByParentIdisNullAndFileName(){
		VirtualFileSystem csvDir = fileDao.selectByParentIdisNullAndFileName("csv");
		System.out.println(csvDir);
	}
	
	@Test
	public void selectByParentIdAndFileName(){
		VirtualFileSystem yearDir = fileDao.selectByParentIdAndFileName(1, "2015");
		System.out.println(yearDir);
	}
	@Test
	public void findAll(){
		List<VirtualFileSystem> list = fileDao.findAll();
		for (VirtualFileSystem fs : list) {
			System.out.println(fs);
		}
	}
	
	@Test
	public void selectByOption(){
		String series = "j9";
		String star = "02";
		long dirId = 1;
		String beginTime = "2015-08-11";
		String endTime = "2015-08-12";
		String dataTypes = "dat,csv";
		String paramType = J9Series_Star_ParameterType.FLYWHEEL.getValue();
		Pager<VirtualFileSystem> pager = fileDao.selectByOption(series, star,paramType, dirId, beginTime, endTime, dataTypes, "fileName",1,10);
		List<VirtualFileSystem> list = pager.getRows();
		for (VirtualFileSystem fs : list) {
			System.out.println(fs);
		}
	}
	
	@Test
	public void test(){
		String series = "j9";
		String star = "03";
		String parameterType = "flywheel";
		VirtualFileSystem csvDir = fileDao.selectBySeriesAndStarAndParameterTypeAndParentIdAndFileName(series, star, parameterType, 0, "csv");
		System.out.println(csvDir);
	}
}
