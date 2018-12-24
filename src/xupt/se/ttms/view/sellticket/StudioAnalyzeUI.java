package xupt.se.ttms.view.sellticket;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import xupt.se.ttms.model.Studio;
import xupt.se.ttms.service.SaleAnalyzeSrv;
import xupt.se.ttms.view.ServerSide.ServerSideUI;
import xupt.se.util.MouseListerDemo;

public class StudioAnalyzeUI extends ServerSideUI {

	private static final long serialVersionUID = 1L;
	private JPanel contain, singlePan;
	private JLabel textName, textEarn, header;
	private JButton exit;

	public StudioAnalyzeUI() {
		Rectangle rect = contPan.getBounds();

		SaleAnalyzeSrv saleAnalyze = new SaleAnalyzeSrv();
		List<Studio> stuList = saleAnalyze.getAllStudio();
		// 构建容器
		contain = new JPanel(new GridLayout(stuList.size(), 1));
		contain.setBounds(0, 200, rect.width, 400);

		// 添加头部
		header = new JLabel("售票分析(演出厅)", JLabel.CENTER);
		header.setBounds(0, 10, rect.width, 30);
		header.setFont(new java.awt.Font("宋体", 1, 20));
		header.setForeground(Color.yellow);
		contPan.add(header);

		header = new JLabel("演出厅名称", JLabel.CENTER);
		header.setBounds(0, 30, rect.width / 2, 30);
		header.setFont(new java.awt.Font("宋体", 1, 20));
		header.setForeground(Color.yellow);
		contPan.add(header);

		header = new JLabel("演出厅销售额", JLabel.CENTER);
		header.setBounds(rect.width / 2, 30, rect.width / 2, 30);
		header.setFont(new java.awt.Font("宋体", 1, 20));
		header.setForeground(Color.yellow);
		contPan.add(header);

		// 添加内容
		for (Studio s : stuList) {
			singlePan = new JPanel();
			singlePan.setLayout(null);
			singlePan.setBounds(0, 0, rect.width, 200);

			int sale_play = saleAnalyze.addByStudio(s.getId());
			textName = new JLabel(s.getName(), JLabel.CENTER);
			textName.setFont(new java.awt.Font("宋体", 1, 20));
			textName.setBounds(0, 0, rect.width / 2, 100);
			textName.setBorder(BorderFactory.createLineBorder(Color.gray));
			singlePan.add(textName);

			textEarn = new JLabel(sale_play + "", JLabel.CENTER);
			textEarn.setFont(new java.awt.Font("宋体", 1, 20));
			textEarn.setBounds(rect.width / 2, 0, rect.width / 2 - 10, 100);
			textEarn.setBorder(BorderFactory.createLineBorder(Color.gray));
			singlePan.add(textEarn);

			contain.add(singlePan);
		}

		// 添加容器到页面
		contPan.add(contain);

		exit = new JButton("返回");
		exit.setForeground(Color.YELLOW);
		// seatM.setBorderPainted(false); // 去除边框
		exit.setFocusPainted(false); // 去除焦点
		exit.setContentAreaFilled(false);
		MouseListerDemo.setMouseLister(exit);
		exit.setBounds(rect.width - 360, rect.height - 45, 130, 30);
		exit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent Event) {
				btnExitClicked();
			}
		});
		contPan.add(exit);
	}

	public void btnExitClicked() {
		SellTicketMgrUI analyzeUI = new SellTicketMgrUI();
		analyzeUI.initCd();
		analyzeUI.setVisible(true);
		this.setVisible(false);
	}
}
