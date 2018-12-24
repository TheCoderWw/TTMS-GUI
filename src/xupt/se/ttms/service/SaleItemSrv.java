package xupt.se.ttms.service;

import java.util.List;

import xupt.se.ttms.idao.DAOFactory;
import xupt.se.ttms.idao.iSaleItemDAO;
import xupt.se.ttms.model.SaleItem;

public class SaleItemSrv {
	private iSaleItemDAO saleItemDAO = DAOFactory.creatSaleItemDAO();
	
	public int add(SaleItem saleItem) {
		return saleItemDAO.insert(saleItem);
	}
	
	public List<SaleItem> Fetch(String condt) {
		return saleItemDAO.select(condt);
	}
	
	public List<SaleItem> FetchAll() {
		return saleItemDAO.select("");
	}
}
