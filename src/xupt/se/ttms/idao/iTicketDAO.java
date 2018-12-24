package xupt.se.ttms.idao;

import java.util.List;

import xupt.se.ttms.model.Ticket;

public interface iTicketDAO {
	public List<Ticket> select(String condt);

	public boolean insert(Ticket ticket);

	public int update(Ticket ticket);
}
