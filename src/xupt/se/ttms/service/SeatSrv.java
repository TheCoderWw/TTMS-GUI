package xupt.se.ttms.service;

import java.util.List;

import xupt.se.ttms.idao.DAOFactory;
import xupt.se.ttms.idao.iSeatDAO;
import xupt.se.ttms.model.Seat;

public class SeatSrv {
	private iSeatDAO seatDAO=DAOFactory.creatSeatDAO();
	
	public int initSeat(int stu_id,int row,int col) {
		return seatDAO.init(stu_id,row,col);
	}
	
	public int add(Seat seat){
		return seatDAO.insert(seat);	
	}
	
	public int modify(Seat seat){
		return seatDAO.update(seat); 		
	}
	
	public int delete(int ID){
		return seatDAO.delete(ID); 	
	}
	
	public List<Seat> Fetch(String condt){
		return seatDAO.select(condt);		
	}
	
	public List<Seat> FetchAll(){
		return seatDAO.select("");		
	}
	
	public int deleteByStudio(int stu_id) {
		return seatDAO.deleteByStudio(stu_id);
	}
}
