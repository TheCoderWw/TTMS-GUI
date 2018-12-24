package xupt.se.ttms.view.play;

import java.awt.Color;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

import xupt.se.ttms.model.Play;
import xupt.se.ttms.model.Schedule;
import xupt.se.ttms.service.PlaySrv;
import xupt.se.ttms.service.ScheduleSrv;
import xupt.se.ttms.view.ServerSide.ServerSideUI;
import xupt.se.util.MouseListerDemo;

class PlayTable {
	/**
	 * 
	 */

	private static final long serialVersionUID = 1L;
	private JTable jt;

	public PlayTable(JScrollPane jp) {

		DefaultTableModel tabModel = new DefaultTableModel() {
			private static final long serialVersionUID = 1L;

			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			};
		};
		tabModel.addColumn("play_id");
		tabModel.addColumn("play_type_id");
		tabModel.addColumn("play_lang_id");
		tabModel.addColumn("play_name");
		tabModel.addColumn("play_introduction");
		tabModel.addColumn("play_image");
		tabModel.addColumn("play_length");
		tabModel.addColumn("play_ticket_price");
		tabModel.addColumn("play_status");
		// 初始化列明
		jt = new JTable(tabModel);

		// 设置各列的宽度
		TableColumnModel columnModel = jt.getColumnModel();
		jt.setRowHeight(50);
		// 隐藏ID这一列
		TableColumn column = columnModel.getColumn(0);
		// column.setMinWidth(0);
		// column.setMaxWidth(0);
		column.setWidth(10);
		column.setPreferredWidth(10);

