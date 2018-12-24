package xupt.se.ttms.idao;

import xupt.se.ttms.dao.DictDAO;
import xupt.se.ttms.dao.EmployeeDAO;
import xupt.se.ttms.dao.PlayDAO;
import xupt.se.ttms.dao.SaleDAO;
import xupt.se.ttms.dao.SaleItemDAO;
import xupt.se.ttms.dao.ScheduleDAO;
import xupt.se.ttms.dao.SeatDAO;
import xupt.se.ttms.dao.StudioDAO;
import xupt.se.ttms.dao.TicketDAO;
import xupt.se.ttms.dao.UserDAO;

public class DAOFactory {
	public static iStudioDAO creatStudioDAO() {
		return new StudioDAO();
	}

	public static iUserDAO creatUserDAO() {
		return new UserDAO();
	}

	public static iEmployeeDAO creatEmployeeDAO() {
		return new EmployeeDAO();
	}

	public static iSeatDAO creatSeatDAO() {

		return new SeatDAO();
	}

	public static iPlayDAO creatPlayDAO() {
		return new PlayDAO();
	}

	public static iDictDAO creatDictDAO() {
		return new DictDAO();
	}

	public static iScheduleDAO creatScheduleDAO() {
		return new ScheduleDAO();
	}
	
	public static iTicketDAO creatTicketDAO() {
		return new TicketDAO();
	}
	
	public static iSaleDAO creatSaleDAO() {
		return new SaleDAO();
	}

	public static iSaleItemDAO creatSaleItemDAO() {
		return new SaleItemDAO();
	}
}
