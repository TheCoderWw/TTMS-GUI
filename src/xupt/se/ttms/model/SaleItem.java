package xupt.se.ttms.model;

public class SaleItem {
	
	public int sale_item_id;
	public int ticket_id;
	public int sale_ID;
	public Double sale_item_price;
	
	public SaleItem() {

	}
	
	public SaleItem(int ticket_id, int sale_ID, Double sale_item_price) {
		super();
		this.ticket_id = ticket_id;
		this.sale_ID = sale_ID;
		this.sale_item_price = sale_item_price;
	}

	public int getSale_item_id() {
		return sale_item_id;
	}

	public void setSale_item_id(int sale_item_id) {
		this.sale_item_id = sale_item_id;
	}

	public int getTicket_id() {
		return ticket_id;
	}
	public void setTicket_id(int ticket_id) {
		this.ticket_id = ticket_id;
	}
	public int getSale_ID() {
		return sale_ID;
	}
	public void setSale_ID(int sale_ID) {
		this.sale_ID = sale_ID;
	}
	public Double getSale_item_price() {
		return sale_item_price;
	}
	public void setSale_item_price(Double sale_item_price) {
		this.sale_item_price = sale_item_price;
	}
	@Override
	public String toString() {
		return "sale_item [ticket_id=" + ticket_id + ", sale_ID=" + sale_ID + ", sale_item_price=" + sale_item_price
				+ "]";
	}
	
}
