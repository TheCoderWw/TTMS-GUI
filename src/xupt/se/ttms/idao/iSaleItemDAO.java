package xupt.se.ttms.idao;

import java.util.List;

import xupt.se.ttms.model.SaleItem;

public interface iSaleItemDAO {
	public int insert(SaleItem saleItem);
	public List<SaleItem> select(String condt);
}
