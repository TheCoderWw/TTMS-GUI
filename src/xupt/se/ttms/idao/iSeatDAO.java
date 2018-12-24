package xupt.se.ttms.idao;

import java.util.List;

import xupt.se.ttms.model.Seat;


public interface iSeatDAO {
	
	public int insert(Seat seat);

	public int update(Seat seat);

	public int delete(int ID);

	public List<Seat> select(String condt);

	public int init(int stu_id, int row, int col);

	public int deleteByStudio(int stu_id);
}
