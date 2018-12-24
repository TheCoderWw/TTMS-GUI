package xupt.se.ttms.view.sellticket;

import java.awt.Color;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;

import xupt.se.ttms.service.SaleAnalyzeSrv;
import xupt.se.ttms.view.ServerSide.ServerSideUI;

public class SellTicketMgrUI extends ServerSideUI {

	private static final long serialVersionUID = 1L;
	private JButton showByStu, showByPlay;
	private JLabel totalSale, header;

	public void initCd() {
		Rectangle rect = contPan.getBounds();

		Font btnFont = new Font("华文行楷", Font.BOLD, 18);
		// 添加头部
		header = new JLabel("售票分析", JLabel.CENTER);
		header.setBounds(0, 10, rect.width, 30);
		header.setFont(new java.awt.Font("宋体", 1, 20));
		header.setForeground(Color.yellow);
		contPan.add(header);

		totalSale = new JLabel("目前影院总盈利为 : " + new SaleAnalyzeSrv().addAllSale() + "元");
		totalSale.setBounds(rect.width / 2 - 300, 180, 300, 80);
		totalSale.setForeground(Color.YELLOW);
		totalSale.setFont(btnFont);
		contPan.add(totalSale);

		showByStu = new JButton("查看各演出厅的销售额");
		showByStu.setBounds(rect.width / 2 - 150 - 150, 280, 250, 80);
		showByStu.setFocusPainted(false);
		showByStu.setFont(btnFont);
		showByStu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent Event) {
				btnStuClicked();
			}
		});
		contPan.add(showByStu);

		showByPlay = new JButton("查看各剧目的销售额");
		showByPlay.setBounds(rect.width / 2 + 50, 280, 250, 80);
		showByPlay.setFocusPainted(false);
		showByPlay.setFont(btnFont);
		showByPlay.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent Event) {
				btnPlayClicked();
			}
		});
		contPan.add(showByPlay);
	}

	public void btnPlayClicked() {
		new PlayAnalyzeUI().setVisible(true);
		setVisible(false);
	}

	public void btnStuClicked() {
		new StudioAnalyzeUI().setVisible(true);
		setVisible(false);
	}

	public static void main(String[] args) {
		SellTicketMgrUI analyzeUI = new SellTicketMgrUI();
		analyzeUI.initCd();
		analyzeUI.setVisible(true);
	}

}
