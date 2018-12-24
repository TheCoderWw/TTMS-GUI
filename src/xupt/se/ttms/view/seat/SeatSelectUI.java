package xupt.se.ttms.view.seat;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import xupt.se.ttms.model.Schedule;
import xupt.se.ttms.model.Seat;
import xupt.se.ttms.model.Studio;
import xupt.se.ttms.model.Ticket;
import xupt.se.ttms.service.ScheduleSrv;
import xupt.se.ttms.service.SeatSrv;
import xupt.se.ttms.service.StudioSrv;
import xupt.se.ttms.service.TicketSrv;
import xupt.se.ttms.view.nowshowing.SchedSelectUI;
import xupt.se.ttms.view.sellticket.SellTicketUI;
import xupt.se.ttms.view.tmpl.MainUITmpl;
import xupt.se.util.ImageUtil;
import xupt.se.util.MouseListerDemo;

public class SeatSelectUI extends MainUITmpl {

	private static final long serialVersionUID = 1L;
	private int stu_id;
	private int sched_id;
	private int play_id;
	private JLabel ca1 = null;
	private JPanel jsc;
	private JButton btnSave;
	JLabel screenPan, screenContain;
	private JLabel iconTemp;
	private Set<Seat> changedSeatSet = new HashSet<>();
	// 座位的图形
	ImageIcon white = ImageUtil.getScaledImage("resource/image/seat0.png", 30, 30);
	ImageIcon green = ImageUtil.getScaledImage("resource/image/seat1.png", 30, 30);
	ImageIcon red = ImageUtil.getScaledImage("resource/image/seat2.png", 30, 30);
	ImageIcon screen;

	public SeatSelectUI(int sched_id) {
		this.sched_id = sched_id;
		ScheduleSrv schedSrv = new ScheduleSrv();
		String condt = "sched_id = " + this.sched_id;
		Schedule schedNow = schedSrv.Fetch(condt).get(0);
		this.stu_id = schedNow.getStudio_id();
		this.play_id = schedNow.getPlay_id();
	}

	// 从数据库中获取到该演出厅
	public Studio getSeatNum(int studioId) {
		Studio stuNow = new Studio();
		String condt = "studio_id= " + studioId + ";";
		StudioSrv stuSrv = new StudioSrv();
		List<Studio> stuList = stuSrv.Fetch(condt);
		if (!stuList.isEmpty())
			stuNow = stuList.get(0);
		else
			return null;
		return stuNow;
	}

