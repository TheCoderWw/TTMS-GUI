package xupt.se.ttms.view.studio;

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

import xupt.se.ttms.model.Schedule;
import xupt.se.ttms.model.Studio;
import xupt.se.ttms.service.ScheduleSrv;
import xupt.se.ttms.service.StudioSrv;
import xupt.se.ttms.view.ServerSide.ServerSideUI;
import xupt.se.ttms.view.seat.SeatMgrUI;
import xupt.se.util.MouseListerDemo;

class StudioTable {
	private JTable jt;

	public StudioTable(JScrollPane jp) {

		DefaultTableModel tabModel = new DefaultTableModel() {
			private static final long serialVersionUID = 1L;

			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			};
		};

		tabModel.addColumn("id");
		tabModel.addColumn("name");
		tabModel.addColumn("row");
		tabModel.addColumn("column");
		tabModel.addColumn("desciption");

		// 初始化列明
		jt = new JTable(tabModel);

		// 设置各列的宽度
		TableColumnModel columnModel = jt.getColumnModel();
		jt.setRowHeight(50);
		// 隐藏ID这一列
		TableColumn column = columnModel.getColumn(0);
		// column.setMinWidth(10);
		// column.setMaxWidth(10);
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

		jp.add(jt);
		jp.setViewportView(jt);

	}

	public Studio getStudio() {
		int rowSel = jt.getSelectedRow();
		if (rowSel >= 0) {
			Studio stud = new Studio();
			stud.setID(Integer.parseInt(jt.getValueAt(rowSel, 0).toString()));
			stud.setName(jt.getValueAt(rowSel, 1).toString());
			stud.setRowCount(Integer.parseInt(jt.getValueAt(rowSel, 2).toString())); // 0
			stud.setColCount(Integer.parseInt(jt.getValueAt(rowSel, 3).toString()));
			if (jt.getValueAt(rowSel, 4) != null)
				stud.setIntroduction(jt.getValueAt(rowSel, 4).toString());
			else
				stud.setIntroduction("");
			return stud;
		} else {
			return null;
		}
	}

	// 创建JTable
	public void showStudioList(List<Studio> stuList) {
		try {
			DefaultTableModel tabModel = (DefaultTableModel) jt.getModel();
			tabModel.setRowCount(0);

			Iterator<Studio> itr = stuList.iterator();
			while (itr.hasNext()) {
				Studio stu = itr.next();
				Object data[] = new Object[5];
				data[0] = Integer.toString(stu.getID());
				data[1] = stu.getName();
				data[2] = Integer.toString(stu.getRowCount());
				data[3] = Integer.toString(stu.getColCount());
				data[4] = stu.getIntroduction();
				tabModel.addRow(data);
				;
			}
			jt.invalidate();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void SelectTheBank(int row) {
		// 选定该行
		jt.setRowSelectionInterval(row, row);
	}
}

public class StudioMgrUI extends ServerSideUI {
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
	private JButton btnAdd, btnEdit, btnDel, btnQuery, seatM;

	StudioTable tms; // 显示演出厅列表

	public StudioMgrUI() {

	}

	// To be override by the detailed business block interface
	@Override
	protected void initContent() {
		Rectangle rect = contPan.getBounds();

		ca1 = new JLabel("演出厅管理", JLabel.CENTER);
		ca1.setBounds(0, 5, rect.width, 30);
		ca1.setFont(new java.awt.Font("宋体", 1, 20));
		ca1.setForeground(Color.yellow);
		contPan.add(ca1);

		jsc = new JScrollPane();
		jsc.setBounds(0, 40, rect.width, rect.height - 90);
		contPan.add(jsc);

		hint = new JLabel("请输入演出厅名称:", JLabel.RIGHT);
		hint.setForeground(Color.YELLOW);
		hint.setBounds(60, rect.height - 45, 150, 30);
		contPan.add(hint);

		input = new JTextField();
		input.setBounds(220, rect.height - 45, 200, 30);
		contPan.add(input);

		// 查找 ，删除和编辑的按钮，其中含有相关的事件处理！
		btnQuery = new JButton("查找");
		btnQuery.setForeground(Color.YELLOW);
		// btnQuery.setBorderPainted(false); // 去除边框
		btnQuery.setFocusPainted(false); // 去除焦点
		btnQuery.setContentAreaFilled(false);
		MouseListerDemo.setMouseLister(btnQuery);
		btnQuery.setBounds(440, rect.height - 45, 60, 30);
		btnQuery.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent Event) {
				btnQueryClicked();
			}
		});
		contPan.add(btnQuery);

		seatM = new JButton("座位管理");
		seatM.setForeground(Color.YELLOW);
		// seatM.setBorderPainted(false); // 去除边框
		seatM.setFocusPainted(false); // 去除焦点
		seatM.setContentAreaFilled(false);
		MouseListerDemo.setMouseLister(seatM);
		seatM.setBounds(rect.width - 360, rect.height - 45, 130, 30);
		seatM.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent Event) {
				btnSeatClicked();
			}
		});
		contPan.add(seatM);

		btnAdd = new JButton("添加");
		btnAdd.setForeground(Color.YELLOW);
		// btnAdd.setBorderPainted(false); // 去除边框
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
		btnEdit.setForeground(Color.YELLOW);
		// btnEdit.setBorderPainted(false); // 去除边框
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
		btnDel.setForeground(Color.YELLOW);
		// btnDel.setBorderPainted(false); // 去除边框
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

		tms = new StudioTable(jsc);

		showTable();
	}

	private void btnAddClicked() {

		StudioAddUI addStuUI = null;

		addStuUI = new StudioAddUI();
		addStuUI.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		addStuUI.setWindowName("添加演出厅");
		addStuUI.toFront();
		addStuUI.setModalityType(JDialog.ModalityType.APPLICATION_MODAL);
		addStuUI.setVisible(true);
		if (addStuUI.getReturnStatus()) {
			showTable();
		}
	}

	private void btnModClicked() {
		Studio stud = tms.getStudio();
		if (null == stud) {
			JOptionPane.showMessageDialog(null, "请选择要修改的演出厅");
			return;
		}

		StudioEditUI modStuUI = new StudioEditUI(stud);
		modStuUI.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		modStuUI.setWindowName("修改演出厅");
		modStuUI.initData(stud);
		modStuUI.toFront();
		modStuUI.setModal(true);
		modStuUI.setModalityType(JDialog.ModalityType.APPLICATION_MODAL);
		modStuUI.setVisible(true);

		if (modStuUI.getReturnStatus()) {
			showTable();
		}
	}

	private void btnDelClicked() {
		Studio stud = tms.getStudio();
		if (null == stud) {
			JOptionPane.showMessageDialog(null, "请选择要删除的演出厅");
			return;
		}

		int confirm = JOptionPane.showConfirmDialog(null, "确认删除所选？", "删除", JOptionPane.YES_NO_OPTION);
		if (confirm == JOptionPane.YES_OPTION) {
			StudioSrv stuSrv = new StudioSrv();
			ScheduleSrv scheduleSrv = new ScheduleSrv();

			List<Schedule> schedList = scheduleSrv.Fetch("studio_id= " + stud.getID() + ";");
			if (schedList.isEmpty()) {
			} else {
				scheduleSrv.delete(schedList.get(0).getSched_id());
			}
			stuSrv.delete(stud.getID());

			showTable();

		}
	}

	private void btnQueryClicked() {
		String inp = input.getText();
		if (!inp.equals("")) {
			List<Studio> stuList = new StudioSrv().FetchAll();

			int i = 0;
			Iterator<Studio> it = stuList.iterator();
			while (it.hasNext()) {
				Studio stu = it.next();
				tms.SelectTheBank(i);
				if (inp.equals(stu.getName())) {
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
		List<Studio> stuList = new StudioSrv().FetchAll();
		tms.showStudioList(stuList);
	}

	private void btnSeatClicked() {
		Studio stud = tms.getStudio();
		if (null == stud) {
			JOptionPane.showMessageDialog(null, "请选择要进行座位管理的演出厅");
			return;
		}
		int stu_id = stud.getID();
		SeatMgrUI frmSeaMgr = new SeatMgrUI(stu_id);// 创建窗口
		frmSeaMgr.initCd();
		frmSeaMgr.setVisible(true);// 显示
		setVisible(false);
	}

	public static void main(String[] args) {
		StudioMgrUI frmStuMgr = new StudioMgrUI();
		frmStuMgr.setVisible(true);
	}
}
