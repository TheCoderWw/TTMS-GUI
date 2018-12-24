package xupt.se.ttms.dao;

import java.sql.ResultSet;
import java.util.LinkedList;
import java.util.List;

import xupt.se.ttms.idao.iSaleDAO;
import xupt.se.ttms.model.Sale;
import xupt.se.util.DBUtil;

public class SaleDAO implements iSaleDAO {

	@Override
	public int insert(Sale sale) {
		try {
			String sql = "insert into sale( user_id ,sale_time ,sale_payment)" + " values("
					+ sale.getUser_id() + ", '" + sale.getSale_time() + "', "
					+ sale.getSale_payment() + " )";
			DBUtil db = new DBUtil();
			db.openConnection();
			ResultSet rst = db.getInsertObjectIDs(sql);
			if (rst != null && rst.first()) {
				sale.setSale_ID(rst.getInt(1));
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
	public List<Sale> select(String condt) {
		List<Sale> saleList = null;
		saleList = new LinkedList<Sale>();
		try {
			String sql = "select * from sale ";
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
					Sale sale = new Sale();
					sale.setSale_ID(rst.getInt("sale_id"));
					sale.setSale_payment(rst.getInt("sale_payment"));
					sale.setSale_time(rst.getString("sale_time"));
					sale.setUser_id(rst.getInt("user_id"));
					saleList.add(sale);
				}
			}
			db.close(rst);
			db.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
	
		}

		return saleList;
	}

}
