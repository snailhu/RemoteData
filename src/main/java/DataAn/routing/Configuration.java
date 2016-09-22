package DataAn.routing;

import java.io.Serializable;

public class Configuration implements Serializable{

	private long canvasPointNum=50000;

	public long getCanvasPointNum() {
		return canvasPointNum;
	}

	public void setCanvasPointNum(long canvasPointNum) {
		this.canvasPointNum = canvasPointNum;
	}
	
	
	
}
