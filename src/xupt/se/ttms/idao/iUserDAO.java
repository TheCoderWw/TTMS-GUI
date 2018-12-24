package xupt.se.ttms.idao;

import xupt.se.ttms.model.User;

public interface iUserDAO {
	public boolean select(User user);

	public boolean insert(User user);

	public boolean update(User user);

	public boolean select(String userName);

	public int selectMoney(String userName);

	public int selectID(String userName);
	// public boolea
}
