package xupt.se.ttms.view.nowshowing;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;

import xupt.se.ttms.model.Play;
import xupt.se.ttms.model.Schedule;
import xupt.se.ttms.service.DictSrv;
import xupt.se.ttms.service.PlaySrv;
import xupt.se.ttms.service.ScheduleSrv;
import xupt.se.ttms.view.tmpl.MainUITmpl;
import xupt.se.util.MouseListerDemo;

public class NowShowingUI extends MainUITmpl {
	private static final long serialVersionUID = 1L;

	Play play = new Play();
	PlaySrv playSrv = new PlaySrv();
	ScheduleSrv schSrv = new ScheduleSrv();

	JPanel BigPlayPan = new JPanel();
	protected Set<Play> playSet = null;
	protected List<Play> playList = null;
	protected List<Schedule> schList = null;
	JScrollPane jsp;
	int i = 0;

	public NowShowingUI() {
		getPlayInfo();
		// showing();
	}

	public void getPlayInfo() {
		contPan.setLayout(new BorderLayout());

		BigPlayPan.setBounds(10, 100, 1260, 800);
		schList = schSrv.FetchAll();
		playList = new ArrayList<Play>();

		// playList = playSrv.FetchAll();

		Iterator<Schedule> it = schList.iterator();

		// BigPlayPan.add(jsp);

		while (it.hasNext()) {

			Schedule sch = it.next();
			playList.add(playSrv.Fetch("play_id= " + sch.getPlay_id() + ";").get(0));
		}
		// System.out.println(playList);

		// 将基本类型List转化为Set时直接转化,将对象List转化为Set时要重写对象类的hashcode()和equals();
		playSet = new HashSet<Play>(playList);

		// System.out.println(playSet);

		Iterator<Play> itPlay = playSet.iterator();
		BigPlayPan.setLayout(new GridLayout(playSet.size() + 2, 1));
		while (itPlay.hasNext()) {
			play = itPlay.next();
			OnePlay(play);
		}

		// this.setContentPane(jsp);
		jsp = new JScrollPane(BigPlayPan, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
				ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		contPan.add(jsp, BorderLayout.CENTER);
	}

	public boolean OnePlay(Play play) {
		showing(play);
		return true;
	}

	public void showing(Play play) {
		JPanel playPan = new JPanel(new BorderLayout());
		JPanel playUp = new JPanel(new GridLayout(1, 3));
		JPanel smallPlayPan = new JPanel(new BorderLayout());
		JPanel center = new JPanel(new BorderLayout());

		JLabel playName = new JLabel("名称:" + play.getPlay_name());
		JLabel playLX = new JLabel("类型:" + new DictSrv().FetchByID(play.getPlay_type_id()).get(0).getDict_value());
		JLabel playLan = new JLabel("语言:" + new DictSrv().FetchByID(play.getLang_id()).get(0).getDict_value());
		JLabel playLength = new JLabel("时长:" + play.getPlay_length());
		// JLabel playPrice = new JLabel("票价:" + play.getPlay_ticket_price());
		JLabel playLable = new JLabel();

		JTextArea playIntroduction = new JTextArea(play.getPlay_introduction());
		Icon playIcon = new ImageIcon(play.getPlay_image());

		JButton goStudio = new JButton("选择演出厅");
		goStudio.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				SchedSelectUI s = new SchedSelectUI(play.getPlay_id());
				s.initC();
				s.setVisible(true);
				setVisible(false);
			}

		});
		// BigPlayPan.setLayout(null);

		playUp.setBackground(new Color(210, 180, 140));
		center.setBackground(new Color(176, 196, 222));
		smallPlayPan.setBackground(Color.gray);

		center.setBounds(280, 50, 500, 295);
		// smallPlayPan.setBounds(780, 50, 500, 295);
		playPan.setBounds(0, 0 + i, 1200, 295);
		// goStudio.setBounds(30, 200, 120, 30);
		// playPrice.setBounds(50, 60, 100, 100);
		// i = i + 300;
		playIntroduction.setRows(13);
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

		goStudio.setFocusable(false);
		goStudio.setContentAreaFilled(false);
		MouseListerDemo.setMouseLister(goStudio);

		smallPlayPan.add(goStudio, BorderLayout.CENTER);

		// playPrice.setFont(new Font("宋体", Font.BOLD, 20));
		// smallPlayPan.add(playPrice, BorderLayout.CENTER);

		playLable.setIcon(playIcon);

		center.add(playUp, BorderLayout.NORTH);
		center.add(playName, BorderLayout.CENTER);
		center.add(js, BorderLayout.SOUTH);

		playPan.add(playLable, BorderLayout.WEST);
		playPan.add(center, BorderLayout.CENTER);
		playPan.add(smallPlayPan, BorderLayout.EAST);

		// contPan.add(playPan);
		BigPlayPan.add(playPan);

		// jsp.setViewportView(playPan);
		// jsp.setBounds(50, 50, 100, 100);
		// jsp.setVisible(true);
		// this.getContentPane().add(jsp);
		contPan.remove(reFresh);
		// jsp.setViewportView(playPan);
		// smallPlayPan.setBorder(BorderFactory.createTitledBorder("分组框")); // 分组框
		// smallPlayPan.setBorder(BorderFactory.createLineBorder(Color.red));
	}

	public static void main(String[] args) {
		new NowShowingUI().setVisible(true);
	}
}
