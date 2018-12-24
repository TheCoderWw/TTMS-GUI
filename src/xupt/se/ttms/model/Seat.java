package xupt.se.ttms.model;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;

public class Seat {
	private int seat_num;
	private int seat_id;
	private int studio_id;
	private int seat_row;
	private int seat_col;
	private int seat_status;

	// 座位的图形
	ImageIcon white = new ImageIcon("resource/image/seat0.png");
	ImageIcon green = new ImageIcon("resource/image/seat1.png");
	ImageIcon red = new ImageIcon("resource/image/seat2.png");

	// 座位的button
	JButton seatButton = new JButton();

	public Seat() {

	}

	// 初始化该座位的属性
	public Seat(int num, int studio_id, int seat_row, int seat_col, int seat_status) {
		this.studio_id = studio_id;
		this.seat_row = seat_row;
		this.seat_col = seat_col;
		this.seat_status = seat_status;
		// 根据行列数设定该座位的编号
		this.seat_num = num;
	}

	// 初始化座位的属性，默认为可选状态,返回一个button
	public JButton initSelect(Ticket t) {
		if (this.getSeatStatus() == 0) {
			if(t.getTicket_status() == 0 || t.getTicket_status() == 1)
			seatButton.setIcon(white);
			else if(t.getTicket_status() ==  2) {
				seatButton.setIcon(red);
			}
		} else if (this.getSeatStatus() == -1) { // 坏掉的座位
			seatButton.setVisible(false);
		}

		seatButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (getSeatStatus() == 0 && t.getTicket_status() != 2) {
					seatButton.setIcon(green);
					setSeatStatus(1);
				} else if (getSeatStatus() == 1) {
					seatButton.setIcon(white);
					setSeatStatus(0);
				}
				if (t.getTicket_status() == 2) {
					JOptionPane.showMessageDialog(null, "该座位已售出");
				}
			}
		});

		seatButton.setContentAreaFilled(false);
		seatButton.setBorderPainted(false);
		seatButton.setFocusPainted(false);
		return seatButton;
	}

	public JButton initMg() {
		if (this.getSeatStatus() == -1) { // 坏掉的座位
			seatButton.setIcon(red);
		} else {
			seatButton.setIcon(white);
		}

		seatButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (getSeatStatus() == -1) {
					seatButton.setIcon(green);
					setSeatStatus(0);
				} else {
					seatButton.setIcon(red);
					setSeatStatus(-1);
				}
			}
		});
		seatButton.setContentAreaFilled(false);
		seatButton.setBorderPainted(false);
		seatButton.setFocusPainted(false);
		return seatButton;
	}

	public int getSeatStatus() {
		return seat_status;
	}

	public void setSeatStatus(int seatStatus) {
		this.seat_status = seatStatus;
	}

	public int getId() {
		return seat_id;
	}

	public void setId(int id) {
		this.seat_id = id;
	}

	public int getStudioId() {
		return studio_id;
	}

	public void setStudioId(int studioId) {
		this.studio_id = studioId;
	}

	public int getRow() {
		return seat_row;
	}

	public void setRow(int row) {
		this.seat_row = row;
	}

	public int getColumn() {
		return seat_col;
	}

	public void setColumn(int column) {
		this.seat_col = column;
	}

	public int getNum() {
		return seat_num;
	}

	public void setNum(int num) {
		this.seat_num = num;
	}

	@Override
	public String toString() {
		return "Seat [seat_num=" + seat_num + ", seat_id=" + seat_id + ", studio_id=" + studio_id + ", seat_row="
				+ seat_row + ", seat_col=" + seat_col + ", seat_status=" + seat_status + "]";
	}

}
