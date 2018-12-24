package xupt.se.ttms.model;

public class Sale {
	private int sale_ID;
	private int user_id;//数据库中emp_id
	private String sale_time;
	private int sale_payment; //表示付款，无需找零
//	private int sale_type;//暂时无用
//	private int sale_status;//暂时无用


	public Sale(int user_id, String sale_time, int sale_payment) {
		this.user_id = user_id;
		this.sale_time = sale_time;
		this.sale_payment = sale_payment;
	}
	
	public Sale() {
	}

	public int getSale_ID() {
		return sale_ID;
	}
	public void setSale_ID(int sale_ID) {
		this.sale_ID = sale_ID;
	}
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	public String getSale_time() {
		return sale_time;
	}
	public void setSale_time(String sale_time) {
		this.sale_time = sale_time;
	}
	public int getSale_payment() {
		return sale_payment;
	}
	public void setSale_payment(int sale_payment) {
		this.sale_payment = sale_payment;
	}

	@Override
	public String toString() {
		return "Sale [sale_ID=" + sale_ID + ", user_id=" + user_id + ", sale_time=" + sale_time + ", sale_payment="
				+ sale_payment + "]";
	}

	
}
