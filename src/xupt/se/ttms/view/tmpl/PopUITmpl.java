package xupt.se.ttms.view.tmpl;

import java.awt.Color;
import java.awt.Image;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class PopUITmpl extends JDialog {
	private static final long serialVersionUID = 1L;
	private int frmWidth = 800;
	private int frmHeight = 600;
	public final ImagePanel headPan = new ImagePanel("resource/image/test.jpg");

	Image image = new ImageIcon("resource/image/BGpicture.jpg").getImage();
	public JPanel contPan = new BackgroundPanel(image);
	public JLabel windowName = new JLabel();

	public PopUITmpl() {
		this.setSize(frmWidth, frmHeight);
		this.setLocationRelativeTo(null); // 放置在屏幕中间
		this.setResizable(false);
		this.setLayout(null);

		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				onWindowClosing();
			}
		});

		headPan.setBounds(0, 0, frmWidth, 60);
		headPan.setLayout(null);
		this.add(headPan);

		contPan.setBounds(0, 60, frmWidth, this.frmHeight - 80);
		contPan.setLayout(null);
		contPan.setBackground(new Color(47, 79, 79));
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
					new PopUITmpl().setVisible(true);

				} catch (Exception e) {
					javax.swing.JOptionPane.showMessageDialog(null, e, "Exception", 0);
					e.printStackTrace();
				}
			}
		});

	}

	private void initHeader() {
		try {

			windowName.setBounds(frmWidth - 130, 5, 160, 50); // 改过160 ->130
			windowName.setFont(new java.awt.Font("dialog", 1, 20));
			windowName.setForeground(Color.blue);
			headPan.add(windowName);
			setWindowName(""); // 改过

		} catch (Exception e) {
			javax.swing.JOptionPane.showMessageDialog(null, e, "Exception", 0);
			e.printStackTrace();
		}
	}

	// Set the name of the popup window
	public void setWindowName(String name) {
		windowName.setText(""); // 改过
	}

	// To be override by the detailed business block interface
	protected void onWindowClosing() {
		this.dispose();
	}

	// To be override by the detailed business block interface
	protected void initContent() {

	}

}
