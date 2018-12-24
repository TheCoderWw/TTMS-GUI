package xupt.se.ttms.view.schedule;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import xupt.se.ttms.model.Play;
import xupt.se.ttms.model.Schedule;
import xupt.se.ttms.model.Seat;
import xupt.se.ttms.model.Studio;
import xupt.se.ttms.model.Ticket;
import xupt.se.ttms.service.PlaySrv;
import xupt.se.ttms.service.ScheduleSrv;
import xupt.se.ttms.service.SeatSrv;
import xupt.se.ttms.service.StudioSrv;
import xupt.se.ttms.service.TicketSrv;
import xupt.se.ttms.view.tmpl.PopUITmpl;
import xupt.se.util.MouseListerDemo;
import xupt.se.util.PlayComboBox;
import xupt.se.util.StudioComboBox;

public class ScheduleAddUI extends PopUITmpl implements ActionListener {

	private static final long serialVersionUID = 1L;
	private JButton btnCancel, btnSave;
	protected boolean rst = false;
	private JLabel lblName, lblPlay, lblPrice, lblYear, lblMonth, lblDay, lblTime;
	protected JTextField txtDate, txtPrice, txtPlay;
	protected JTextField txtmonth, txtday, txthour, txtminu;
	protected JComboBox<String> monthBox, dayBox, hourBox, minuteBox;
	private String[] monthArr, dayArr, hourArr, minuteArr;
	protected JComboBox<String> stuBox, playBox;

	public void initDate(String[] dateArr) {
		for (int i = 0; i < dateArr.length; i++) {
			dateArr[i] = i + 1 + "";
		}
	}

	public void initTime(String[] timeArr) {
		for (int i = 0; i < timeArr.length; i++) {
			timeArr[i] = i + "";
		}
	}

	public String getTime() {
		String rt = "2018-";
		rt += monthBox.getSelectedItem() + "-" + dayBox.getSelectedItem() + " " + hourBox.getSelectedItem() + ":"
				+ minuteBox.getSelectedItem() + ":00";
		return rt;
	}

