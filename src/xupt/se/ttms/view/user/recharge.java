package xupt.se.ttms.view.user;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import xupt.se.ttms.model.User;
import xupt.se.ttms.service.LoginedUser;
import xupt.se.ttms.service.UserSrv;
import xupt.se.ttms.view.system.SysUserModUI;
import xupt.se.ttms.view.tmpl.MainUITmpl;
import xupt.se.util.MouseListerDemo;

public class recharge extends SysUserModUI {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JLabel rechargeMoney = new JLabel("充值金额");
	// JLabel money = new JLabel(LoginedUser.getInstance().getEmpName());
	JTextField money = new JTextField();
	JButton verifyMoney = new JButton("确认");
	JButton cancel = new JButton("取消");

	public recharge() {
		rechargeMoney.setForeground(Color.WHITE);
		rechargeMoney.setFont(new java.awt.Font("宋体", 1, 13));
		rechargeMoney.setBounds(100, 150, 100, 30);

		money.setFont(new java.awt.Font("宋体", 1, 13));
		money.setForeground(Color.BLACK);
		money.setBounds(230, 150, 200, 30);

		cancel.setForeground(Color.white);
		cancel.setBounds(230, 270, 100, 30);
		cancel.setContentAreaFilled(false); // 设置为false让button透明;
		cancel.setFocusPainted(false); // 去除焦点
		MouseListerDemo.setMouseLister(cancel);
		cancel.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				new MainUITmpl().setVisible(true);
				setVisible(false);

			}
		});

		verifyMoney.setForeground(Color.white);
		verifyMoney.setBounds(100, 270, 100, 30);
		verifyMoney.setContentAreaFilled(false); // 设置为false让button透明;
		verifyMoney.setFocusPainted(false); // 去除焦点
		MouseListerDemo.setMouseLister(verifyMoney);
		verifyMoney.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				try {
					Integer i = Integer.valueOf(money.getText());
					if (LoginedUser.getInstance().getEmpName() == null) {
						JOptionPane.showMessageDialog(null, "充值失败!当前无用户登录!");

					} else if (i > 0 && i < 1000) {
						User user = new User();
						UserSrv userSrv = new UserSrv();

						int moneyNow = userSrv.selectMoney(LoginedUser.getInstance().getEmpName());

						user.setUserMoney(i + moneyNow);

						user.setUserName(LoginedUser.getInstance().getEmpName());
						user.setPassWord(LoginedUser.getInstance().getPassWord());
						if (userSrv.update(user)) {
							JOptionPane.showMessageDialog(null, "充值成功,去买票吧!" + "");
							LoginedUser.getInstance().setUserMoney(user.getUserMoney());
							// showUserMoney();
							// new MainUITmpl().setVisible(true);
							setVisible(false);
						}
					} else {
						JOptionPane.showMessageDialog(null, "单次充值只能在1-1000元之间");
					}
				} catch (Exception ex) {
					JOptionPane.showMessageDialog(null, "请输入整数!");
				} finally {

				}
			}
		});
		contPan.add(verifyMoney);
		contPan.add(rechargeMoney);
		contPan.add(money);
		contPan.add(cancel);
		contPan.remove(registerZc);
		// contPan.remove(comp);
		contPan.remove(newPassWord2);
		contPan.remove(newPwdText2);
		contPan.remove(passWord);
		contPan.remove(pwdText);
		contPan.remove(newPassWord);
		contPan.remove(newPwdText);
	}

	public static void main(String[] args) {
		new recharge().setVisible(true);
	}
}
