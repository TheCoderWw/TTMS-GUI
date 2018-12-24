package xupt.se.ttms.view.system;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;

import xupt.se.ttms.model.User;
import xupt.se.ttms.service.LoginedUser;
import xupt.se.ttms.service.UserSrv;
import xupt.se.ttms.view.tmpl.PopUITmpl;
import xupt.se.util.MD5Util;
import xupt.se.util.MouseListerDemo;

public class SysUserModUI extends PopUITmpl {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected JLabel userName = new JLabel("当前用户:");
	protected JLabel passWord = new JLabel("请输入旧密码:");
	protected JLabel newPassWord = new JLabel("请输入新密码:");
	protected JLabel newPassWord2 = new JLabel("请确认新密码:");
	protected JLabel name = new JLabel(LoginedUser.getInstance().getEmpName());
	protected JPasswordField pwdText = new JPasswordField();
	protected JPasswordField newPwdText = new JPasswordField();
	protected JPasswordField newPwdText2 = new JPasswordField();
	protected JButton cancel = new JButton("返回");
	protected JButton registerZc = new JButton("确认");

	public SysUserModUI() {

		this.setSize(800, 600);
		this.setTitle("修改密码");
		userName.setForeground(Color.WHITE);
		userName.setFont(new java.awt.Font("宋体", 1, 13));
		userName.setBounds(100, 50, 100, 30);

		name.setFont(new java.awt.Font("宋体", 1, 13));
		name.setForeground(Color.WHITE);
		name.setBounds(230, 50, 200, 30);

		passWord.setForeground(Color.WHITE);
		passWord.setFont(new java.awt.Font("宋体", 1, 13));
		passWord.setBounds(100, 100, 100, 30);

		pwdText.setBounds(230, 100, 200, 30);
		pwdText.setEchoChar('●'); // 回显字符

		newPassWord.setForeground(Color.WHITE);
		newPassWord.setFont(new java.awt.Font("宋体", 1, 13));
		newPassWord.setBounds(100, 150, 100, 30);

		newPwdText.setBounds(230, 150, 200, 30);
		newPwdText.setEchoChar('●'); // 回显字符

		newPassWord2.setForeground(Color.WHITE);
		newPassWord2.setFont(new java.awt.Font("宋体", 1, 13));
		newPassWord2.setBounds(100, 200, 100, 30);

		newPwdText2.setBounds(230, 200, 200, 30);
		newPwdText2.setEchoChar('●'); // 回显字符

		registerZc.setForeground(Color.white);
		registerZc.setBounds(100, 270, 100, 30);
		registerZc.setContentAreaFilled(false); // 设置为false让button透明;
		registerZc.setFocusPainted(false); // 去除焦点
		MouseListerDemo.setMouseLister(registerZc);

		cancel.setForeground(Color.white);
		cancel.setBounds(230, 270, 100, 30);
		cancel.setFocusPainted(false); // 去除焦点
		cancel.setContentAreaFilled(false); // 设置为false让button透明;
		MouseListerDemo.setMouseLister(cancel);
		;
		registerZc.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String userName = LoginedUser.getInstance().getEmpName();
				String passWord = String.valueOf(pwdText.getPassword());
				String newPassWord = String.valueOf(newPwdText.getPassword());
				String newPassWord2 = String.valueOf(newPwdText2.getPassword());
				UserSrv userSrv = new UserSrv();
				User user = new User();
				user.setUserName(userName);
				user.setPassWord(MD5Util.crypt(passWord));
				if (userSrv.select(user)) {
					if (!passWord.equals(newPassWord)) {
						if (newPassWord.equals(newPassWord2)) {

							user.setPassWord(MD5Util.crypt(newPassWord));
							user.setUserMoney(LoginedUser.getInstance().getUserMoney());

							if (userSrv.update(user)) {

								JOptionPane.showMessageDialog(null, "修改成功,下次登录前更改");
								setVisible(false);
							}
						} else {
							JOptionPane.showMessageDialog(null, "新密码输入不一致");
						}
					} else {
						JOptionPane.showMessageDialog(null, "新密码不能与原密码相同");
					}
				} else {
					JOptionPane.showMessageDialog(null, "原密码输入错误,请重新输入");
				}

			}
		});

		cancel.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				setVisible(false);
			}
		});

		contPan.add(name);
		contPan.add(userName);
		contPan.add(newPassWord2);
		contPan.add(newPwdText2);
		contPan.add(passWord);
		contPan.add(pwdText);
		contPan.add(newPassWord);
		contPan.add(newPwdText);
		contPan.add(registerZc);
		contPan.add(cancel);
	}

	public static void main(String[] args) {
		new SysUserModUI().setVisible(true);
	}
}
