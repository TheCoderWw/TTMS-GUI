package xupt.se.ttms.model;

import java.util.Iterator;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

import xupt.se.ttms.service.StudioSrv;

public class ScheduleSelectTable {

	private JTable jt;
	
	public ScheduleSelectTable(JScrollPane jsc) {

		DefaultTableModel tabModel = new DefaultTableModel() {
			private static final long serialVersionUID = 1L;

			public boolean isCellEditable(int row, int column) {
				return false;
			};
		};
		tabModel.addColumn("sched_id");
		tabModel.addColumn("上映演出厅");
		tabModel.addColumn("上映时间");
		tabModel.addColumn("票价");
		// 初始化列明
		jt = new JTable(tabModel);

		// 设置各列的宽度
		TableColumnModel columnModel = jt.getColumnModel();
		jt.setRowHeight(60);
		// 隐藏ID这一列
		TableColumn column = columnModel.getColumn(0);
		column.setMinWidth(0);
		column.setMaxWidth(0);
		column.setWidth(0);
		column.setPreferredWidth(10);

		column = columnModel.getColumn(1);
		column.setPreferredWidth(20);
		column = columnModel.getColumn(2);
		column.setPreferredWidth(20);
		column = columnModel.getColumn(3);
		column.setPreferredWidth(20);

		jsc.add(jt);
		jsc.setViewportView(jt);
	}

	public void showSelectList(List<Schedule> schedList) {
		DefaultTableModel tabModel = (DefaultTableModel) jt.getModel();
		tabModel.setRowCount(0);
		
		Iterator<Schedule> itr = schedList.iterator();
		while (itr.hasNext()) {
			Schedule sched = itr.next();
			StudioSrv stuSrv = new StudioSrv();
			List<Studio> stuList = stuSrv.Fetch("studio_id= " + sched.getStudio_id() + ";");
			if(!stuList.isEmpty()) {
				String stuName = stuList.get(0).getName();
				
				Object data[] = new Object[4];
				data[0] = Integer.toString(sched.getSched_id());
				data[1] = stuName;
				data[2] = sched.getSched_time();
				data[3] = sched.getSched_ticket_price();
				tabModel.addRow(data);
			}
		}
		jt.invalidate();
	}
	
	public int getSelectSchedId() {
		int rowSel = jt.getSelectedRow();
		if (rowSel >= 0) {
			return Integer.parseInt(jt.getValueAt(rowSel, 0).toString());
		}
		else {
			JOptionPane.showMessageDialog(null, "请选择要观看的场次");
			return -1;
		}
	}

}
