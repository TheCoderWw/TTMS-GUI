package xupt.se.ttms.view.ServerSide;

import java.awt.Color;
import java.awt.Font;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JOptionPane;

import xupt.se.ttms.model.Employee;
import xupt.se.ttms.service.EmployeeSrv;
import xupt.se.ttms.service.LoginedUser;
import xupt.se.ttms.view.play.PlayMgrUI;
import xupt.se.ttms.view.schedule.ScheduleMgrUI;
import xupt.se.ttms.view.sellticket.SellTicketMgrUI;
import xupt.se.ttms.view.studio.StudioMgrUI;
import xupt.se.ttms.view.tmpl.MainUITmpl;
import xupt.se.util.MouseListerDemo;

public class ServerSideUI extends MainUITmpl {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JButton perfM = new JButton("演出管理");
	JButton studioM = new JButton("演出厅管理");
	JButton movieM = new JButton("影片管理");
	JButton empM = new JButton("员工管理");
	JButton ticketM = new JButton("售票管理");

	String EmpName = LoginedUser.getInstance().getEmpName();
	EmployeeSrv employeeSrv = new EmployeeSrv();
	Employee employee = new Employee();

	public ServerSideUI() {
		employee.setName(EmpName);
		usrName.setFont(new java.awt.Font("宋体", 1, 20));
		perfM.setMargin(new Insets(0, 0, 0, 0));
		perfM.setFont(new Font("Dialog", 1, 15));
		perfM.setForeground(Color.yellow);
		perfM.setContentAreaFilled(false);
		perfM.setFocusable(false);
		MouseListerDemo.setMouseLister(perfM);
		perfM.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if (employeeSrv.select(employee.getName()) == 1) {
					new ScheduleMgrUI().setVisible(true);
					setVisible(false);
				} else {
					JOptionPane.showMessageDialog(null, "您没有权限访问");
				}
			}
		});

		ticketM.setMargin(new Insets(0, 0, 0, 0));
		ticketM.setFont(new Font("Dialog", 1, 15));
		ticketM.setForeground(Color.yellow);
		ticketM.setContentAreaFilled(false);
		ticketM.setFocusable(false);
		MouseListerDemo.setMouseLister(ticketM);
		ticketM.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if (employeeSrv.select(employee.getName()) <= 2 && employeeSrv.select(employee.getName()) >= 1) {
					SellTicketMgrUI sell = new SellTicketMgrUI();
					sell.initCd();
					sell.setVisible(true);
					setVisible(false);
				} else {
					JOptionPane.showMessageDialog(null, "您没有权限访问");
				}
			}
		});

		studioM.setMargin(new Insets(0, 0, 0, 0));
		studioM.setFont(new Font("Dialog", 1, 15));
		studioM.setForeground(Color.yellow);
		studioM.setContentAreaFilled(false);
		studioM.setFocusable(false);
		MouseListerDemo.setMouseLister(studioM);
		studioM.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if (employeeSrv.select(employee.getName()) == 1) {
					new StudioMgrUI().setVisible(true);
					setVisible(false);
				} else {
					JOptionPane.showMessageDialog(null, "您没有权限访问");
				}
			}
		});

		movieM.setMargin(new Insets(0, 0, 0, 0));
		movieM.setFont(new Font("Dialog", 1, 15));
		movieM.setForeground(Color.yellow);
		movieM.setContentAreaFilled(false);
		movieM.setFocusable(false);
		MouseListerDemo.setMouseLister(movieM);
		movieM.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if (employeeSrv.select(employee.getName()) == 1) {
					new PlayMgrUI().setVisible(true);
					setVisible(false);

				} else {
					JOptionPane.showMessageDialog(null, "您没有权限访问");
				}
			}
		});

		empM.setMargin(new Insets(0, 0, 0, 0));
		empM.setFont(new Font("Dialog", 1, 15));
		empM.setForeground(Color.yellow);
		empM.setContentAreaFilled(false);
		empM.setFocusable(false);
		MouseListerDemo.setMouseLister(empM);
		empM.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if (employeeSrv.select(employee.getName()) == 1) {
					JOptionPane.showMessageDialog(null, "敬请期待");
				} else {
					JOptionPane.showMessageDialog(null, "您没有权限访问");
				}
			}
		});

		headPan1.add(perfM);
		headPan1.add(studioM);
		headPan1.add(movieM);
		// headPan1.add(empM);
		headPan1.add(ticketM);
		headPan1.remove(nowShowing);
		headPan1.remove(btnModPwd);
		headPan1.remove(btnExit);
		contPan.remove(reFresh);
		headPan1.remove(recharge);
		headPan1.remove(userMoney);
		// headPan1.remove(btnExit);
	}

	public static void main(String[] args) {
		new ServerSideUI().setVisible(true);
	}
}
