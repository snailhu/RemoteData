package DataAn.mongo.service;

import java.io.InputStream;

public interface IMongoGridFSService {

	public abstract InputStream downLoadToStream(String mongoFSUUId);
}
