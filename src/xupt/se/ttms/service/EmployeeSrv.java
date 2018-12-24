package xupt.se.ttms.service;

import xupt.se.ttms.idao.DAOFactory;
import xupt.se.ttms.idao.iEmployeeDAO;
import xupt.se.ttms.model.Employee;

public class EmployeeSrv {
	private iEmployeeDAO employeeDAO = DAOFactory.creatEmployeeDAO();

	public boolean select(Employee employee) {
		return employeeDAO.select(employee);
	}

	public int select(String employeeName) {
		return employeeDAO.select(employeeName);
	}

	public boolean update(Employee employee) {
		return employeeDAO.select(employee);
	}
}
