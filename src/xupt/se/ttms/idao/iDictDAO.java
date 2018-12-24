package xupt.se.ttms.idao;

import java.util.List;

import xupt.se.ttms.model.Dict;

public interface iDictDAO {
	public List<Dict> select(String condt);

	public List<Dict> selectByParentName(String parent_name);

	public List<Dict> selectByID(int dict_id);
}
