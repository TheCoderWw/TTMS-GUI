package xupt.se.ttms.idao;

import xupt.se.ttms.model.Employee;

public interface iEmployeeDAO {
	public boolean select(Employee employee);

	public boolean update(Employee employee);

	public int select(String employeeName);
}
