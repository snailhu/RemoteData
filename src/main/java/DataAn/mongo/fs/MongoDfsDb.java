package DataAn.mongo.fs;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import DataAn.common.utils.LogUtil;
import DataAn.mongo.client.MongodbUtil;
import DataAn.mongo.init.InitMongo;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.gridfs.GridFS;
import com.mongodb.gridfs.GridFSDBFile;
import com.mongodb.gridfs.GridFSInputFile;

public class MongoDfsDb implements IDfsDb{
	
//	private GridFS gridFS;
//	private DBCollection DBCollection = null; 
//	private DBCollection chunksCollection = null;
		
	private static MongoClient mg = null;
	
	private static ConcurrentHashMap<String, DB> dbs = new ConcurrentHashMap<String, DB>();

	private static ConcurrentHashMap<String, GridFS> gridFSs = new ConcurrentHashMap<String, GridFS>();
	
	private static class MongoDfsDbInstance {
		private static final IDfsDb instance = new MongoDfsDb();
	}
	
	public static IDfsDb getInstance(){
		return MongoDfsDbInstance.instance;
	}
	
	private MongoDfsDb(){
//		// 1.建立一个Mongo的数据库连接对象
//		String uri = InitMongo.SERVER_HOST + ":" + InitMongo.SERVER_PORT;
//		Mongo connection = new Mongo(uri);
//		// 2.创建相关数据库的连接
//		DB db = connection.getDB(InitMongo.DATABASE_TEST);
//		gridFS = new GridFS(db);
		
		if (LogUtil.getInstance().getLogger(MongodbUtil.class).isDebugEnabled()) {
			LogUtil.getInstance().getLogger(MongodbUtil.class).debug("MongoDfsDb() - start "); //$NON-NLS-1$
		}
		System.out.println("启动文件存储数据库：{}" + InitMongo.DB_SERVER_HOST + ":" + InitMongo.FS_SERVER_PORT);
		LogUtil.getInstance().getLogger(this.getClass()).info("启动单机存储文件数据库：{}",InitMongo.DB_SERVER_HOST);
		mg = new MongoClient(InitMongo.FS_SERVER_HOST, InitMongo.FS_SERVER_PORT);
		
		dbs.put(InitMongo.FS_J9STAR2, getDB(InitMongo.FS_J9STAR2));
	}

	/**
	 * getDB:(获取数据库). 
	 * @param databasename
	 * @return
	 */
	public DB getDB(String databasename){
		DB db = dbs.get(databasename);
		if (db==null) {
			db = mg.getDB(databasename);
			dbs.put(databasename, db);
		}
		return db;
	}
	/**
	 * dropDB:(删除数据库). 
	 * @param databasename
	 * @return
	 */
	public void dropDB(String databasename){
		DB db = dbs.get(databasename);
		if (db==null) {
			db = mg.getDB(databasename);
			dbs.put(databasename, db);
		}
		dbs.remove(databasename);
		db.dropDatabase();
	}
	
	/**
	 * 判断数据库是否存在
	 * @param databasename
	 * @return
	 */
	public boolean isExistDB(String databasename){
		List<String> databaseNames = mg.getDatabaseNames();
		if (databaseNames.contains(databasename)) {
			return true;
		}
		return false;
	}
	/**
	 * getGridFS:(获取文件库集合). 
	 * @author 
	 * @param databasename
	 * @param collectionname
	 * @return
	 */
	public GridFS getGridFS(String databasename){
		GridFS gridFS = gridFSs.get(databasename);
		if (gridFS==null) {
			DB db = getDB(databasename);
			gridFS = new GridFS(db);
			gridFSs.put(databasename, gridFS);
		}
		return gridFS;
	}
	
	@Override
	public boolean upload(String mongoFSUUId, String filePath) throws Exception {
		return this.upload(InitMongo.DATABASE_TEST, mongoFSUUId, filePath);
	}

	@Override
	public boolean upload(String databaseName, String mongoFSUUId,
			String filePath) throws Exception {
		GridFS gridFS = this.getGridFS(databaseName);
//		// 查找条件
//		DBObject query = new BasicDBObject("uuId","fb9ade04cacd4ab6a57f192b829b4048");
//		// 查询的结果：
//		GridFSDBFile gridDBFile = gridFS.findOne(query);
//		if(gridDBFile != null){
//			System.out.println("文件已存在。。");
//			return;
//		}
		File f = new File(filePath);
		GridFSInputFile inputFile = gridFS.createFile(f);
		// 可以再添加属性
		inputFile.put("uploadDate", new Date());
		inputFile.put("uuId", mongoFSUUId);
		inputFile.put("filename", f.getName());
		inputFile.save();
//		System.out.println("fileId: " + inputFile.getId().toString() +"---"+ inputFile.getId().toString().length());
//		System.out.println("md5: " + inputFile.getMD5() + "---" + inputFile.getMD5().length());
		return true;
		
	}

