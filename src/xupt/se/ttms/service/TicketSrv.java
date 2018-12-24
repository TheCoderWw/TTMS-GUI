package xupt.se.ttms.service;

import java.util.List;

import xupt.se.ttms.idao.DAOFactory;
import xupt.se.ttms.idao.iTicketDAO;
import xupt.se.ttms.model.Ticket;

public class TicketSrv {
	private iTicketDAO ticketDAO = DAOFactory.creatTicketDAO();

	public boolean add(Ticket ticket) {
		return ticketDAO.insert(ticket);
	}

	public List<Ticket> Fetch(String condt) {
		return ticketDAO.select(condt);
	}

	public List<Ticket> FetchAll() {
		return ticketDAO.select("");
	}

	public int modify(Ticket ticket) {
		return ticketDAO.update(ticket);
	}
}
