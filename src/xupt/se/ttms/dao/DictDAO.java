package xupt.se.ttms.dao;

import java.sql.ResultSet;
import java.util.LinkedList;
import java.util.List;

import xupt.se.ttms.idao.iDictDAO;
import xupt.se.ttms.model.Dict;
import xupt.se.util.DBUtil;

public class DictDAO implements iDictDAO {
	public List<Dict> select(String condt) {
		List<Dict> DictList = null;
		DictList = new LinkedList<Dict>();
		try {
			String sql = "select dict_id, dict_parent_id, dict_index,dict_name,dict_value from data_dict ";
			condt.trim();
			if (!condt.isEmpty())
				sql += " where " + condt;
			DBUtil db = new DBUtil();
			if (!db.openConnection()) {
				System.out.print("fail to connect database");
				return null;
			}
			ResultSet rst = db.execQuery(sql);
			if (rst != null) {
				while (rst.next()) {
					Dict dict = new Dict();
					dict.setDict_id(rst.getInt("dict_id"));
					dict.setDict_parent_id(rst.getInt("dict_parent_id"));
					dict.setDict_index(rst.getInt("dict_index"));
					dict.setDict_name(rst.getString("dict_name"));
					dict.setDict_value(rst.getString("dict_value"));

					DictList.add(dict);
				}
			}
			db.close(rst);
			db.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {

		}

		return DictList;
	}

	public List<Dict> selectByParentName(String parent_name) {
		List<Dict> parentList = select("dict_name = '" + parent_name + "';");
		int parent_id = parentList.get(0).getDict_id();
		return select("dict_parent_id = '" + parent_id + "';");
	}

	public List<Dict> selectByID(int dict_id) {
		List<Dict> itemList = select("dict_id = " + dict_id + ";");
		return itemList;
	}

}