	@Override
	public boolean upload(String fileName,String mongoFSUUId, InputStream in){
		return this.upload(InitMongo.DATABASE_TEST, fileName, mongoFSUUId, in);
	}
	
	@Override
	public boolean upload(String databaseName, String fileName,
			String mongoFSUUId, InputStream in) {
		GridFS gridFS = this.getGridFS(databaseName);
		// 查找条件
		DBObject query = new BasicDBObject("uuId",mongoFSUUId);
		// 查询的结果：
		GridFSDBFile gridDBFile = gridFS.findOne(query);
		if(gridDBFile != null){
			System.out.println("文件已存在2");
			return false;
		}
		GridFSInputFile inputFile = gridFS.createFile(in);
		// 可以再添加属性
		inputFile.put("uploadDate", new Date());
		inputFile.put("uuId", mongoFSUUId);
		inputFile.put("filename", fileName);
		inputFile.save();
//		System.out.println("fileId: " + inputFile.getId().toString());
//		System.out.println("getFilename: " + inputFile.getFilename());
//		System.out.println("getLength: " + inputFile.getLength());
		return true;
	}

	@Override
	public boolean downLoadToLocal(String mongoFSUUId, String localPath) throws Exception {
		return this.downLoadToLocal(InitMongo.DATABASE_TEST, mongoFSUUId, localPath);
	}

	@Override
	public boolean downLoadToLocal(String databaseName, String mongoFSUUId,
			String localPath) throws Exception {
		GridFS gridFS = this.getGridFS(databaseName);
		// 查找条件
		DBObject query = new BasicDBObject();
		query.put("uuId", mongoFSUUId);
//		query.put("_id", path);
		// 查询的结果：
		GridFSDBFile gridDBFile = gridFS.findOne(query);
		if(gridDBFile == null){
			return false;
		}
		// 获得其中的文件名
		// 注意 ： 不是fs中的表的列名，而是根据调试gridDBFile中的属性而来
		String fileName = (String) gridDBFile.get("filename");

//		System.out.println("从Mongodb获得文件名为：" + fileName);
		// 如果sPath不以文件分隔符结尾，自动添加文件分隔符
		if (!localPath.endsWith(File.separator)) {
			localPath = localPath + File.separator;
		}
		
		File writeDir = new File(localPath);
		if (!writeDir.exists()) {
			writeDir.mkdirs();
		}		
		FileOutputStream out2 = new FileOutputStream(localPath + fileName);
		OutputStream os2 = new BufferedOutputStream(out2);

		// 把数据写入磁盘中
		// 查看相应的提示
//		gridDBFile.writeTo("d:/temp/a.txt");
		// 写入文件中
		gridDBFile.writeTo(os2);
		os2.flush();
		os2.close();
		out2.close();
		return true;
	}
	
	@Override
	public InputStream downLoadToStream(String mongoFSUUId) {
		return this.downLoadToStream(InitMongo.DATABASE_TEST, mongoFSUUId);
	}

	@Override
	public InputStream downLoadToStream(String databaseName, String mongoFSUUId) {
		GridFS gridFS = this.getGridFS(databaseName);
		// 查找条件
		DBObject query = new BasicDBObject();
		query.put("uuId", mongoFSUUId);
		// 查询的结果：
		GridFSDBFile gridDBFile = gridFS.findOne(query);
		return gridDBFile.getInputStream();
	}

	@Override
	public boolean delete(String mongoFSUUId){
		return this.delete(InitMongo.DATABASE_TEST, mongoFSUUId);
	}
	
	@Override
	public boolean delete(String databaseName, String mongoFSUUId) {
		GridFS gridFS = this.getGridFS(databaseName);
		// 查找条件
		DBObject query = new BasicDBObject();
		query.put("uuId", mongoFSUUId);
		// 查询的结果：
//		GridFSDBFile gridDBFile = gridFS.findOne(query);
//		gridDBFile.removeField(key)
		gridFS.remove(query);
		return true;
	}
	
	@Override
	public boolean rename(String src, String dst) throws Exception {
		return false;		
	}

	@Override
	public boolean mkdir(String dir) throws Exception {
		return false;
	}
	
	@Override
	public boolean copy(String[] path, String dst, boolean flag) throws Exception {
		return flag;
	}
	@Override
	public void showList() {
		GridFS gridFS = this.getGridFS(InitMongo.FS_J9STAR2);
		// 查找条件
		DBObject query = new BasicDBObject();
		// 查询的结果：
		List<GridFSDBFile> listfiles = gridFS.find(query);
		for (GridFSDBFile fs : listfiles) {
			System.out.println(fs);
		}
	}


}