	public void initCd() {
		Rectangle rect = contPan.getBounds();
		// 通过传入的演出厅id ，获取该演出厅的行列数

		Studio stuNow = getSeatNum(this.stu_id);

		ca1 = new JLabel(stuNow.getName() + "   座位选择", JLabel.CENTER);
		ca1.setBounds(0, 5, rect.width, 30);
		ca1.setFont(new java.awt.Font("宋体", 1, 20));
		ca1.setForeground(Color.yellow);
		contPan.add(ca1);

		// 添加荧幕
		screenContain = new JLabel();
		screenContain.setBounds(100, 40, rect.width - 200, 50);
		screenContain.setBackground(Color.WHITE);
		contPan.add(screenContain);
		screenPan = new JLabel();
		screen = ImageUtil.getScaledImage("resource/image/screen.png", rect.width - 400, 70);
		screenPan.setIcon(screen);
		screenPan.setBounds(100, 10, screen.getIconWidth(), screen.getIconHeight());
		screenPan.setHorizontalAlignment(SwingConstants.CENTER);
		screenContain.add(screenPan);

		jsc = printSeat(stuNow);
		jsc.setBounds(100, 100, rect.width - 200, rect.height - 150);
		contPan.add(jsc);

		// 添加确认修改按钮
		btnSave = new JButton("确认选座");
		// btnSave.setBorderPainted(false); // 去除边框
		btnSave.setForeground(Color.YELLOW);
		btnSave.setFocusPainted(false); // 去除焦点
		btnSave.setContentAreaFilled(false);
		MouseListerDemo.setMouseLister(btnSave);
		btnSave.setBounds(rect.width - 550, rect.height - 40, 120, 30);
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent Event) {

				btnExecClicked();
			}
		});
		contPan.add(btnSave);

		btnSave = new JButton("取消");
		// btnSave.setBorderPainted(false); // 去除边框
		btnSave.setForeground(Color.YELLOW);
		btnSave.setFocusPainted(false); // 去除焦点
		btnSave.setContentAreaFilled(false);
		MouseListerDemo.setMouseLister(btnSave);
		btnSave.setBounds(rect.width - 410, rect.height - 40, 80, 30);
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent Event) {
				setVisible(false);
				SchedSelectUI frmStuMgr = new SchedSelectUI(play_id);
				frmStuMgr.initC();
				frmStuMgr.setVisible(true);
			}
		});
		contPan.add(btnSave);

		iconTemp = new JLabel("可选座位", JLabel.CENTER);
		iconTemp.setForeground(Color.YELLOW);
		iconTemp.setIcon(white);
		iconTemp.setBounds(rect.width - 320, rect.height - 40, 100, 30);
		contPan.add(iconTemp);

		iconTemp = new JLabel("已售出", JLabel.RIGHT);
		iconTemp.setForeground(Color.YELLOW);
		iconTemp.setIcon(red);
		iconTemp.setBounds(rect.width - 220, rect.height - 40, 100, 30);
		contPan.add(iconTemp);

		iconTemp = new JLabel("已选中", JLabel.RIGHT);
		iconTemp.setForeground(Color.YELLOW);
		iconTemp.setIcon(green);
		iconTemp.setBounds(rect.width - 120, rect.height - 40, 100, 30);
		contPan.add(iconTemp);

	}

	// get seats from database by Studio id
	public List<Seat> getSeatList(int studioId) {
		String condt = "studio_id=" + studioId + ";";
		List<Seat> seatList = new SeatSrv().Fetch(condt);

		return seatList;
	}

	public Ticket getTicket(int sched_id, int seat_id) {
		List<Ticket> tList = new TicketSrv().Fetch("sched_id = " + sched_id + " and seat_id = " + seat_id);
		if (tList.isEmpty())
			System.out.println("获取座位失败");
		return tList.get(0);
	}

	// 绘制座位选择的座位图示
	public JPanel printSeat(Studio stuNow) {

		List<Seat> seatList = getSeatList(stuNow.getID());

		int rowNum = stuNow.getRowCount();
		int colNum = stuNow.getColCount();
		// 改变容器的布局方式
		JPanel contain = new JPanel(new GridLayout(rowNum, colNum, 5, 5));

		Iterator<Seat> itr = seatList.iterator();
		while (itr.hasNext()) {
			Seat seat = itr.next();
			if ((seat.getNum() - 1) % colNum == 0) {
				JLabel row = new JLabel(" 第 " + (seat.getNum() / colNum + 1) + " 排");
				contain.add(row);
			}
			// allSeatList.add(seat);

			// JButton but = seat.initSelect(getTicket(sched_id,seat.getId()));
			Ticket t = getTicket(sched_id, seat.getId());
			JButton seatButton = new JButton();
			if (seat.getSeatStatus() == 0) {
				if (t.getTicket_status() == 0 || t.getTicket_status() == 1)
					seatButton.setIcon(white);
				else if (t.getTicket_status() == 2) {
					seatButton.setIcon(red);
				}
			} else if (seat.getSeatStatus() == -1) { // 坏掉的座位
				seatButton.setVisible(false);
			}

			seatButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {

					if (seat.getSeatStatus() == 0 && t.getTicket_status() != 2) {
						if (getNoRepeat(changedSeatSet).size() < 6) {
							seatButton.setIcon(green);
							seat.setSeatStatus(1);
							changedSeatSet.add(seat);
						} else {
							JOptionPane.showMessageDialog(null, "每次限购6张票");
						}
					} else if (seat.getSeatStatus() == 1) {
						seatButton.setIcon(white);
						seat.setSeatStatus(0);
					}
					if (t.getTicket_status() == 2) {
						JOptionPane.showMessageDialog(null, "该座位已售出");
					}

				}
			});

			seatButton.setContentAreaFilled(false);
			seatButton.setBorderPainted(false);
			seatButton.setFocusPainted(false);
			contain.add(seatButton);
		}
		return contain;
	};

	// 获取到去重的Set
	public Set<Seat> getNoRepeat(Set<Seat> seatSet) {
		Set<Seat> noRepeatSet = new HashSet<>();
		for (Seat s : seatSet) {
			// 获取到数据中数据,去重
			SeatSrv seatSrv = new SeatSrv();
			Seat seatNow = seatSrv.Fetch("seat_id = " + s.getId()).get(0);
			if (seatNow.getSeatStatus() != s.getSeatStatus() && s.getSeatStatus() != 0) {
				noRepeatSet.add(s);
			}
		}
		return noRepeatSet;
	}

	public void btnExecClicked() {
		Set<Seat> changedSet = new HashSet<>();
		Set<Ticket> changedTicketSet = new HashSet<>();

		for (Seat s : changedSeatSet) {
			// 获取到数据中数据,去重
			SeatSrv seatSrv = new SeatSrv();
			Seat seatNow = seatSrv.Fetch("seat_id = " + s.getId()).get(0);
			if (seatNow.getSeatStatus() != s.getSeatStatus()) {
				// s.setSeatStatus(2);
				changedSet.add(s);
			}
		}
		int flag = 0;

		// 判断时间是否超出加锁时间
		for (Seat s : changedSet) {
			Ticket ticketNow = getTicket(sched_id, s.getId());
			Date dateTime;
			SimpleDateFormat sim = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			try {
				// 获取当前票的加锁时间
				dateTime = sim.parse(ticketNow.getTicket_locked_time());
				System.out.println(dateTime.getTime());

				// 计算该加锁时间+10分钟后的时间
				long curren = dateTime.getTime();
				curren += 10 * 60 * 1000;
				Date da = new Date(curren);

				if (da.before(new Date()) || ticketNow.getTicket_status() == 0) {
					// ticketNow.setTicket_status(0);
				} else {
					String tem = s.getRow() + "排" + s.getColumn() + "座";
					JOptionPane.showMessageDialog(null, tem + " 已被锁定,请选择其他座位");
					;
					flag = 1;
					continue;

				}

				System.out.println(da);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

		// for (Seat s : changedSet) {
		// // 判断是否被选中
		// // TicketSrv ticketSrv = new TicketSrv();
		// Ticket ticketNow = getTicket(sched_id, s.getId());
		// if (ticketNow.getTicket_status() == 1) {
		// String tem = s.getRow() + "排" + s.getColumn() + "座";
		// JOptionPane.showMessageDialog(null, tem + " 已被锁定,请选择其他座位");
		// ;
		// flag = 1;
		// continue;
		// }
		// }

		if (flag == 0) {

			for (Seat s : changedSet) {

				Date currentTime = new Date();
				SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				String dateString = formatter.format(currentTime);
				System.out.println(dateString);
				TicketSrv ticketSrv = new TicketSrv();
				Ticket ticketNow = getTicket(sched_id, s.getId());
				ticketNow.setTicket_status(1);
				// System.out.println(new Date().toString());
				ticketNow.setTicket_locked_time(dateString);
				ticketSrv.modify(ticketNow);
				changedTicketSet.add(ticketNow);
			}

			SellTicketUI sell = new SellTicketUI(changedTicketSet);
			sell.setVisible(true);
			setVisible(false);
		}
	}
}
