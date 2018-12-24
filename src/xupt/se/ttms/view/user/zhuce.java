package xupt.se.ttms.view.user;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;

import xupt.se.ttms.model.User;
import xupt.se.ttms.service.UserSrv;
import xupt.se.util.MD5Util;
import xupt.se.util.MouseListerDemo;

public class zhuce extends registerUI {
	JButton cancel = new JButton("返回");
	JButton registerZc = new JButton("确定");

	public zhuce() {
		this.setTitle("注册");

		JLabel passWord2 = new JLabel("确认密码:");
		passWord2.setForeground(Color.WHITE);
		passWord2.setBounds(100, 130, 100, 30);
		JPasswordField jp2 = new JPasswordField(10);

		jp2.setEchoChar('●'); // 回显字符
		jp2.setBounds(150, 130, 200, 30);
		cancel.setForeground(Color.WHITE);
		cancel.setBackground(new Color(0, 0, 0));
		cancel.setBorderPainted(false); // 去除边框
		cancel.setFocusPainted(false); // 去除焦点
		cancel.setBounds(255, 170, 95, 30);
		MouseListerDemo.setMouseLister(cancel);
		cancel.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				setVisible(false);
			}
		});
		registerZc.setBackground(new Color(0, 0, 0));
		registerZc.setForeground(Color.WHITE);
		registerZc.setBorderPainted(false);
		registerZc.setFocusPainted(false);

		registerZc.setBounds(150, 170, 95, 30);
		MouseListerDemo.setMouseLister(registerZc);

		registerZc.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String userName = jt1.getText();
				String passWord = String.valueOf(jp.getPassword());
				String passWord2 = String.valueOf(jp2.getPassword());

				// user.setPassWord(passWord);
				if (!userSrv.select(userName)) {
					if (userName.length() >= 2 && userName.length() <= 8) {
						if (passWord.length() >= 8 && passWord.length() <= 18) {
							if (passWord.equals(passWord2)) {
								// UserDAO ud = new UserDAO();
								UserSrv userSrv = new UserSrv();
								User user = new User();
								user.setUserName(userName);
								user.setPassWord(MD5Util.crypt(passWord));
								user.setUserMoney(0);
								if (userSrv.add(user)) {

									JOptionPane.showMessageDialog(null, "注册成功,请登录");
									setVisible(false);
									new registerUI().setVisible(true);
								}
							} else {
								JOptionPane.showMessageDialog(null, "密码不一致,请重新输入");
							}
						} else {
							JOptionPane.showMessageDialog(null, "密码长度为8 -- 18位");
						}
					} else {
						JOptionPane.showMessageDialog(null, "用户名长度为2 -- 8位");
					}
				} else {
					JOptionPane.showMessageDialog(null, "用户名已存在");
				}
			}
		});
		ct.remove(enter);
		ct.remove(register);
		ct.remove(adminL);
		ct.remove(hint);
		ct.add(passWord2);
		ct.add(jp2);
		ct.add(cancel);
		ct.add(registerZc);
		// this.setUndecorated(true);
	}

	public static void main(String[] args) {
		new zhuce();
	}
}
