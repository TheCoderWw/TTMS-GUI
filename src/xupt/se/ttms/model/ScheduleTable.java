package xupt.se.ttms.model;

import java.util.Iterator;
import java.util.List;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

import xupt.se.ttms.service.PlaySrv;
import xupt.se.ttms.service.StudioSrv;

public class ScheduleTable {
	private JTable jt;

	public ScheduleTable(JScrollPane jp) {

		DefaultTableModel tabModel = new DefaultTableModel() {
			private static final long serialVersionUID = 1L;

			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			};
		};
		tabModel.addColumn("sched_id");
		tabModel.addColumn("演出厅名称");
		tabModel.addColumn("剧目名称");
		tabModel.addColumn("上映时间");
		tabModel.addColumn("票价");
		// 初始化列明
		jt = new JTable(tabModel);

		// 设置各列的宽度
		TableColumnModel columnModel = jt.getColumnModel();
		jt.setRowHeight(50);
		// 隐藏ID这一列
		TableColumn column = columnModel.getColumn(0);
		column.setMinWidth(0);
		column.setMaxWidth(0);
		column.setWidth(0);
		column.setPreferredWidth(10);

		column = columnModel.getColumn(1);
		column.setPreferredWidth(10);
		column = columnModel.getColumn(2);
		column.setPreferredWidth(10);
		column = columnModel.getColumn(3);
		column.setPreferredWidth(20);
		column = columnModel.getColumn(4);
		column.setPreferredWidth(20);

		jp.add(jt);
		jp.setViewportView(jt);

	}

	public Schedule getSchedule() {
		int rowSel = jt.getSelectedRow();
		if (rowSel >= 0) {
			Schedule sched = new Schedule();
			sched.setSched_id(Integer.parseInt(jt.getValueAt(rowSel, 0).toString()));
			// 获取studio_id
			StudioSrv stuSrv = new StudioSrv();
			List<Studio> stuList = stuSrv.Fetch("studio_name= '" + jt.getValueAt(rowSel, 1).toString() + "';");
			sched.setStudio_id(stuList.get(0).getID());
			// 获取play_id
			PlaySrv playSrv = new PlaySrv();
			List<Play> playList = playSrv.Fetch("play_name= '" + jt.getValueAt(rowSel, 2).toString() + "';");
			sched.setPlay_id(playList.get(0).getPlay_id());
			sched.setSched_time(jt.getValueAt(rowSel, 3).toString());
			sched.setSched_ticket_price(Double.parseDouble(jt.getValueAt(rowSel, 4).toString()));
			return sched;
		} else {
			return null;
		}
	}

	public void showScheduleList(List<Schedule> schedList) {
		try {
			DefaultTableModel tabModel = (DefaultTableModel) jt.getModel();
			tabModel.setRowCount(0);

			Iterator<Schedule> itr = schedList.iterator();
			while (itr.hasNext()) {

				Schedule sched = itr.next();

				StudioSrv stuSrv = new StudioSrv();
				List<Studio> stuList = stuSrv.Fetch("studio_id= " + sched.getStudio_id() + ";");
				if (!stuList.isEmpty()) {
					String stuName = stuSrv.Fetch("studio_id =" + sched.getStudio_id()).get(0).getName();
					PlaySrv playSrv = new PlaySrv();
					String playName = playSrv.Fetch("play_id= " + sched.getPlay_id() + ";").get(0).getPlay_name();

					Object data[] = new Object[5];
					data[0] = Integer.toString(sched.getSched_id());
					data[1] = stuName;
					data[2] = playName;
					data[3] = sched.getSched_time();
					data[4] = sched.getSched_ticket_price();
					tabModel.addRow(data);
					;
				}
			}
			jt.invalidate();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 选定该行
	public void SelectTheBank(int row) {
		jt.setRowSelectionInterval(row, row);
	}

}
