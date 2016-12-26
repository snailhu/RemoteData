package DataAn.mongo.service.impl;

import java.io.InputStream;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import DataAn.fileSystem.dao.IVirtualFileSystemDao;
import DataAn.fileSystem.domain.VirtualFileSystem;
import DataAn.mongo.fs.IDfsDb;
import DataAn.mongo.fs.MongoDfsDb;
import DataAn.mongo.init.InitMongo;
import DataAn.mongo.service.IMongoGridFSService;

@Service
public class MongoGridFSServiceImpl implements IMongoGridFSService{

	//从mongofs中获取数据流
	private IDfsDb dfs = MongoDfsDb.getInstance();
	@Resource
	private IVirtualFileSystemDao fileDao;
	
	@Override
	public InputStream downLoadToStream(String mongoFSUUId) {
		List<VirtualFileSystem> list = fileDao.findByParam("mongoFSUUId", mongoFSUUId);
		if (list != null && list.size() > 0) {
			VirtualFileSystem fs = list.get(0);
			String databaseName = InitMongo.getFSBDNameBySeriesAndStar(fs.getSeries(), fs.getStar());
			return dfs.downLoadToStream(databaseName, fs.getMongoFSUUId());
		}
		return null;
	}
	
	
}
