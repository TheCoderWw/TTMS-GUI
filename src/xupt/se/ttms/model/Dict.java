package xupt.se.ttms.model;

public class Dict {
	int dict_id;
	int dict_parent_id;
	int dict_index;
	String dict_name;
	String dict_value;
	
	
	
	public int getDict_id() {
		return dict_id;
	}
	public void setDict_id(int dict_id) {
		this.dict_id = dict_id;
	}
	public int getDict_parent_id() {
		return dict_parent_id;
	}
	public void setDict_parent_id(int dict_parent_id) {
		this.dict_parent_id = dict_parent_id;
	}
	public int getDict_index() {
		return dict_index;
	}
	
	public String toString() {
		return "Dict [dict_id=" + dict_id + ", dict_parent_id=" + dict_parent_id + ", dict_index=" + dict_index
				+ ", dict_name=" + dict_name + ", dict_value=" + dict_value + "]";
	}
	public void setDict_index(int dict_index) {
		this.dict_index = dict_index;
	}
	public String getDict_name() {
		return dict_name;
	}
	public void setDict_name(String dict_name) {
		this.dict_name = dict_name;
	}
	public String getDict_value() {
		return dict_value;
	}
	public void setDict_value(String dict_value) {
		this.dict_value = dict_value;
	}
	
	
}
