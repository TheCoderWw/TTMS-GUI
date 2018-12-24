package xupt.se.ttms.model;

public class Schedule {
	int sched_id;
	int studio_id;
	int play_id;
	String sched_time;
	int sched_status;

	public int getSched_status() {
		return sched_status;
	}

	public void setSched_status(int sched_status) {
		this.sched_status = sched_status;
	}

	public String getSched_time() {
		return sched_time;
	}

	public void setSched_time(String datetime) {
		this.sched_time = datetime;
	}

	public double getSched_ticket_price() {
		return sched_ticket_price;
	}

	public void setSched_ticket_price(double sched_ticket_price) {
		this.sched_ticket_price = sched_ticket_price;
	}

	double sched_ticket_price;

	public int getSched_id() {
		return sched_id;
	}

	public void setSched_id(int sched_id) {
		this.sched_id = sched_id;
	}

	public int getStudio_id() {
		return studio_id;
	}

	public void setStudio_id(int studio_id) {
		this.studio_id = studio_id;
	}

	public int getPlay_id() {
		return play_id;
	}

	public void setPlay_id(int play_id) {
		this.play_id = play_id;
	}

	@Override
	public String toString() {
		return "Schedule [sched_id=" + sched_id + ", studio_id=" + studio_id + ", play_id=" + play_id + ", sched_time="
				+ sched_time + ", sched_ticket_price=" + sched_ticket_price + "]";
	}
}
