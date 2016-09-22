package DataAn.routing;

import java.util.List;

public class DefaultRepo implements Repo {

	private long period;
	
	private String name;
	
	private String database;
	
	private String collection;
	
	private int index;
	
	private List<? extends Repo> repos;  
	
	@Override
	public long period() {
		return period;
	}

	@Override
	public String name() {
		return name;
	}

	@Override
	public String database() {
		return database;
	}

	@Override
	public String collection() {
		return collection;
	}

	public long getPeriod() {
		return period;
	}

	public void setPeriod(long period) {
		this.period = period;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDatabase() {
		return database;
	}

	public void setDatabase(String database) {
		this.database = database;
	}

	public String getCollection() {
		return collection;
	}

	public void setCollection(String collection) {
		this.collection = collection;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}
	@Override
	public int index() {
		return index;
	}

	@Override
	public Repo ahead() {
		if(index==0){
			return null;
		}
		return repos.get(index-1);
	}

	@Override
	public Repo next() {
		if(index==repos.size()-1){
			return null;
		}
		return repos.get(index+1);
	}
	
	
}
