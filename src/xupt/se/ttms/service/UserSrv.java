package xupt.se.ttms.service;

import xupt.se.ttms.idao.DAOFactory;
import xupt.se.ttms.idao.iUserDAO;
import xupt.se.ttms.model.User;

public class UserSrv {
	private iUserDAO userDAO = DAOFactory.creatUserDAO();

	public boolean add(User user) {
		return userDAO.insert(user);
	}

	public boolean select(User user) {
		return userDAO.select(user);
	}

	public boolean select(String userName) {
		return userDAO.select(userName);
	}

	public boolean update(User user) {
		return userDAO.update(user);
	}

	public int selectMoney(String userName) {
		return userDAO.selectMoney(userName);
	}

	public int selectID(String userName) {
		return userDAO.selectID(userName);
	}
}
