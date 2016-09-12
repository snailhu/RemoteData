package DataAn.common.pageModel;

public class Combo {
   
	private String text ;

	private String value ;
	
	private boolean selected = false;

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public boolean isSelected() {
		return selected;
	}

	public void setSelected(boolean selected) {
		this.selected = selected;
	}

	@Override
	public String toString() {
		return "Combo [text=" + text + ", value=" + value + ", selected="
				+ selected + "]";
	}
	
}
