package xupt.se.ttms.idao;

import java.util.List;

import xupt.se.ttms.model.Sale;

public interface iSaleDAO {
	public int insert(Sale sale);
	public List<Sale> select(String condt);
}
