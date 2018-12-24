package xupt.se.ttms.dao;

import java.sql.ResultSet;

import xupt.se.ttms.idao.iUserDAO;
import xupt.se.ttms.model.User;
import xupt.se.util.DBUtil;

public class UserDAO implements iUserDAO {

	@Override
	public boolean select(User user) {

		boolean i = false;
		// TODO Auto-generated method stub
		try {
			String sql = "select passWord from userinfo where userName = \"" + user.getUserName() + "\"";
			DBUtil db = new DBUtil();
			if (!db.openConnection()) {
				System.out.print("fail to connect database");
				return false;
			}
			ResultSet rst = null;
			rst = db.execQuery(sql);
			if (rst != null) {
				while (rst.next()) {
					String pass = rst.getString(1);
					if (pass.equals(user.getPassWord())) {
						i = true;
					}
				}
			}
			db.close(rst);
			db.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return i;
	}

	@Override
	public boolean insert(User user) {
		// TODO Auto-generated method stub
		boolean i = false;
		String sql = "insert into userinfo(userName,passWord,user_money) values('" + user.getUserName() + "','"
				+ user.getPassWord() + "'," + user.getUserMoney() + ")";
		DBUtil db = new DBUtil();
		if (!db.openConnection()) {
			System.out.print("fail to connect database");
			return false;
		}
		try {
			ResultSet rst = db.getInsertObjectIDs(sql);
			i = true;
			db.close(rst);
			db.close();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return i;
	}

	@Override
	public boolean update(User user) {
		boolean i = false;
		String sql = "update userinfo set passWord = '" + user.getPassWord() + "', user_money = '" + user.getUserMoney()
		// TODO Auto-generated method stub
				+ "' where userName = '" + user.getUserName() + "'";
		DBUtil db = new DBUtil();
		if (!db.openConnection()) {
			System.out.print("fail to connect database");
			return false;
		}
		try {
			db.execCommand(sql);
			i = true;
			db.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return i;
	}

	@Override
	public boolean select(String userName1) {
		// TODO Auto-generated method stub
		boolean i = false;
		// TODO Auto-generated method stub
		try {
			String sql = "select userName from userinfo where userName = \"" + userName1 + "\"";
			DBUtil db = new DBUtil();
			if (!db.openConnection()) {
				System.out.print("fail to connect database");
				return false;
			}
			ResultSet rst = null;
			rst = db.execQuery(sql);
			if (rst != null) {
				while (rst.next()) {
					String userN = rst.getString(1);
					if (userN.equals(userName1)) {
						i = true;
					}
				}
			}
			db.close(rst);
			db.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return i;
	}

	@Override
	public int selectMoney(String userName) {
		// TODO Auto-generated method stub
		int i = 0;
		try {
			String sql = "select user_money from userinfo where userName = \"" + userName + "\"";
			DBUtil db = new DBUtil();
			if (!db.openConnection()) {
				System.out.print("fail to connect database");
				return 0;
			}
			ResultSet rst = null;

			rst = db.execQuery(sql);

			if (rst != null) {
				while (rst.next()) {
					i = rst.getInt(1);
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return i;
	}

	@Override
	public int selectID(String userName) {
		// TODO Auto-generated method stub
		int i = 0;
		try {
			String sql = "select userId from userinfo where userName = \"" + userName + "\"";
			DBUtil db = new DBUtil();
			if (!db.openConnection()) {
				System.out.print("fail to connect database");
				return 0;
			}
			ResultSet rst = null;

			rst = db.execQuery(sql);

			if (rst != null) {
				while (rst.next()) {
					i = rst.getInt(1);
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return i;
	}

}
