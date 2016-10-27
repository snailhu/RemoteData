package DataAn.routing;

import java.io.Serializable;

public class Configuration implements Serializable{

	private int canvasPointNum=50000;
	
	private int perPointNum=10000;

	private int expectedPerPointNum=perPointNum;

	public int getCanvasPointNum() {
		return canvasPointNum;
	}

	public void setCanvasPointNum(int canvasPointNum) {
		this.canvasPointNum = canvasPointNum;
	}

	public int getPerPointNum() {
		return perPointNum;
	}

	public void setPerPointNum(int perPointNum) {
		this.perPointNum = perPointNum;
	}

	public int getExpectedPerPointNum() {
		return expectedPerPointNum;
	}

	public void setExpectedPerPointNum(int expectedPerPointNum) {
		this.expectedPerPointNum = expectedPerPointNum;
	}
	
	
}