		column = columnModel.getColumn(1);
		column.setPreferredWidth(10);
		column = columnModel.getColumn(2);
		column.setPreferredWidth(10);
		column = columnModel.getColumn(3);
		column.setPreferredWidth(10);
		column = columnModel.getColumn(4);
		column.setPreferredWidth(500);
		column = columnModel.getColumn(5);
		column.setPreferredWidth(10);
		column = columnModel.getColumn(6);
		column.setPreferredWidth(10);
		column = columnModel.getColumn(7);
		column.setPreferredWidth(10);
		column = columnModel.getColumn(8);
		column.setPreferredWidth(10);
		jp.add(jt);
		jp.setViewportView(jt);

	}

	public Play getPlay() {
		int rowSel = jt.getSelectedRow();
		if (rowSel >= 0) {
			Play play = new Play();
			play.setPlay_id(Integer.parseInt(jt.getValueAt(rowSel, 0).toString()));

			play.setPlay_type_id(Integer.valueOf(jt.getValueAt(rowSel, 1).toString()));

			play.setLang_id(Integer.parseInt(jt.getValueAt(rowSel, 2).toString()));

			play.setPlay_name((jt.getValueAt(rowSel, 3).toString()));

			// play.setPlay_introduction((jt.getValueAt(rowSel, 4).toString()));

			play.setPlay_image((jt.getValueAt(rowSel, 5).toString()));

			play.setPlay_length(Integer.parseInt(jt.getValueAt(rowSel, 6).toString()));

			play.setPlay_ticket_price(Integer.parseInt(jt.getValueAt(rowSel, 7).toString()));

			play.setPlay_status(Integer.parseInt(jt.getValueAt(rowSel, 8).toString()));

			if (jt.getValueAt(rowSel, 4) != null)
				play.setPlay_introduction(jt.getValueAt(rowSel, 4).toString());
			else
				play.setPlay_introduction("");

			return play;
		} else {
			return null;
		}

	}

	// 创建JTable
	public void showPlayList1(List<Play> playList) {
		try {
			DefaultTableModel tabModel = (DefaultTableModel) jt.getModel();
			tabModel.setRowCount(0);

			Iterator<Play> itr = playList.iterator();
			while (itr.hasNext()) {
				Play play = itr.next();
				Object data[] = new Object[9];
				data[0] = Integer.toString(play.getPlay_id());
				data[1] = play.getPlay_type_id();
				data[2] = play.getLang_id();
				data[3] = play.getPlay_name();
				data[4] = play.getPlay_introduction();
				data[5] = play.getPlay_image();
				data[6] = play.getPlay_length();
				data[7] = play.getPlay_ticket_price();
				data[8] = play.getPlay_status();
				tabModel.addRow(data);
				;
			}
			jt.invalidate();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void showPlayList(List<Play> palyList) {
		// TODO 自动生成的方法存根

	}

	public void SelectTheBank(int row) {
		// 选定该行
		jt.setRowSelectionInterval(row, row);
	}

	public Play getfilm() {
		// TODO 自动生成的方法存根
		return null;
	}
}

public class PlayMgrUI extends ServerSideUI {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JLabel ca1 = null; // 界面提示
	// 用来放表格的滚动控件
	private JScrollPane jsc;
	// 查找的提示和输出
	private JLabel hint;
	private JTextField input;

	// 查找，编辑和删除按钮
	private JButton btnAdd, btnEdit, btnDel, btnQuery;

	PlayTable tms; // 显示演出厅列表

	public PlayMgrUI() {

	}

	// To be override by the detailed business block interface
	@Override
	protected void initContent() {
		Rectangle rect = contPan.getBounds();

		ca1 = new JLabel("影片管理", JLabel.CENTER);
		ca1.setBounds(0, 5, rect.width, 30);
		ca1.setFont(new java.awt.Font("宋体", 1, 20));
		ca1.setForeground(Color.yellow);
		contPan.add(ca1);

		jsc = new JScrollPane();
		jsc.setBounds(0, 40, rect.width, rect.height - 90);
		contPan.add(jsc);

		hint = new JLabel("请输入电影名称:", JLabel.RIGHT);
		hint.setForeground(Color.YELLOW);
		hint.setBounds(60, rect.height - 45, 150, 30);
		contPan.add(hint);

		input = new JTextField();
		input.setBounds(220, rect.height - 45, 200, 30);
		contPan.add(input);

		// 查找 ，删除和编辑的按钮，其中含有相关的事件处理！
		btnQuery = new JButton("查找");
		btnQuery.setForeground(Color.yellow);
		btnQuery.setBounds(440, rect.height - 45, 60, 30);
		btnQuery.setFocusPainted(false); // 去除焦点
		btnQuery.setContentAreaFilled(false);
		MouseListerDemo.setMouseLister(btnQuery);
		btnQuery.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent Event) {
				btnQueryClicked();
			}
		});
		contPan.add(btnQuery);

		btnAdd = new JButton("添加");
		btnAdd.setForeground(Color.yellow);
		btnAdd.setFocusPainted(false); // 去除焦点
		btnAdd.setContentAreaFilled(false);
		MouseListerDemo.setMouseLister(btnAdd);
		btnAdd.setBounds(rect.width - 220, rect.height - 45, 60, 30);
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent Event) {
				btnAddClicked();
			}
		});
		contPan.add(btnAdd);

		btnEdit = new JButton("修改");
		btnEdit.setForeground(Color.yellow);
		btnEdit.setFocusPainted(false); // 去除焦点
		btnEdit.setContentAreaFilled(false);
		MouseListerDemo.setMouseLister(btnEdit);
		btnEdit.setBounds(rect.width - 150, rect.height - 45, 60, 30);
		btnEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent Event) {
				btnModClicked();
			}
		});
		contPan.add(btnEdit);

		btnDel = new JButton("删除");
		btnDel.setForeground(Color.yellow);
		btnDel.setFocusPainted(false); // 去除焦点
		btnDel.setContentAreaFilled(false);
		MouseListerDemo.setMouseLister(btnDel);
		btnDel.setBounds(rect.width - 80, rect.height - 45, 60, 30);
		btnDel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent Event) {
				btnDelClicked();
			}
		});
		contPan.add(btnDel);
		contPan.add(ca1);

		tms = new PlayTable(jsc);

		showTable();
	}

	private void btnAddClicked() {

		PlayAddUI addPlayUI = null;

		addPlayUI = new PlayAddUI();
		addPlayUI.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		addPlayUI.setWindowName("");
		addPlayUI.toFront();
		addPlayUI.setModalityType(JDialog.ModalityType.APPLICATION_MODAL);
		addPlayUI.setVisible(true);
		if (addPlayUI.getReturnStatus()) {
			showTable();
		}
	}

	private void btnModClicked() {
		Play play = tms.getPlay();
		if (null == play) {
			JOptionPane.showMessageDialog(null, "请选择要修改的电影");
			return;
		}

		PlayEditUI modPlayUI = new PlayEditUI(play);
		modPlayUI.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		modPlayUI.setWindowName("");
		modPlayUI.initData(play);
		modPlayUI.toFront();
		modPlayUI.setModal(true);
		modPlayUI.setModalityType(JDialog.ModalityType.APPLICATION_MODAL);
		modPlayUI.setVisible(true);

		if (modPlayUI.getReturnStatus()) {
			showTable();
		}
	}

	private void btnDelClicked() {
		Play play = tms.getPlay();
		if (null == play) {
			JOptionPane.showMessageDialog(null, "请选择要删除的电影");
			return;
		}

		int conPlay = JOptionPane.showConfirmDialog(null, "确认删除所选？", "删除", JOptionPane.YES_NO_OPTION);
		if (conPlay == JOptionPane.YES_OPTION) {
			PlaySrv playSrv = new PlaySrv();

			ScheduleSrv scheduleSrv = new ScheduleSrv();

			List<Schedule> schedList = scheduleSrv.Fetch("play_id= " + play.getPlay_id() + ";");

			if (!schedList.isEmpty()) {
				scheduleSrv.delete(schedList.get(0).getSched_id());
			}
			playSrv.delete(play.getPlay_id());
			showTable();
		}
	}

	private void btnQueryClicked() {
		String inp = input.getText();
		if (!inp.equals("")) {
			List<Play> playList = new PlaySrv().FetchAll();

			int i = 0;
			Iterator<Play> it = playList.iterator();
			while (it.hasNext()) {
				Play play = it.next();
				if (inp.equals(play.getPlay_name())) {
					tms.SelectTheBank(i);
					JOptionPane.showMessageDialog(null, "已经找到");
					break;
				} else if (!it.hasNext())
					JOptionPane.showMessageDialog(null, "抱歉,没有该演出厅!");

				i++;
			}
		} else {
			JOptionPane.showMessageDialog(null, "请输入检索条件");
		}
	}

	private void showTable() {
		// System.out.println("dadad");
		List<Play> playList = new PlaySrv().FetchAll();
		tms.showPlayList1(playList);
	}

	public static void main(String[] args) {
		PlayMgrUI playMgr = new PlayMgrUI();
		playMgr.setVisible(true);
	}
}
