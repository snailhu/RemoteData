package DataAn.routing;

import java.io.Serializable;

public interface Repo extends Serializable {

	/**
	 * 
	 * @return  milliseconds .
	 */
	long period();
	
	String name();
	
	/**
	 * db_j9_star2
	 * @return
	 */
	String database();
	
	/**
	 * db_j9_star2
	 * @return
	 */
	String collection();
	
	
}
