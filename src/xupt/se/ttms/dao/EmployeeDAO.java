package xupt.se.ttms.dao;

import java.sql.ResultSet;

import xupt.se.ttms.idao.iEmployeeDAO;
import xupt.se.ttms.model.Employee;
import xupt.se.util.DBUtil;

public class EmployeeDAO implements iEmployeeDAO {

	@Override
	public boolean select(Employee employee) {
		// TODO Auto-generated method stub
		boolean i = false;
		try {
			String sql = "select emp_Pass from employee where emp_name =\"" + employee.getName() + "\"";
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
					if (pass.equals(employee.getPassword())) {
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
	public boolean update(Employee employee) {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public int select(String employeeName) {
		// TODO Auto-generated method stub
		int i = 0;
		try {
			String sql = "select emp_access from employee where emp_name = \"" + employeeName + "\"";
			DBUtil db = new DBUtil();
			if (!db.openConnection()) {
				System.out.print("fail to connect database");
				return i;
			}
			ResultSet rst = null;
			rst = db.execQuery(sql);
			if (rst != null) {
				while (rst.next()) {
					i = rst.getInt(1);
				}
			}
			db.close(rst);
			db.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return i;
	}

}
