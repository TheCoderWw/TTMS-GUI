/**
 * 
 */
package xupt.se.ttms.view.tmpl;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import xupt.se.ttms.service.LoginedUser;
import xupt.se.ttms.view.nowshowing.NowShowingUI;
import xupt.se.ttms.view.system.SysUserModUI;
import xupt.se.ttms.view.user.recharge;
import xupt.se.util.MouseListerDemo;

public class MainUITmpl extends JFrame {

	private static final long serialVersionUID = 1L;
	// URL url = MainUITmpl.class.getResource("SB.jpg");
	private int frmWidth = 1324;
	private int frmHeight = 1000;
	Image image = new ImageIcon("resource/image/BGpicture.jpg").getImage();
	protected ImagePanel headPan = new ImagePanel("resource/image/anyue1.jpg");
	protected JPanel headPan1 = new JPanel();
	protected JPanel contPan = new BackgroundPanel(image);
	protected JLabel usrLabel = new JLabel();
	protected JLabel usrName = new JLabel();
	protected JLabel userMoney = new JLabel();
	protected Icon headIcon = new ImageIcon("resource/image/头像2.jpg");

	protected JButton btnModPwd = new JButton("修改密码");
	protected JButton recharge = new JButton("充值");
	protected JButton btnExit = new JButton("退出登录");
	protected JButton nowShowing = new JButton("正在上映");
	protected JButton reFresh = new JButton("刷新");

