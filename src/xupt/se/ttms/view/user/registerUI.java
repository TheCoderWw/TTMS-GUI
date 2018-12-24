package xupt.se.ttms.view.user;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

import xupt.se.ttms.model.User;
import xupt.se.ttms.service.LoginedUser;
import xupt.se.ttms.service.UserSrv;
import xupt.se.ttms.view.admin.adminRegister;
import xupt.se.ttms.view.tmpl.MainUITmpl;
import xupt.se.util.MD5Util;
import xupt.se.util.MouseListerDemo;

public class registerUI extends JFrame {

	/**
	 * 
	 */
	UserSrv userSrv = new UserSrv();
	User user = new User();
	protected Container ct = getContentPane();
	protected JTextField jt1 = new JTextField(10);
	protected JButton enter = new JButton("登录");
	protected JButton userL = new JButton("用户登录");
	protected JButton register = new JButton("注册");
	protected JButton adminL = new JButton("管理员登录");
	protected JLabel userName = new JLabel("用户名:");
	protected JLabel passWord = new JLabel("密    码 :");
	protected JLabel hint = new JLabel("注意:管理员登录请点击左上角按钮!");
	protected JPasswordField jp = new JPasswordField(10);
	private static final long serialVersionUID = 1L;

	public registerUI() {

		this.setTitle("安悦影城登录系统");
		setLayout(null);
		setResizable(false);
		setSize(450, 250);
		int windowWidth = this.getWidth(); // 获得窗口宽
		int windowHeight = this.getHeight(); // 获得窗口高
		Toolkit kit = Toolkit.getDefaultToolkit();
		Dimension screenSize = kit.getScreenSize(); // 获取屏幕的尺寸
		int screenWidth = screenSize.width; // 获取屏幕的宽
		int screenHeight = screenSize.height; // 获取屏幕的高
		this.setLocation(screenWidth / 2 - windowWidth / 2, screenHeight / 2 - windowHeight / 2);// 设置窗口居中显示

		ct.setBackground(new Color(153, 50, 204));

		hint.setForeground(Color.YELLOW);
		hint.setBounds(150, 180, 250, 30);

		userName.setForeground(Color.WHITE);
		userName.setBounds(100, 50, 100, 30);

		passWord.setForeground(Color.WHITE);
		passWord.setBounds(100, 90, 100, 30);

		jt1.setBounds(150, 50, 200, 30);

		jp.setEchoChar('●'); // 回显字符
		jp.setBounds(150, 90, 200, 30);

		adminL.setBounds(5, 5, 100, 30);
		adminL.setBorderPainted(false); // 去除边框
		adminL.setFocusPainted(false); // 去除焦点
		adminL.setBackground(new Color(0, 0, 0));
		adminL.setForeground(Color.YELLOW);
		MouseListerDemo.setMouseLister(adminL);
		adminL.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				new adminRegister().setVisible(true);
				setVisible(false);

			}
		});

		enter.setForeground(Color.WHITE);
		enter.setBackground(new Color(0, 0, 0));
		enter.setBorderPainted(false); // 去除边框
		enter.setFocusPainted(false); // 去除焦点
		enter.setBounds(255, 130, 95, 30);
		MouseListerDemo.setMouseLister(enter);
		enter.addActionListener(new ActionListener() { // 点击事件

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String userName = jt1.getText();
				String passWord = String.valueOf(jp.getPassword()); // 通过MD5加密
				if (userName.length() == 0 || passWord.length() == 0) {
					JOptionPane.showMessageDialog(null, "请输入完整登录信息");
				} else {
					user.setUserName(userName);
					user.setPassWord(MD5Util.crypt(passWord));
					user.setUserMoney(userSrv.selectMoney(userName));
					if (userSrv.select(user)) {
						JOptionPane.showMessageDialog(null, "登陆成功");
						LoginedUser.getInstance().setUserID(userSrv.selectID(userName));
						LoginedUser.getInstance().setEmpName(userName);
						LoginedUser.getInstance().setUserMoney(user.getUserMoney());
						LoginedUser.getInstance().setPassWord(user.getPassWord());
						// System.out.println("jsjsj");
						// System.out.println(LoginedUser.getInstance().getUserID());
						new MainUITmpl().setVisible(true); // 调用MainUITmpl()
						setVisible(false); // 隐藏登录方法
					} else {
						JOptionPane.showMessageDialog(null, "账号或密码输入错误");
					}
				}
			}
		});

		register.setBackground(new Color(0, 0, 0));
		register.setForeground(Color.WHITE);
		register.setBorderPainted(false);
		register.setFocusPainted(false);

		register.setBounds(150, 130, 95, 30);
		register.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				new zhuce();
				setVisible(false);
				// JOptionPane.showMessageDialog(null, "注册成功,请登录");
			}
		});
		MouseListerDemo.setMouseLister(register);

		ImageIcon img = new ImageIcon("resource/image/BGpicture.jpg");
		// 要设置的背景图片
		JLabel imgLabel = new JLabel(img);
		// 将背景图放在标签里。
		this.getLayeredPane().add(imgLabel, new Integer(Integer.MIN_VALUE));
		// 将背景标签添加到jfram的LayeredPane面板里。
		imgLabel.setBounds(0, 0, img.getIconWidth(), img.getIconHeight());
		// 设置背景标签的位置
		Container contain = this.getContentPane();
		((JPanel) contain).setOpaque(false);
		// 将内容面板设为透明。将LayeredPane面板中的背景显示出来。
		this.setUndecorated(false);
		ct.add(adminL);
		ct.add(hint);
		ct.add(userName);
		ct.add(jt1);
		ct.add(passWord);
		ct.add(jp);
		ct.add(enter);
		ct.add(register);
		this.setVisible(true);

		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
	}

	public static void main(String[] args) {
		new registerUI();
	}
}
