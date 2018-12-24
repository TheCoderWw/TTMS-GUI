package xupt.se.ttms.view.sellticket;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import xupt.se.ttms.model.Sale;
import xupt.se.ttms.model.SaleItem;
import xupt.se.ttms.model.Schedule;
import xupt.se.ttms.model.Seat;
import xupt.se.ttms.model.Ticket;
import xupt.se.ttms.model.User;
import xupt.se.ttms.service.DictSrv;
import xupt.se.ttms.service.LoginedUser;
import xupt.se.ttms.service.PlaySrv;
import xupt.se.ttms.service.SaleItemSrv;
import xupt.se.ttms.service.SaleSrv;
import xupt.se.ttms.service.ScheduleSrv;
import xupt.se.ttms.service.SeatSrv;
import xupt.se.ttms.service.TicketSrv;
import xupt.se.ttms.service.UserSrv;
import xupt.se.ttms.view.nowshowing.NowShowingUI;
import xupt.se.ttms.view.seat.SeatSelectUI;
import xupt.se.ttms.view.tmpl.PopUITmpl;
import xupt.se.util.MouseListerDemo;

//传入   选中座位id,schedid;
public class SellTicketUI extends PopUITmpl {

	private static final long serialVersionUID = 1L;
	private JLabel ticketInfo = new JLabel("电影信息:");
	private JLabel nowBuying = new JLabel("已选座位:");
	private JLabel nowSeatInfo = new JLabel();
	private JLabel tkInfo = new JLabel();
	private JLabel time = new JLabel();
	private JLabel allMoney = new JLabel();
	private JButton yes = new JButton("确认支付");
	private JButton no = new JButton("取消支付");

	private Seat seat;
	protected int sched_id;
	private Schedule schedule;
	private ScheduleSrv scheduleSrv;
	private PlaySrv playSrv;
	private DictSrv dictSrv;
	private Sale saleLog;

	public Sale createSaleLog(int user_id, int pay) {
		// 获取当前时间
		Calendar now = Calendar.getInstance();
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String nowTime = df.format(now.getTime());

		Sale saleNow = new Sale(user_id, nowTime, pay);

		return saleNow;
	}

	public Ticket createTicketLog(int sched_id, int seat_id) {

		// 获取当前时间
		Calendar now = Calendar.getInstance();
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String nowTime = df.format(now.getTime());

		ScheduleSrv schedSrv = new ScheduleSrv();
		Double price = schedSrv.Fetch("sched_id = " + sched_id).get(0).getSched_ticket_price();

		Ticket ticketNow = new Ticket(sched_id, seat_id, price, nowTime);

		return ticketNow;
	}

