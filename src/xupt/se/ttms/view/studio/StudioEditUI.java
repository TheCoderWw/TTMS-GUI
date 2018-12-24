package xupt.se.ttms.view.studio;

import javax.swing.JOptionPane;

import xupt.se.ttms.model.Studio;
import xupt.se.ttms.service.SeatSrv;
import xupt.se.ttms.service.StudioSrv;;

public class StudioEditUI extends StudioAddUI {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Studio stud;

	public StudioEditUI(Studio stu) {
		initData(stu);
	}

	public void initData(Studio stu) {
		if (null == stu) {
			return;
		}
		this.setTitle("修改演出厅");
		txtName.setText(stu.getName());
		txtRow.setText(Integer.toString(stu.getRowCount()));
		txtColumn.setText(Integer.toString(stu.getColCount()));
		txtIntro.setText(stu.getIntroduction());
		stud = stu;
		this.invalidate();
	}

	@Override
	protected void btnSaveClicked() {
		if (txtName.getText() != null && txtRow.getText() != null && txtColumn.getText() != null) {
			StudioSrv stuSrv = new StudioSrv();
			Studio stu = stud;
			stu.setName(txtName.getText());
			stu.setRowCount(Integer.parseInt(txtRow.getText()));
			stu.setColCount(Integer.parseInt(txtColumn.getText()));
			stu.setIntroduction(txtIntro.getText());
			//删除并重新初始化座位
			SeatSrv seatSrv = new SeatSrv();
			seatSrv.deleteByStudio(stu.getId());
			stuSrv.modify(stu);
			seatSrv.initSeat(stu.getId(), stu.getRowCount(), stu.getColCount());
			/*
			stuSrv.delete(stu.getId());
			stuSrv.add(stu);

			int stu_id = stuSrv.Fetch(" studio_name = '" + stu.getName() + "' ").get(0).getID();
			int row = stu.getRowCount();
			int col = stu.getColCount();
			SeatSrv seatSrv = new SeatSrv();
			seatSrv.initSeat(stu_id, row, col);
*/	
			// stuSrv.modify(stu);
			this.setVisible(false);
			rst = true;

		} else {
			JOptionPane.showMessageDialog(null, "数据不完整");
		}
	}

}
