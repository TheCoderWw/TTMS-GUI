package xupt.se.ttms.view.play;

import javax.swing.JOptionPane;

import xupt.se.ttms.model.Dict;
import xupt.se.ttms.model.Play;
import xupt.se.ttms.service.DictSrv;
import xupt.se.ttms.service.PlaySrv;

public class PlayEditUI extends PlayAddUI {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Play play;
	private DictSrv dictSrv;
	private Dict dict;

	public PlayEditUI(Play play) {
		initData(play);
	}

	public void initData(Play play) {
		if (null == play) {
			return;
		}
		txtName.setText(play.getPlay_name());
		this.setTitle("修改电影");
		dictSrv = new DictSrv();
		/*
		 * dictSrv.FetchByID(play.getPlay_type_id()).get(0).getDict_value()
		 * play.getPlay_type_id() -----拿到play的id
		 * dictSrv.FetchByID(play.getPlay_type_id())----------通过id拿到该id的所有数据的List
		 * dictSrv.FetchByID(play.getPlay_type_id()).get(0).getDict_value()
		 * -----通过List的get(int index)方法 与getDict_value()拿到要的该id的对应类型value;
		 */
		jcType.setSelectedItem(dictSrv.FetchByID(play.getPlay_type_id()).get(0).getDict_value());

		jcLang.setSelectedItem(dictSrv.FetchByID(play.getLang_id()).get(0).getDict_value());

		txtImg.setText(play.getPlay_image());
		txtRow.setText((String.valueOf(play.getPlay_ticket_price())));
		txtColumn.setText(String.valueOf(play.getPlay_length()));
		txtIntro.setText(play.getPlay_introduction());
		this.play = play;
		this.invalidate();
	}

	@Override
	protected void btnSaveClicked() {
		if (txtName.getText() != null && txtRow.getText() != null && txtColumn.getText() != null) {
			PlaySrv playSrv = new PlaySrv();
			Play play = this.play;

			// 注:切记不可直接将 jcType.getSelectedItem() 直接传入下一层,具体见DAO层 SQL 语句
			dc = new DictSrv().Fetch(" dict_value = '" + jcType.getSelectedItem() + "' ");
			play.setPlay_name(txtName.getText());
			play.setPlay_ticket_price(Integer.parseInt(txtRow.getText()));
			play.setPlay_length(Integer.parseInt(txtColumn.getText()));
			play.setPlay_introduction(txtIntro.getText());
			play.setPlay_type_id(dc.get(0).getDict_id());
			play.setPlay_image(txtImg.getText());
			dc = new DictSrv().Fetch(" dict_value = '" + jcLang.getSelectedItem() + "' ");
			play.setLang_id(dc.get(0).getDict_id());
			playSrv.modify(play);
			this.setVisible(false);
			rst = true;

		} else {
			JOptionPane.showMessageDialog(null, "数据不完整");
		}
	}

}
