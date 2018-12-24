package xupt.se.ttms.view.seat;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import xupt.se.ttms.model.Seat;
import xupt.se.ttms.model.Studio;
import xupt.se.ttms.service.SeatSrv;
import xupt.se.ttms.service.StudioSrv;
import xupt.se.ttms.view.ServerSide.ServerSideUI;
import xupt.se.ttms.view.studio.StudioMgrUI;
import xupt.se.util.ImageUtil;
import xupt.se.util.MouseListerDemo;

public class SeatMgrUI extends ServerSideUI {

	private static final long serialVersionUID = 1L;
	private JLabel ca1 = null;
	private JLabel iconTemp;
	// 用来放表格的滚动控件
	private JPanel jsc;
	JLabel screenPan, screenContain;
	private JButton btnSave;

	// 存储当前界面中所有的座位JButton
	// private List<Seat> allSeatList = new ArrayList<>();
	private Set<Seat> changSeatSet = new HashSet<>();

	private int stu_id;

	// 座位的图形
	ImageIcon white = ImageUtil.getScaledImage("resource/image/seat0.png", 30, 30);
	ImageIcon green = ImageUtil.getScaledImage("resource/image/seat1.png", 30, 30);
	ImageIcon red = ImageUtil.getScaledImage("resource/image/seat2.png", 30, 30);
	ImageIcon screen;

	public SeatMgrUI() {

	}

	public SeatMgrUI(int id) {
		this.stu_id = id;
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

	// get seats from database by Studio id
	public List<Seat> getSeatList(int studioId) {
		String condt = "studio_id=" + studioId + ";";
		List<Seat> seatList = new SeatSrv().Fetch(condt);

		return seatList;
	}

	// 绘制座位(button)
	public JPanel printSeat(Studio stuNow) {
		List<Seat> seatList = getSeatList(stuNow.getID());

		int rowNum = stuNow.getRowCount();
		int colNum = stuNow.getColCount();
		// 改变容器的布局方式
		JPanel contain = new JPanel(new GridLayout(rowNum, colNum + 1, 5, 5));
		Iterator<Seat> itr = seatList.iterator();
		while (itr.hasNext()) {
			Seat seat = itr.next();
			if ((seat.getNum() - 1) % colNum == 0) {
				JLabel row = new JLabel(" 第 " + (seat.getNum() / colNum + 1) + " 排");
				contain.add(row);
			}
			// allSeatList.add(seat);
			JButton but = seat.initMg();
			but.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					changSeatSet.add(seat);
				}
			});
			contain.add(but);
		}

		return contain;
	};

	public void initCd() {
		Rectangle rect = contPan.getBounds();
		// 通过传入的演出厅id ，获取该演出厅的行列数
		Studio stuNow = getSeatNum(this.stu_id);

		ca1 = new JLabel(stuNow.getName() + " 座位管理", JLabel.CENTER);
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
		btnSave = new JButton("确认修改");
		// btnSave.setBorderPainted(false); // 去除边框
		btnSave.setForeground(Color.YELLOW);
		btnSave.setFocusPainted(false); // 去除焦点
		btnSave.setContentAreaFilled(false);
		MouseListerDemo.setMouseLister(btnSave);
		btnSave.setBounds(rect.width - 550, rect.height - 40, 120, 30);
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent Event) {
				btnSaveClicked();
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
				StudioMgrUI frmStuMgr = new StudioMgrUI();
				frmStuMgr.setVisible(true);
			}
		});
		contPan.add(btnSave);

		iconTemp = new JLabel("正常座位", JLabel.CENTER);
		iconTemp.setForeground(Color.YELLOW);
		iconTemp.setIcon(white);
		iconTemp.setBounds(rect.width - 320, rect.height - 40, 100, 30);
		contPan.add(iconTemp);

		iconTemp = new JLabel("坏掉的座位", JLabel.RIGHT);
		iconTemp.setForeground(Color.YELLOW);
		iconTemp.setIcon(red);
		iconTemp.setBounds(rect.width - 220, rect.height - 40, 100, 30);
		contPan.add(iconTemp);

		iconTemp = new JLabel("已修复座位", JLabel.RIGHT);
		iconTemp.setForeground(Color.YELLOW);
		iconTemp.setIcon(green);
		iconTemp.setBounds(rect.width - 120, rect.height - 40, 100, 30);
		contPan.add(iconTemp);

	}

	private void btnSaveClicked() {
		SeatSrv seatsrv = new SeatSrv();
		for (Seat seat : changSeatSet) {
			seatsrv.modify(seat);
		}

		setVisible(false);
		StudioMgrUI frmStuMgr = new StudioMgrUI();
		frmStuMgr.setVisible(true);
	}

	public static void main(String[] args) {
		SeatMgrUI frmSeaMgr = new SeatMgrUI(26);// 创建窗口
		frmSeaMgr.setVisible(true);// 显示
	}

}
