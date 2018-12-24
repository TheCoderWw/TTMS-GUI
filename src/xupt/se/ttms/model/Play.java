package xupt.se.ttms.model;

public class Play {
	private int play_id = 0;
	private int play_type_id = 0;
	private int lang_id = 0;
	private String play_name = null;
	private String play_introduction = null;
	private String play_image = null;
	private int play_length = 0;
	private int play_ticket_price = 0;
	private int play_status = 0;

	public int getPlay_id() {
		return play_id;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + lang_id;
		result = prime * result + play_id;
		result = prime * result + ((play_image == null) ? 0 : play_image.hashCode());
		result = prime * result + ((play_introduction == null) ? 0 : play_introduction.hashCode());
		result = prime * result + play_length;
		result = prime * result + ((play_name == null) ? 0 : play_name.hashCode());
		result = prime * result + play_status;
		result = prime * result + play_ticket_price;
		result = prime * result + play_type_id;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Play other = (Play) obj;
		if (lang_id != other.lang_id)
			return false;
		if (play_id != other.play_id)
			return false;
		if (play_image == null) {
			if (other.play_image != null)
				return false;
		} else if (!play_image.equals(other.play_image))
			return false;
		if (play_introduction == null) {
			if (other.play_introduction != null)
				return false;
		} else if (!play_introduction.equals(other.play_introduction))
			return false;
		if (play_length != other.play_length)
			return false;
		if (play_name == null) {
			if (other.play_name != null)
				return false;
		} else if (!play_name.equals(other.play_name))
			return false;
		if (play_status != other.play_status)
			return false;
		if (play_ticket_price != other.play_ticket_price)
			return false;
		if (play_type_id != other.play_type_id)
			return false;
		return true;
	}

	public void setPlay_id(int play_id) {
		this.play_id = play_id;
	}

	public int getPlay_type_id() {
		return play_type_id;
	}

	public void setPlay_type_id(int play_type_id) {
		this.play_type_id = play_type_id;
	}

	public int getLang_id() {
		return lang_id;
	}

	public void setLang_id(int lang_id) {
		this.lang_id = lang_id;
	}

	public String getPlay_name() {
		return play_name;
	}

	public void setPlay_name(String play_name) {
		this.play_name = play_name;
	}

	public String getPlay_introduction() {
		return play_introduction;
	}

	public void setPlay_introduction(String play_introduction) {
		this.play_introduction = play_introduction;
	}

	public String getPlay_image() {
		return play_image;
	}

	public void setPlay_image(String play_image) {
		this.play_image = play_image;
	}

	public int getPlay_length() {
		return play_length;
	}

	public void setPlay_length(int play_length) {
		this.play_length = play_length;
	}

	public int getPlay_ticket_price() {
		return play_ticket_price;
	}

	public void setPlay_ticket_price(int play_ticket_price) {
		this.play_ticket_price = play_ticket_price;
	}

	public int getPlay_status() {
		return play_status;
	}

	public void setPlay_status(int play_status) {
		this.play_status = play_status;
	}

}
