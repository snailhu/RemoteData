package DataAn.Analysis.dto;

import java.io.Serializable;
import java.util.List;

public class GroupMenu implements Serializable{
	
	private String id;
	
	private String text;
	
	private String icon;
	
	private String url;
	
	private List<GroupMenu> menus;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public List<GroupMenu> getMenus() {
		return menus;
	}

	public void setMenus(List<GroupMenu> menus) {
		this.menus = menus;
	}
	
	
}
