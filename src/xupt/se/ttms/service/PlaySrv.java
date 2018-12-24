package xupt.se.ttms.service;

import java.util.List;

import xupt.se.ttms.idao.DAOFactory;
import xupt.se.ttms.idao.iPlayDAO;
import xupt.se.ttms.model.Play;

public class PlaySrv {
	private iPlayDAO playDAO = DAOFactory.creatPlayDAO();

	public int add(Play play) {
		return playDAO.insert(play);
	}

	public int modify(Play play) {
		return playDAO.update(play);
	}

	public int delete(int ID) {
		return playDAO.delete(ID);
	}

	public List<Play> Fetch(String condt) {
		return playDAO.select(condt);
	}

	public List<Play> FetchAll() {
		return playDAO.select("");
	}
}
