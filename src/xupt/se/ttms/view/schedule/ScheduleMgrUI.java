package xupt.se.ttms.view.schedule;

import java.awt.Color;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;

import xupt.se.ttms.model.Schedule;
import xupt.se.ttms.model.ScheduleTable;
import xupt.se.ttms.service.ScheduleSrv;
import xupt.se.ttms.view.ServerSide.ServerSideUI;
import xupt.se.util.MouseListerDemo;

public class ScheduleMgrUI extends ServerSideUI {

	private static final long serialVersionUID = 1L;
	private JLabel ca1 = null;
	private JScrollPane jsc;
	private JButton btnAdd, btnEdit, btnDel;
	ScheduleTable tms;

	protected void initContent() {
		Rectangle rect = contPan.getBounds();

		ca1 = new JLabel("", JLabel.CENTER);
		ca1.setBounds(0, 5, rect.width, 30);
		ca1.setFont(new java.awt.Font("宋体", 1, 20));
		ca1.setForeground(Color.blue);
		contPan.add(ca1);

		jsc = new JScrollPane();
		jsc.setBounds(0, 40, rect.width, rect.height - 90);
		contPan.add(jsc);

		btnAdd = new JButton("添加");
		// btnAdd.setBorderPainted(false); // 去除边框
		btnAdd.setFocusPainted(false); // 去除焦点
		btnAdd.setContentAreaFilled(false);
		btnAdd.setForeground(Color.yellow);
		MouseListerDemo.setMouseLister(btnAdd);
		btnAdd.setBounds(rect.width - 220, rect.height - 45, 60, 30);
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent Event) {
				btnAddClicked();
			}
		});
		contPan.add(btnAdd);

		btnEdit = new JButton("修改");
		// btnEdit.setBorderPainted(false); // 去除边框
		btnEdit.setFocusPainted(false); // 去除焦点
		btnEdit.setContentAreaFilled(false);
		btnEdit.setForeground(Color.yellow);
		MouseListerDemo.setMouseLister(btnEdit);
		btnEdit.setBounds(rect.width - 150, rect.height - 45, 60, 30);
		btnEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent Event) {
				btnModClicked();
			}
		});
		contPan.add(btnEdit);

		btnDel = new JButton("删除");
		// btnDel.setBorderPainted(false); // 去除边框
		btnDel.setFocusPainted(false); // 去除焦点
		btnDel.setContentAreaFilled(false);
		btnDel.setForeground(Color.yellow);
		MouseListerDemo.setMouseLister(btnDel);
		btnDel.setBounds(rect.width - 80, rect.height - 45, 60, 30);
		btnDel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent Event) {
				btnDelClicked();
			}

		});
		contPan.add(btnDel);
		contPan.add(ca1);

		tms = new ScheduleTable(jsc);

		showTable();
	}

	private void btnAddClicked() {

		ScheduleAddUI addSchedUI = null;

		addSchedUI = new ScheduleAddUI();
		addSchedUI.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		addSchedUI.setWindowName("添加演出计划");
		addSchedUI.toFront();
		addSchedUI.setModalityType(JDialog.ModalityType.APPLICATION_MODAL);
		addSchedUI.setVisible(true);
		if (addSchedUI.getReturnStatus()) {
			showTable();
		}
	}

	private void btnModClicked() {
		Schedule sched = tms.getSchedule();
		if (null == sched) {
			JOptionPane.showMessageDialog(null, "请选择要修改的演出计划");
			return;
		}

		ScheduleEditUI modStuUI = new ScheduleEditUI(sched);
		modStuUI.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		modStuUI.setWindowName("修改演出计划");
		modStuUI.initData(sched);
		modStuUI.toFront();
		modStuUI.setModal(true);
		modStuUI.setModalityType(JDialog.ModalityType.APPLICATION_MODAL);
		modStuUI.setVisible(true);

		if (modStuUI.getReturnStatus()) {
			showTable();
		}
	}

	private void btnDelClicked() {
		Schedule sched = tms.getSchedule();
		if (null == sched) {
			JOptionPane.showMessageDialog(null, "请选择要删除的演出计划");
			return;
		}

		int confirm = JOptionPane.showConfirmDialog(null, "确认删除所选？", "删除", JOptionPane.YES_NO_OPTION);
		if (confirm == JOptionPane.YES_OPTION) {
			ScheduleSrv schedSrv = new ScheduleSrv();
			schedSrv.delete(sched.getSched_id());
			showTable();
		}
	}

	private void showTable() {
		List<Schedule> schedList = new ScheduleSrv().FetchAll();
		tms.showScheduleList(schedList);
	}

	public static void main(String[] args) {
		ScheduleMgrUI frmplayMgr = new ScheduleMgrUI();// 创建窗口
		frmplayMgr.setVisible(true);// 显示
	}
}
