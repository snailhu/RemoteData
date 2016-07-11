package DataAn.fileSystem.dao;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import DataAn.fileSystem.domain.VirtualFileSystem;

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
}
