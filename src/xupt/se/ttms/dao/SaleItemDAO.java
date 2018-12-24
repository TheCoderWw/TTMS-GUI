package xupt.se.ttms.dao;

import java.sql.ResultSet;
import java.util.LinkedList;
import java.util.List;

import xupt.se.ttms.idao.iSaleItemDAO;
import xupt.se.ttms.model.SaleItem;
import xupt.se.util.DBUtil;

public class SaleItemDAO  implements iSaleItemDAO{

	@Override
	public int insert(SaleItem saleItem) {
		try {
			String sql = "insert into sale_item(ticket_id , sale_ID, sale_item_price)" + " values("
					+ saleItem.getTicket_id() + ", " + saleItem.getSale_ID() + ", "
					+ saleItem.getSale_item_price() + " )";
			DBUtil db = new DBUtil();
			db.openConnection();
			ResultSet rst = db.getInsertObjectIDs(sql);
			if (rst != null && rst.first()) {
				saleItem.setSale_item_id(rst.getInt(1));
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
	public List<SaleItem> select(String condt) {
		List<SaleItem> saleItemList = null;
		saleItemList = new LinkedList<SaleItem>();
		try {
			String sql = "select * from sale_item ";
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
					SaleItem saleItem = new SaleItem();
					saleItem.setSale_item_id(rst.getInt("sale_item_id"));
					saleItem.setSale_ID(rst.getInt("sale_ID"));
					saleItem.setTicket_id(rst.getInt("ticket_id"));
					saleItem.setSale_item_price(rst.getDouble("sale_item_price"));
					saleItemList.add(saleItem);
				}
			}
			db.close(rst);
			db.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
	
		}
	
		return saleItemList;
	}

}
