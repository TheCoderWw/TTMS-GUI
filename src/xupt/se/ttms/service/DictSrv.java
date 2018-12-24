package xupt.se.ttms.service;

import java.util.List;

import xupt.se.ttms.idao.DAOFactory;
import xupt.se.ttms.idao.iDictDAO;
import xupt.se.ttms.model.Dict;

public class DictSrv {
	private iDictDAO dictDAO = DAOFactory.creatDictDAO();

	public List<Dict> Fetch(String condt) {
		return dictDAO.select(condt);
	}

	public List<Dict> FetchByParentName(String parent_name) {
		return dictDAO.selectByParentName(parent_name);
	}

	public List<Dict> FetchByID(int dict_id) {
		return dictDAO.selectByID(dict_id);
	}

}
