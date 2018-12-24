package xupt.se.ttms.dao;

import java.sql.ResultSet;
import java.util.LinkedList;
import java.util.List;

import xupt.se.ttms.idao.iTicketDAO;
import xupt.se.ttms.model.Ticket;
import xupt.se.util.DBUtil;

public class TicketDAO implements iTicketDAO {

	@Override
	public List<Ticket> select(String condt) {
		List<Ticket> ticketList = null;
		ticketList = new LinkedList<Ticket>();
		try {
			String sql = "select * from ticket ";
			condt.trim();
			if (!condt.isEmpty())
				sql += " where " + condt;
			DBUtil db = new DBUtil();
			if (!db.openConnection()) {
				System.out.print("fail to connect database");
				return null;
			}
			ResultSet rst = db.execQuery(sql);
			if (rst != null) {
				while (rst.next()) {
					Ticket ticket = new Ticket();
					ticket.setTicket_id(rst.getInt("ticket_id"));
					ticket.setSeat_id(rst.getInt("seat_id"));
					ticket.setSched_id(rst.getInt("sched_id"));
					ticket.setTicket_price(rst.getDouble("ticket_price"));
					ticket.setTicket_status(rst.getInt("ticket_status"));
					ticket.setTicket_locked_time(rst.getString("ticket_locked_time"));
					ticketList.add(ticket);
				}
			}
			db.close(rst);
			db.close();
		} catch (

		Exception e) {
			e.printStackTrace();
		} finally {

		}

		return ticketList;
	}

	@Override
	public boolean insert(Ticket ticket) {
		// TODO Auto-generated method stub
		try {
			String sql = "insert into ticket(seat_id, sched_id, ticket_price )" + " values(" + ticket.getSeat_id()
					+ ", " + ticket.getSched_id() + ", " + ticket.getTicket_price() + " )";
			DBUtil db = new DBUtil();
			db.openConnection();
			ResultSet rst = db.getInsertObjectIDs(sql);
			if (rst != null && rst.first()) {
				ticket.setTicket_id(rst.getInt(1));
			}

			db.close(rst);
			db.close();
			return true;

		} catch (Exception e) {

			e.printStackTrace();

		}
		return false;
	}

	public int update(Ticket ticket) {
		int rtn = 0;
		try {
			String sql = "update ticket set " + " seat_id =" + ticket.getSeat_id() + ", " + " sched_id = "
					+ ticket.getSched_id() + ", " + " ticket_price = " + ticket.getTicket_price() + ", "
					+ " ticket_status = " + ticket.getTicket_status() + "," + " ticket_locked_time = '"
					+ ticket.getTicket_locked_time() + "'";

			sql += " where ticket_id = " + ticket.getTicket_id();
			DBUtil db = new DBUtil();
			db.openConnection();
			rtn = db.execCommand(sql);
			db.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return rtn;
	}

}
