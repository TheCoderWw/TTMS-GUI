package xupt.se.ttms.view.play;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import xupt.se.ttms.model.Dict;
import xupt.se.ttms.model.Play;
import xupt.se.ttms.service.DictSrv;
import xupt.se.ttms.service.PlaySrv;
import xupt.se.ttms.view.tmpl.PopUITmpl;
import xupt.se.util.MyComboBox;

public class PlayAddUI extends PopUITmpl implements ActionListener {

	private static final long serialVersionUID = 1L;

	private JButton btnCancel, btnSave; // 取消，保存按鈕

	protected boolean rst = false; // 操作结果
	private JLabel lblName, lblType, lblRow, lblLang, lblColumn, lblIntro, lblImg;
	protected JTextField txtName, txtRow, txtColumn, txtImg;
	protected JTextArea txtIntro;
	protected JComboBox<String> jcType, jcLang;
	protected List<Dict> dc = null;
	JScrollPane js = null;

	@Override
	protected void initContent() {
		this.setTitle("添加电影");

		lblName = new JLabel("电影名称:");
		lblName.setBounds(60, 30, 80, 30);
		lblName.setForeground(Color.white);
		contPan.add(lblName);

		txtName = new JTextField();
		txtName.setBounds(150, 30, 120, 30);
		contPan.add(txtName);

		lblRow = new JLabel("价格  :");
		lblRow.setBounds(60, 80, 80, 30);
		lblRow.setForeground(Color.white);
		contPan.add(lblRow);

		txtRow = new JTextField();
		txtRow.setBounds(150, 80, 120, 30);
		contPan.add(txtRow);

		lblType = new JLabel("电影类型:");
		lblType.setBounds(340, 30, 120, 30);
		lblType.setForeground(Color.white);
		contPan.add(lblType);

		DictSrv dictSrv = new DictSrv();
		dc = dictSrv.FetchByParentName("PLAYTYPE");

		jcType = new JComboBox<String>(new MyComboBox(dc));
		jcType.setBounds(430, 30, 120, 30);
		contPan.add(jcType);

		lblLang = new JLabel("影片语言:");
		lblLang.setBounds(600, 30, 60, 30);
		lblLang.setForeground(Color.white);
		contPan.add(lblLang);
		dc = dictSrv.FetchByParentName("PLAYLANG");

		jcLang = new JComboBox<>(new MyComboBox(dc));
		jcLang.setBounds(670, 30, 100, 30);
		contPan.add(jcLang);

		lblColumn = new JLabel("影片时长");
		lblColumn.setBounds(340, 80, 80, 30);
		lblColumn.setForeground(Color.white);
		contPan.add(lblColumn);
		txtColumn = new JTextField();
		txtColumn.setBounds(430, 80, 120, 30);
		contPan.add(txtColumn);

		lblIntro = new JLabel("影片简介:");
		lblIntro.setBounds(60, 130, 80, 30);
		lblIntro.setForeground(Color.white);
		contPan.add(lblIntro);
		txtIntro = new JTextArea();
		txtIntro.setBounds(150, 130, 400, 100);
		txtIntro.setLineWrap(true);

		contPan.add(txtIntro);

		lblImg = new JLabel("电影海报:");
		lblImg.setBounds(60, 240, 80, 30);
		lblImg.setForeground(Color.white);
		contPan.add(lblImg);
		txtImg = new JTextField();
		txtImg.setBounds(150, 240, 400, 40);
		contPan.add(txtImg);

		btnSave = new JButton("保存");
		btnSave.addActionListener(this);
		btnSave.setBounds(60, 450, 60, 30);
		contPan.add(btnSave);

		btnCancel = new JButton("取消");
		btnCancel.addActionListener(this);
		btnCancel.setBounds(500, 450, 60, 30);
		contPan.add(btnCancel);

	}

	public boolean getReturnStatus() {
		return rst;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnCancel) {
			rst = false;
			this.setVisible(false);
		} else if (e.getSource() == btnSave) {
			btnSaveClicked();
		}
	}

	protected void btnSaveClicked() {
		if (!txtName.getText().equals("") && !txtRow.getText().equals("") && !txtColumn.getText().equals("")) {
			PlaySrv playSrv = new PlaySrv();

			Play play = new Play();
			// 通过类型名拿到id
			dc = new DictSrv().Fetch(" dict_value = '" + jcType.getSelectedItem() + "' ");

			play.setPlay_name(txtName.getText());
			play.setPlay_ticket_price(Integer.parseInt(txtRow.getText()));
			play.setPlay_length(Integer.parseInt(txtColumn.getText()));
			play.setPlay_introduction(txtIntro.getText());
			play.setPlay_type_id(dc.get(0).getDict_id());
			play.setPlay_image(txtImg.getText());
			// 通过语言名拿到id
			dc = new DictSrv().Fetch(" dict_value = '" + jcLang.getSelectedItem() + "' ");
			play.setLang_id(dc.get(0).getDict_id());
			play.setPlay_status(1);

			if (playSrv.add(play) == 1) {
				JOptionPane.showMessageDialog(null, "保存成功");
			}

			this.setVisible(false);
			rst = true;
		} else {
			JOptionPane.showMessageDialog(null, "数据不完整");
		}
	}

}