	public MainUITmpl() {
		this.setSize(frmWidth, frmHeight);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setTitle("安悦影城票务管理系统");

		this.setLayout(null);

		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				onWindowClosing();
			}
		});
		headPan.setBackground(Color.BLACK);
		headPan.setBounds(0, 0, frmWidth, 90);
		headPan.setLayout(null);

		this.add(headPan);

		headPan1.setBackground(Color.BLACK);
		headPan1.setBounds(430, 2, frmWidth - 430, 85);
		headPan1.setLayout(new GridLayout(1, 5, 10, 10));
		headPan.add(headPan1);
		// headPan.setBorder(BorderFactory.createTitledBorder("分组框")); // 分组框
		// headPan.setBorder(BorderFactory.createLineBorder(Color.red));
		contPan.setBounds(0, 80, frmWidth, this.frmHeight - 100);
		contPan.setLayout(null);
		contPan.setBackground(Color.BLACK);

		this.add(contPan);

		initHeader();
		initContent();
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {

					new MainUITmpl().setVisible(true);

				} catch (Exception e) {
					javax.swing.JOptionPane.showMessageDialog(null, e, "Exception", 0);
					e.printStackTrace();
				}
			}
		});

	}

	public int getWidth() {
		return this.frmWidth;

	}

	public int getHeight() {
		return this.frmHeight;

	}

	private void initHeader() {
		try {

			// usrLabel.setBounds(frmWidth - 800, 50, 80, 30);
			// usrLabel.setText("当前用户：");
			usrLabel.setFont(new java.awt.Font("宋体", 1, 20));
			usrLabel.setIcon(headIcon);
			headPan1.add(usrLabel);

			// usrName.setBounds(frmWidth - 80, 5, 80, 30);
			usrName.setText("匿名	 ");
			usrName.setFont(new java.awt.Font("宋体", 1, 20));
			usrName.setForeground(Color.yellow);
			headPan1.add(usrName);

			userMoney.setText("余额:");
			userMoney.setFont(new java.awt.Font("宋体", 1, 20));
			userMoney.setForeground(Color.yellow);
			headPan1.add(userMoney);

			nowShowing.setMargin(new Insets(0, 0, 0, 0));
			nowShowing.setFont(new Font("Dialog", 1, 20));
			nowShowing.setForeground(Color.yellow);
			nowShowing.setContentAreaFilled(false); // 设置为false让button透明;

			nowShowing.setFocusable(false);
			MouseListerDemo.setMouseLister(nowShowing);
			// GetDailySalesService g = new GetDailySalesService();
			headPan1.add(nowShowing);
			nowShowing.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					new NowShowingUI().setVisible(true);
					setVisible(false);
					// JOptionPane.showMessageDialog(null, "敬请期待");
				}
			});

			// btnModPwd.setBounds(frmWidth - 160, 40, 80, 30);
			btnModPwd.setMargin(new Insets(0, 0, 0, 0));
			btnModPwd.setFont(new Font("Dialog", 1, 20));
			btnModPwd.setForeground(Color.yellow);
			btnModPwd.setContentAreaFilled(false); // 设置为false让button透明;

			btnModPwd.setFocusable(false);
			MouseListerDemo.setMouseLister(btnModPwd);
			// GetDailySalesService g = new GetDailySalesService();
			headPan1.add(btnModPwd);
			btnModPwd.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent Event) {
					btnModUserClicked();

				}
			});

			recharge.setMargin(new Insets(0, 0, 0, 0));
			recharge.setFont(new Font("Dialog", 1, 20));
			recharge.setForeground(Color.yellow);
			recharge.setContentAreaFilled(false); // 设置为false让button透明;

			recharge.setFocusable(false);
			MouseListerDemo.setMouseLister(recharge);
			// GetDailySalesService g = new GetDailySalesService();
			headPan1.add(recharge);
			recharge.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent Event) {

					new recharge().setVisible(true);
					// setVisible(false);
					// showUserMoney();
					// JOptionPane.showMessageDialog(null, "敬情期待");

				}
			});

			// btnExit.setBounds(frmWidth - 80, 40, 80, 30);
			btnExit.setContentAreaFilled(false);
			btnExit.setFocusable(false);
			btnExit.setFont(new Font("Dialog", 1, 20));
			btnExit.setForeground(Color.yellow);
			btnExit.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent Event) {
					btnExitClicked(Event);
				}
			});
			MouseListerDemo.setMouseLister(btnExit);

			headPan1.add(btnExit);

			// Show the information of current user
			showCurrentUser();
			showUserMoney();
		} catch (Exception e) {
			javax.swing.JOptionPane.showMessageDialog(null, e, "Exception", 0);
			e.printStackTrace();
		}
	}

	private void btnModUserClicked() {
		SysUserModUI dlgUserMod = new SysUserModUI();
		dlgUserMod.setModal(true);
		dlgUserMod.setVisible(true);
	}

	private void showCurrentUser() {
		LoginedUser curUser = LoginedUser.getInstance();
		String name = curUser.getEmpName();
		if (null == name || name.isEmpty())
			usrName.setText("匿名用户");
		else if (name.equals("安生"))
			usrName.setText("VIP:" + name);
		else {
			usrName.setText(name);
		}

	}

	public void showUserMoney() {
		//// if (null == name || name.isEmpty())
		//// usrName.setText("匿名用户");
		// else if (name.equals("安生"))
		// usrName.setText("VIP:" + name);
		// else {
		// usrName.setText(name);
		// }
		LoginedUser curUser = LoginedUser.getInstance();
		String money = String.valueOf(curUser.getUserMoney());
		userMoney.setText("余额:" + money);

	}

	// To be override by the detailed business block interface
	protected void onWindowClosing() {
		System.exit(0);
	}

	// To be override by the detailed business block interface
	protected void initContent() {
		reFresh.setMargin(new Insets(0, 0, 0, 0));
		reFresh.setFont(new Font("Dialog", 1, 20));
		reFresh.setBounds(50, 50, 100, 100);
		reFresh.setForeground(Color.yellow);
		reFresh.setContentAreaFilled(false); // 设置为false让button透明;

		reFresh.setFocusable(false);
		MouseListerDemo.setMouseLister(reFresh);
		// GetDailySalesService g = new GetDailySalesService();
		// contPan.add(reFresh);

		reFresh.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				showUserMoney();
			}
		});
	}

	// To be override by the detailed business block interface
	protected void btnExitClicked(ActionEvent Event) {
		System.exit(0);
	}

}
