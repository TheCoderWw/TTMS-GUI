package xupt.se.ttms.dao;

import java.sql.ResultSet;
import java.util.LinkedList;
import java.util.List;

import xupt.se.ttms.idao.iSeatDAO;
import xupt.se.ttms.model.Seat;
import xupt.se.util.DBUtil;

public class SeatDAO implements iSeatDAO{
	public int insert(Seat seat) {
		try {
			String sql = "insert into seat(studio_id, seat_row, seat_col,seat_status,seat_num)"
					+ " values(" + seat.getStudioId() + ", " + seat.getRow() + ", " + seat.getColumn()
					+", " + seat.getSeatStatus() +", "+ seat.getNum() + " )";
			DBUtil db = new DBUtil();
			db.openConnection();
			ResultSet rst = db.getInsertObjectIDs(sql);
			if (rst != null && rst.first()) {
				seat.setId(rst.getInt(1));
			}
			db.close(rst);
			db.close(); 
			return 1;

		} catch (Exception e) {
			e.printStackTrace();
		}

		return 0;
	}

	@Override
	public int update(Seat seat) {
		int rtn = 0;
		try {
			String sql = "update seat set " + 
					" seat_num =" + seat.getNum() +
					", studio_id =" + seat.getStudioId() +
					", seat_row = " + seat.getRow() + 
					", seat_col = " + seat.getColumn() +
					", seat_status =" + seat.getSeatStatus();

			sql += " where seat_id = " + seat.getId();
			DBUtil db = new DBUtil();
			db.openConnection();
			rtn = db.execCommand(sql);
			db.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rtn;
	}

	@Override
	public int delete(int ID) {
		int rtn = 0;
		try {
			String sql = "delete from  seat ";
			sql += " where seat_id = " + ID;
			DBUtil db = new DBUtil();
			db.openConnection();
			rtn = db.execCommand(sql);
			db.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rtn;
	}
	
	public List<Seat> select(String condt) {
		List<Seat> seatList = null;
		seatList = new LinkedList<Seat>();
		try {
			String sql = "select * from seat ";
			condt.trim();
			if (!condt.isEmpty())
				sql+= " where " + condt;
			DBUtil db = new DBUtil();
			if (!db.openConnection()) {
				System.out.print("fail to connect database");
				return null;
			}
			ResultSet rst = db.execQuery(sql);
			if (rst != null) {
				while (rst.next()) {
					Seat seat = new Seat();
					seat.setId(rst.getInt("seat_id"));
					seat.setStudioId(rst.getInt("studio_id"));
					seat.setRow(rst.getInt("seat_row"));
					seat.setColumn(rst.getInt("seat_col"));
					seat.setSeatStatus(rst.getInt("seat_status"));
					seat.setNum(rst.getInt("seat_num"));
					seatList.add(seat);
				}
			}
			db.close(rst);
			db.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {

		}

		return seatList;
	}

	//跟据传入的行列数初始化座位信息，并插入到数据库
	public int init(int stu_id,int row, int col) {
		for(int i = 1; i < row + 1;i++) {
			for(int j = 1; j < col + 1; j++) {
				int seat_num = (i-1) * col + j;
				Seat seat = new Seat(seat_num,stu_id,i,j,0);
				insert(seat);
			}
		}
		return 0;
	}


	public int deleteByStudio(int stu_id) {
		int rtn = 0;
		try {
			String sql = "delete from  seat ";
			sql += " where studio_id = " + stu_id;
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
