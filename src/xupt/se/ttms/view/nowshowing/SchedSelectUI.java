package xupt.se.ttms.view.nowshowing;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import xupt.se.ttms.model.Play;
import xupt.se.ttms.model.Schedule;
import xupt.se.ttms.model.ScheduleSelectTable;
import xupt.se.ttms.service.DictSrv;
import xupt.se.ttms.service.PlaySrv;
import xupt.se.ttms.service.ScheduleSrv;
import xupt.se.ttms.view.seat.SeatSelectUI;
import xupt.se.ttms.view.tmpl.MainUITmpl;
import xupt.se.util.ImageUtil;

public class SchedSelectUI extends MainUITmpl {
	private static final long serialVersionUID = 1L;
	private JScrollPane jsc;
	private ScheduleSelectTable sst;
	private int play_id;
	private JButton btn;

	public void showing(Play play) {
		Rectangle rect = contPan.getBounds();
		JPanel playPan = new JPanel(new BorderLayout());
		JPanel playUp = new JPanel(new GridLayout(1, 3));
		JPanel smallPlayPan = new JPanel(new BorderLayout());
		JPanel center = new JPanel(new BorderLayout());

		JLabel playName = new JLabel("名称:" + play.getPlay_name());
		JLabel playLX = new JLabel("类型:" + new DictSrv().FetchByID(play.getPlay_type_id()).get(0).getDict_value());
		JLabel playLan = new JLabel("语言:" + new DictSrv().FetchByID(play.getLang_id()).get(0).getDict_value());
		JLabel playLength = new JLabel("时长:" + play.getPlay_length());
		JLabel playLable = new JLabel();

		JTextArea playIntroduction = new JTextArea(play.getPlay_introduction());
		ImageIcon playIcon = ImageUtil.getScaledImage(play.getPlay_image(), 230, 220);

		playUp.setBackground(new Color(210, 180, 140));
		center.setBackground(new Color(176, 196, 200));
		smallPlayPan.setBackground(Color.gray);

		center.setBounds(280, 50, 500, 220);
		playPan.setBounds(0, 10, rect.width, 220);
		playIntroduction.setRows(8);
		playIntroduction.setLineWrap(true);
		playIntroduction.setFont(new Font("宋体", Font.BOLD, 15));
		playIntroduction.setBackground(new Color(176, 224, 230));
		playIntroduction.setEditable(false);

		JScrollPane js = new JScrollPane(playIntroduction);

		playLength.setFont(new Font("宋体", Font.BOLD, 20));
		playLX.setFont(new Font("宋体", Font.BOLD, 20));
		playLan.setFont(new Font("宋体", Font.BOLD, 20));
		playName.setFont(new Font("宋体", Font.BOLD, 20));

		playUp.add(playLength);
		playUp.add(playLX);
		playUp.add(playLan);

		playLable.setIcon(playIcon);

		center.add(playUp, BorderLayout.NORTH);
		center.add(playName, BorderLayout.CENTER);
		center.add(js, BorderLayout.SOUTH);

		playPan.add(playLable, BorderLayout.WEST);
		playPan.add(center, BorderLayout.CENTER);
		playPan.add(smallPlayPan, BorderLayout.EAST);

		contPan.add(playPan);
	}

	public SchedSelectUI(int play_id) {
		this.play_id = play_id;
	}

	public void initC() {
		Rectangle rect = contPan.getBounds();

		showing(new PlaySrv().Fetch("play_id = " + this.play_id).get(0));

		jsc = new JScrollPane();
		jsc.setBounds(0, 230, rect.width, rect.height - 300);
		contPan.add(jsc);

		btn = new JButton("确认选择");
		btn.setBounds(rect.width - 250, rect.height - 55, 120, 30);
		btn.setFocusPainted(false); // 去除焦点
		btn.setContentAreaFilled(false);
		btn.setForeground(Color.YELLOW);
		btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnExecClick();
			}
		});
		contPan.add(btn);

		sst = new ScheduleSelectTable(jsc);
		showTableByStu();
	}

	protected void showTableByStu() {
		List<Schedule> schedList = new ScheduleSrv().Fetch("play_id = " + play_id);
		sst.showSelectList(schedList);
	}

	public void btnExecClick() {
		int sched_id = sst.getSelectSchedId();

		if (sched_id != -1) {
			SeatSelectUI seatSelect = new SeatSelectUI(sched_id);
			seatSelect.initCd();
			seatSelect.setVisible(true);
			setVisible(false);
		}
	}

	// public static void main(String[] args) {
	// SchedSelectUI t = new SchedSelectUI(13);
	// t.initC();
	// t.setVisible(true);
	// }
}
