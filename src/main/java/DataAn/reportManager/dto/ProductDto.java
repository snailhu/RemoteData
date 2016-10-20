package DataAn.reportManager.dto;

import java.util.List;

public class ProductDto {

	private int MovableNum;//机动次数
	
	private String productName;//产品名称
	
	public int getMovableNum() {
		return MovableNum;
	}

	public void setMovableNum(int movableNum) {
		MovableNum = movableNum;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}
}
