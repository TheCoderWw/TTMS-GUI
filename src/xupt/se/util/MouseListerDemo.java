package xupt.se.util;

import java.awt.Cursor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;

public class MouseListerDemo {
	public MouseListerDemo() {

	}

	public static void setMouseLister(JButton bt) {
		bt.addMouseListener(new MouseAdapter() { // 鼠标放在按钮上显示手指
			public void mouseEntered(MouseEvent e) {
				bt.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}

			public void mouseExited(MouseEvent e) {
				bt.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}
		});
	}
}
