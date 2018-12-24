package xupt.se.ttms.view.schedule;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.swing.JOptionPane;

import xupt.se.ttms.model.Schedule;
import xupt.se.ttms.service.PlaySrv;
import xupt.se.ttms.service.ScheduleSrv;
import xupt.se.ttms.service.StudioSrv;

public class ScheduleEditUI extends ScheduleAddUI {

	private static final long serialVersionUID = 1L;
	private Calendar schedTime;
	private Schedule schedule;
	private int sched_id;

	public ScheduleEditUI(Schedule schedule) {
		initData(schedule);
	}

	public void initData(Schedule sched) {
		this.setTitle("修改演出计划");
		if (null == sched) {
			return;
		}
		sched_id = sched.getSched_id();
		// 通过id获取并显示演出厅名字
		StudioSrv stuSrv = new StudioSrv();
		int stu_id = sched.getStudio_id();
		String stuName = stuSrv.Fetch("studio_id = " + stu_id).get(0).getName();
		// 通过剧目id获取剧目名称
		PlaySrv playSrv = new PlaySrv();
		int play_id = sched.getPlay_id();
		String playName = playSrv.Fetch("play_id = " + play_id).get(0).getPlay_name();
		// 解析获取到的时间
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			Date dateTime = df.parse(sched.getSched_time());
			schedTime = Calendar.getInstance();
			schedTime.setTime(dateTime);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		// 初始化数据
		stuBox.setSelectedItem(stuName);
		playBox.setSelectedItem(playName);
		monthBox.setSelectedItem(schedTime.get(Calendar.MONTH) + 1 + "");
		dayBox.setSelectedItem(schedTime.get(Calendar.DAY_OF_MONTH) + "");
		hourBox.setSelectedItem(schedTime.get(Calendar.HOUR_OF_DAY) + "");
		minuteBox.setSelectedItem(schedTime.get(Calendar.MINUTE) + "");

		txtPrice.setText(sched.getSched_ticket_price() + "");
		schedule = sched;
		this.invalidate();
	}

	protected void btnSaveClicked() {
		if (stuBox.getSelectedItem() != null && playBox.getSelectedItem() != null && txtPrice.getText() != null
				&& txtPrice.getText() != null) {
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
				// 获取选中演出厅的id
				StudioSrv stuSrv = new StudioSrv();
				int studio_id = stuSrv.Fetch("studio_name ='" + (String) stuBox.getSelectedItem() + "';").get(0)
						.getID();
				PlaySrv playSrv = new PlaySrv();
				int play_id = playSrv.Fetch("play_name = '" + (String) playBox.getSelectedItem() + "';").get(0)
						.getPlay_id();

				ScheduleSrv schedSrv = new ScheduleSrv();
				Schedule sched = new Schedule();
				sched.setSched_id(sched_id);
				sched.setStudio_id(studio_id);
				sched.setPlay_id(play_id);
				sched.setSched_time(getTime());
				sched.setSched_ticket_price(Double.parseDouble(txtPrice.getText()));
				schedSrv.modify(sched);
				setVisible(false);

				rst = true;
			}
		} else {
			JOptionPane.showMessageDialog(null, "数据不完整");
		}
	}
}