	protected void initContent() {
		// 获取所有的演出厅
		StudioSrv stuSrv = new StudioSrv();
		List<Studio> allStudio = stuSrv.FetchAll();
		// 获取所有的剧目
		PlaySrv playSrv = new PlaySrv();
		List<Play> allPlay = playSrv.FetchAll();

		this.setTitle("添加演出计划");

		lblName = new JLabel("选择演出厅:");
		lblName.setForeground(Color.WHITE);
		lblName.setBounds(60, 30, 80, 30);
		contPan.add(lblName);
		stuBox = new JComboBox<>(new StudioComboBox(allStudio));
		stuBox.setBounds(150, 30, 300, 30);
		contPan.add(stuBox);

		lblPlay = new JLabel("选择剧目:");
		lblPlay.setForeground(Color.WHITE);
		lblPlay.setBounds(60, 80, 80, 30);
		contPan.add(lblPlay);
		playBox = new JComboBox<>(new PlayComboBox(allPlay));
		playBox.setBounds(150, 80, 300, 30);
		contPan.add(playBox);

		lblTime = new JLabel("请输入上映时间:");
		lblTime.setForeground(Color.WHITE);
		lblTime.setBounds(60, 120, 150, 30);
		contPan.add(lblTime);

		monthArr = new String[12];
		dayArr = new String[31];
		hourArr = new String[24];
		minuteArr = new String[60];

		initDate(monthArr);
		initDate(dayArr);
		initTime(hourArr);
		initTime(minuteArr);

		lblYear = new JLabel("2018年");
		lblYear.setForeground(Color.WHITE);
		lblYear.setBounds(160, 120, 50, 30);
		contPan.add(lblYear);

		monthBox = new JComboBox<String>(monthArr);
		monthBox.setBounds(210, 120, 50, 25);
		contPan.add(monthBox);
		lblMonth = new JLabel(" 月");
		lblMonth.setForeground(Color.WHITE);
		lblMonth.setBounds(260, 120, 20, 30);
		contPan.add(lblMonth);

		dayBox = new JComboBox<String>(dayArr);
		dayBox.setBounds(290, 120, 50, 25);
		contPan.add(dayBox);
		lblDay = new JLabel(" 日");
		lblDay.setForeground(Color.WHITE);
		lblDay.setBounds(340, 120, 20, 30);
		contPan.add(lblDay);

		hourBox = new JComboBox<String>(hourArr);
		hourBox.setBounds(400, 120, 50, 25);
		contPan.add(hourBox);
		lblTime = new JLabel(" 时");
		lblTime.setForeground(Color.WHITE);
		lblTime.setBounds(450, 120, 20, 30);
		contPan.add(lblTime);

		minuteBox = new JComboBox<String>(minuteArr);
		minuteBox.setBounds(470, 120, 50, 25);
		contPan.add(minuteBox);
		lblTime = new JLabel(" 分");
		lblTime.setForeground(Color.WHITE);
		lblTime.setBounds(520, 120, 20, 30);
		contPan.add(lblTime);

		lblPrice = new JLabel("请输入票价:");
		lblPrice.setForeground(Color.WHITE);
		lblPrice.setBounds(60, 170, 150, 30);
		contPan.add(lblPrice);
		txtPrice = new JTextField();
		txtPrice.setBounds(220, 170, 80, 30);
		contPan.add(txtPrice);

		btnSave = new JButton("保存");
		btnSave.setBorderPainted(false); // 去除边框
		btnSave.setFocusPainted(false); // 去除焦点
		MouseListerDemo.setMouseLister(btnSave);
		btnSave.addActionListener(this);
		btnSave.setBounds(60, 280, 60, 30);
		contPan.add(btnSave);

		btnCancel = new JButton("取消");
		btnCancel.setBorderPainted(false); // 去除边框
		btnCancel.setFocusPainted(false); // 去除焦点
		MouseListerDemo.setMouseLister(btnCancel);
		btnCancel.addActionListener(this);
		btnCancel.setBounds(180, 280, 60, 30);
		contPan.add(btnCancel);

		// 初始化时间
		Calendar now = Calendar.getInstance();
		monthBox.setSelectedItem(now.get(Calendar.MONTH) + 1 + "");
		dayBox.setSelectedItem(now.get(Calendar.DAY_OF_MONTH) + "");
		hourBox.setSelectedItem(now.get(Calendar.HOUR_OF_DAY) + "");
		minuteBox.setSelectedItem(now.get(Calendar.MINUTE) + "");

	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnCancel) {
			rst = false;
			this.setVisible(false);
		} else if (e.getSource() == btnSave) {
			btnSaveClicked();
		}
	}

	public boolean getReturnStatus() {
		return rst;
	}

	protected void btnSaveClicked() {
		if (stuBox.getSelectedItem() != null && playBox.getSelectedItem() != null && !txtPrice.getText().equals("")) {
			SimpleDateFormat sim = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			int flag = 0;
			try {
				Date dateTime;
				dateTime = sim.parse(getTime());
				if (dateTime.before(new Date())) {
					flag = 1;
					JOptionPane.showMessageDialog(null, "不能设置该时间(请设置为当前时间之后)!");
				} else if (timeConflict(dateTime, (String) stuBox.getSelectedItem()) == 0) {
					flag = 1;
					JOptionPane.showMessageDialog(null, "不能设置该时间(与其他演出计划冲突)!");
				}
				// if(getTime())
				// 获取选中演出厅的id
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if (flag == 0) {
				StudioSrv stuSrv = new StudioSrv();
				int studio_id = stuSrv.Fetch("studio_name ='" + (String) stuBox.getSelectedItem() + "';").get(0)
						.getID();
				PlaySrv playSrv = new PlaySrv();
				int play_id = playSrv.Fetch("play_name = '" + (String) playBox.getSelectedItem() + "';").get(0)
						.getPlay_id();

				ScheduleSrv schedSrv = new ScheduleSrv();
				Schedule sched = new Schedule();
				sched.setStudio_id(studio_id);
				sched.setPlay_id(play_id);
				sched.setSched_time(getTime());
				sched.setSched_ticket_price(Double.parseDouble(txtPrice.getText()));
				schedSrv.add(sched);

				// 获取到所有的座位
				SeatSrv seatSrv = new SeatSrv();
				List<Seat> allSeatList = seatSrv.Fetch("studio_id = " + studio_id);
				// 初始化票数据
				if (allSeatList.isEmpty()) {
					System.out.println("获取座位失败");
				}

				TicketSrv ticketSrv = new TicketSrv();

				for (Seat s : allSeatList) {
					Ticket ticket = new Ticket();
					ticket.setSeat_id(s.getId());
					ticket.setSched_id(sched.getSched_id());
					ticket.setTicket_price(sched.getSched_ticket_price());

					ticketSrv.add(ticket);
				}
				setVisible(false);

				rst = true;
			}
		} else {
			JOptionPane.showMessageDialog(null, "数据不完整");
		}
	}

	public int timeConflict(Date cur, String studio_name) {
		Calendar beginTime = null, curTime = null, endTime = null;
		int playLen;
		Date dateTime;

		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		// 转化传入的时间
		curTime = Calendar.getInstance();
		curTime.setTime(cur);

		StudioSrv stuSrv = new StudioSrv();
		int stu_id = stuSrv.Fetch(" studio_name = '" + studio_name + "'").get(0).getId();
		ScheduleSrv schedSrv = new ScheduleSrv();
		List<Schedule> allSched = schedSrv.Fetch("studio_id = " + stu_id);

		for (Schedule sched : allSched) {
			// 获取每个演出计划的时间
			try {
				dateTime = df.parse(sched.getSched_time());
				beginTime = Calendar.getInstance();
				beginTime.setTime(dateTime);
			} catch (ParseException e) {
				e.printStackTrace();
			}
			// 获取对应剧目的时长
			PlaySrv playSrv = new PlaySrv();
			playLen = playSrv.Fetch("play_id = " + sched.getPlay_id()).get(0).getPlay_length();
			if (curTime.after(beginTime)) {
				endTime = beginTime;
				endTime.add(Calendar.MINUTE, playLen);
				if (curTime.before(endTime))
					return 0;
			}
		}
		return 1;
	}

}
