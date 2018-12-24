package xupt.se.ttms.dao;

import java.sql.ResultSet;
import java.util.LinkedList;
import java.util.List;

import xupt.se.ttms.idao.iPlayDAO;
import xupt.se.ttms.model.Play;
import xupt.se.util.DBUtil;

public class PlayDAO implements iPlayDAO {

	@Override
	public int insert(Play play) {
		// TODO Auto-generated method stub
		try {
			System.out.println(play.getPlay_type_id());
			String sql = "insert into play(play_type_id, play_lang_id,play_name,"
					+ " play_introduction,play_image,play_length,play_ticket_price,play_status)" + " values("
					+ play.getPlay_type_id() + "," + play.getLang_id() + ",'" + play.getPlay_name() + "','"
					+ play.getPlay_introduction() + "','" + play.getPlay_image() + "'," + play.getPlay_length() + ","
					+ play.getPlay_ticket_price() + "," + play.getPlay_status() + ")";
			DBUtil db = new DBUtil();
			db.openConnection();
			ResultSet rst = db.getInsertObjectIDs(sql);
			if (rst != null && rst.first()) {
				play.setPlay_id(rst.getInt(1));
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
	public int update(Play play) {
		int rtn = 0;
		try {
			String sql = "update play set " + " play_type_id =" + play.getPlay_type_id() + ", " + " play_lang_id = "
					+ play.getLang_id() + ", " + " play_name = '" + play.getPlay_name() + "', "
					+ " play_introduction = '" + play.getPlay_introduction() + "', " + "play_image = '"
					+ play.getPlay_image() + "'," + "play_length = " + play.getPlay_length() + ","
					+ "play_ticket_price = " + play.getPlay_ticket_price() + "," + "play_status = "
					+ play.getPlay_status();

			sql += " where play_id = " + play.getPlay_id();
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
			String sql = "update play set play_status = 0";
			sql += " where play_id = " + ID;
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
	public List<Play> select(String playtem) {

		List<Play> playList = null;
		playList = new LinkedList<Play>();
		try {
			String sql = "select play_id,play_type_id, play_lang_id, play_name, play_introduction,"
					+ " play_image, play_length,play_ticket_price,play_status from play ";
			playtem.trim();
			if (!playtem.isEmpty())
				sql += " where " + playtem;
			DBUtil db = new DBUtil();
			if (!db.openConnection()) {
				System.out.print("fail to connect database");
				return null;
			}
			ResultSet rst = db.execQuery(sql);
			if (rst != null) {
				while (rst.next()) {
					if (rst.getInt(9) == 1) {
						Play play = new Play();
						play.setPlay_id(rst.getInt("play_id"));
						play.setPlay_type_id(rst.getInt("play_type_id"));
						play.setLang_id(rst.getInt("play_lang_id"));
						play.setPlay_name(rst.getString("play_name"));
						play.setPlay_introduction(rst.getString("play_introduction"));
						play.setPlay_image(rst.getString("play_image"));
						play.setPlay_length(rst.getInt("play_length"));
						play.setPlay_ticket_price(rst.getInt("play_ticket_price"));
						play.setPlay_status(rst.getInt("play_status"));
						playList.add(play);
					}
				}

			}
			db.close(rst);
			db.close();
		} catch (

		Exception e) {
			e.printStackTrace();
		} finally {

		}

		return playList;
	}

}