	// Set<ticket>
	public SellTicketUI(Set<Ticket> ticketSet) {
		SeatSrv seatSrv = new SeatSrv();
		Seat seat = new Seat();
		Ticket ticket = new Ticket();
		// Schedule schedule = new Schedule();
		// Set<Ticket> 迭代器

		Iterator<Ticket> itTicket1 = ticketSet.iterator();

		// 随意获取一张票的信息
		ticket = itTicket1.next();
		// 接收schedId
		int schedId = ticket.getSched_id();

		Iterator<Ticket> itTicket = ticketSet.iterator();

		Set<Seat> seatSet = new HashSet<Seat>();
		List<Seat> seatList = new ArrayList<Seat>();
		while (itTicket.hasNext()) {
			Ticket ticketNow = itTicket.next();
			seat = seatSrv.Fetch("seat_id = " + ticketNow.getSeat_id() + ";").get(0);
			// seatSrv.Fetch(condt)ticketNow.getSeat_id()
			seatSet.add(seat);
		}

		// List<Seat> seatList = seatSrv.Fetch("seat_id = " +ticket.getSeat_id()+";");

		scheduleSrv = new ScheduleSrv();
		playSrv = new PlaySrv();
		dictSrv = new DictSrv();

		this.sched_id = schedId;
		// 获取当前演出计划的所有信息List
		List<Schedule> schedList = scheduleSrv.Fetch("sched_id = " + schedId + ";");
		schedule = schedList.get(0);
		// this.setSize(800, 600);
		this.setTitle("购票");

		ticketInfo.setForeground(Color.WHITE);
		ticketInfo.setFont(new java.awt.Font("宋体", 1, 20));
		ticketInfo.setBounds(100, 50, 100, 30);

		tkInfo.setForeground(Color.WHITE);
		tkInfo.setFont(new java.awt.Font("宋体", 1, 20));
		tkInfo.setBounds(210, 50, 500, 30);
		String tickTem = "";
		schedule = schedList.get(0);
		tickTem += playSrv.Fetch("play_id = " + schedule.getPlay_id() + ";").get(0).getPlay_name();
		tickTem += " 时长:" + playSrv.Fetch("play_id = " + schedule.getPlay_id() + ";").get(0).getPlay_length();
		tickTem += " 语言:"
				+ dictSrv.FetchByID(playSrv.Fetch("play_id = " + schedule.getPlay_id() + ";").get(0).getLang_id())
						.get(0).getDict_value();
		time.setText("上映时间:" + schedule.getSched_time());

		time.setForeground(Color.WHITE);
		time.setFont(new java.awt.Font("宋体", 1, 20));
		time.setBounds(100, 100, 500, 30);

		tkInfo.setText(tickTem);

		nowBuying.setForeground(Color.WHITE);
		nowBuying.setFont(new java.awt.Font("宋体", 1, 20));
		nowBuying.setBounds(100, 150, 100, 30);

		Iterator<Seat> itSeat = seatSet.iterator();
		String tem = "";
		while (itSeat.hasNext()) {

			seat = itSeat.next();

			tem = tem + seat.getRow() + "排" + seat.getColumn() + "座 . ";

		}
		tem += "单价:" + schedule.getSched_ticket_price();
		nowSeatInfo.setText(tem);

		nowSeatInfo.setForeground(Color.WHITE);
		nowSeatInfo.setFont(new java.awt.Font("宋体", 1, 20));
		nowSeatInfo.setBounds(210, 150, 500, 30);

		allMoney.setForeground(Color.WHITE);
		allMoney.setFont(new java.awt.Font("宋体", 1, 20));
		allMoney.setBounds(100, 200, 500, 30);
		allMoney.setText("总价 :" + schedule.getSched_ticket_price() * seatSet.size());

		yes.setForeground(Color.YELLOW);
		yes.setFont(new java.awt.Font("宋体", 1, 15));
		yes.setBounds(100, 300, 100, 30);
		yes.setContentAreaFilled(false); // 设置为false让button透明;
		yes.setFocusable(false);
		MouseListerDemo.setMouseLister(yes);

		yes.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				Date dateTime;

				Iterator<Ticket> ittem = ticketSet.iterator();
				Ticket tk1 = ittem.next();
				SimpleDateFormat sim = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				try {
					// 获取当前票的加锁时间
					dateTime = sim.parse(tk1.getTicket_locked_time());

					// 计算该加锁时间+10分钟后的时间
					long curren = dateTime.getTime();
					curren += 10 * 60 * 1000;
					Date da = new Date(curren);

					if (da.before(new Date())) {
						JOptionPane.showMessageDialog(null, "该票锁定时间已过");
						new NowShowingUI().setVisible(true);

						setVisible(false);

					} else {
						btnYesClicked(ticketSet);
					}
				} catch (Exception e) {
					System.out.println(e.getMessage());
				}

			}
		});

		no.setForeground(Color.YELLOW);
		no.setFont(new java.awt.Font("宋体", 1, 15));
		no.setBounds(250, 300, 100, 30);
		no.setContentAreaFilled(false); // 设置为false让button透明;
		no.setFocusable(false);
		MouseListerDemo.setMouseLister(no);

		no.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Date dateTime;

				Iterator<Ticket> ittem = ticketSet.iterator();
				Ticket tk1 = ittem.next();
				SimpleDateFormat sim = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				try {
					// 获取当前票的加锁时间
					dateTime = sim.parse(tk1.getTicket_locked_time());

					// 计算该加锁时间+10分钟后的时间
					long curren = dateTime.getTime();
					curren += 10 * 60 * 1000;
					Date da = new Date(curren);

					if (da.before(new Date())) {
						JOptionPane.showMessageDialog(null, "该票锁定时间已过");
						new NowShowingUI().setVisible(true);

						setVisible(false);
					} else {

						Iterator<Ticket> it = ticketSet.iterator();
						while (it.hasNext()) {
							Ticket ticket = new Ticket();
							ticket = it.next();
							ticket.setTicket_status(0);
							TicketSrv ticketSrv = new TicketSrv();
							ticketSrv.modify(ticket);
						}
						JOptionPane.showMessageDialog(null, "用户取消支付");
						SeatSelectUI seatSelect = new SeatSelectUI(sched_id);
						seatSelect.initCd();
						seatSelect.setVisible(true);
						setVisible(false);
					}
				} catch (Exception e) {
					System.out.println(e.getMessage());
				}

			}
		});

		contPan.add(nowBuying);
		contPan.add(nowSeatInfo);
		contPan.add(ticketInfo);
		contPan.add(tkInfo);
		contPan.add(allMoney);
		contPan.add(yes);
		contPan.add(no);
		contPan.add(time);
	}

	public void btnYesClicked(Set<Ticket> ticketSet) {
		if (LoginedUser.getInstance().getUserMoney() >= (int) schedule.getSched_ticket_price() * ticketSet.size()) {
			int nowMoney = LoginedUser.getInstance().getUserMoney()
					- (int) schedule.getSched_ticket_price() * ticketSet.size();
			User user = new User();
			UserSrv userSrv = new UserSrv();
			user.setUserMoney(nowMoney);

			user.setUserName(LoginedUser.getInstance().getEmpName());
			user.setPassWord(LoginedUser.getInstance().getPassWord());
			if (userSrv.update(user)) {

				Iterator<Ticket> it = ticketSet.iterator();
				while (it.hasNext()) {
					Ticket ticket = new Ticket();
					ticket = it.next();
					ticket.setTicket_locked_time(schedule.getSched_time());
					ticket.setTicket_status(2);
					// SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// 设置日期格式
					// System.out.println(df.format(new Date()));
					// ticket.setTicket_locked_time(df.format(new Date()));
					TicketSrv ticketSrv = new TicketSrv();
					ticketSrv.modify(ticket);
				}

				JOptionPane.showMessageDialog(null, "购买成功,祝您观影愉快!" + "");
				LoginedUser.getInstance().setUserMoney(user.getUserMoney());

				// 初始化一个sale对象，存储当前的交易记录
				saleLog = createSaleLog(LoginedUser.getInstance().getUserID(),
						(int) schedule.getSched_ticket_price() * ticketSet.size());
				new SaleSrv().add(saleLog);

				new NowShowingUI().setVisible(true);
				setVisible(false);
			}

			// 数据操作
			for (Ticket s : ticketSet) {

				// 链接票与交易记录
				SaleItem saleItemNow = new SaleItem();
				saleItemNow.setSale_ID(saleLog.getSale_ID());
				saleItemNow.setTicket_id(s.getTicket_id());
				saleItemNow.setSale_item_price(schedule.getSched_ticket_price());
				new SaleItemSrv().add(saleItemNow);

			}
		} else {
			JOptionPane.showMessageDialog(null, "余额不足,请充值");
		}
	}

}
