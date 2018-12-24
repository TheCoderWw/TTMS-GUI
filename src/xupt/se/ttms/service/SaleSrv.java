package xupt.se.ttms.service;

import java.util.List;

import xupt.se.ttms.idao.DAOFactory;
import xupt.se.ttms.idao.iSaleDAO;
import xupt.se.ttms.model.Sale;

public class SaleSrv {
	private iSaleDAO saleDAO = DAOFactory.creatSaleDAO();
	
	public int add(Sale sale) {
		return saleDAO.insert(sale);
	}
	
	public List<Sale> Fetch(String condt) {
		return saleDAO.select(condt);
	}
	
	public List<Sale> FetchAll() {
		return saleDAO.select("");
	}
}
