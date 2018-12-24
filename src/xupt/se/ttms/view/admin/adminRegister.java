package xupt.se.ttms.view.admin;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JOptionPane;

import xupt.se.ttms.model.Employee;
import xupt.se.ttms.service.EmployeeSrv;
import xupt.se.ttms.service.LoginedUser;
import xupt.se.ttms.view.ServerSide.ServerSideUI;
import xupt.se.ttms.view.user.registerUI;
import xupt.se.util.MD5Util;
import xupt.se.util.MouseListerDemo;

public class adminRegister extends registerUI {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Employee emp = new Employee();
	EmployeeSrv empSvr = new EmployeeSrv();
	JButton empRegister = new JButton("登录");

	public adminRegister() {

		empRegister.setBounds(200, 130, 95, 30);
		empRegister.setBorderPainted(false); // 去除边框
		empRegister.setFocusPainted(false); // 去除焦点
		empRegister.setForeground(Color.WHITE);
		empRegister.setBackground(new Color(0, 0, 0));
		MouseListerDemo.setMouseLister(empRegister);
		empRegister.addActionListener(new ActionListener() { // 点击事件

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String userName = jt1.getText();
				String passWord = String.valueOf(jp.getPassword()); // 通过MD5加密
				if (userName.length() == 0 || passWord.length() == 0) {
					JOptionPane.showMessageDialog(null, "请输入完整登录信息");
				} else {
					emp.setName(userName);
					emp.setPassword(MD5Util.crypt(passWord));

					if (empSvr.select(emp)) {
						JOptionPane.showMessageDialog(null, "登陆成功");
						LoginedUser.getInstance().setEmpName(userName);

						new ServerSideUI().setVisible(true); // 调用ServerSideUI();
						setVisible(false); // 隐藏登录方法
					} else {
						JOptionPane.showMessageDialog(null, "账号或密码输入错误");
					}
				}
			}
		});
		ct.add(empRegister);
		ct.remove(enter);
		ct.remove(adminL);
		ct.remove(hint);
		ct.remove(register);
	}

	// public static void main(String[] args) {
	// new adminRegister().setVisible(true);
	// }
}
