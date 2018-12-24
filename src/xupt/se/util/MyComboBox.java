package xupt.se.util;

import java.util.Iterator;
import java.util.List;

import javax.swing.AbstractListModel;
import javax.swing.ComboBoxModel;

import xupt.se.ttms.model.Dict;

public class MyComboBox extends AbstractListModel<String> implements ComboBoxModel<String> {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String sel = null;
	String[] test = null;

	public MyComboBox(List<Dict> list) {
		// TODO Auto-generated constructor stub

		test = new String[list.size()];
		Iterator<Dict> it = list.iterator();
		int i = 0;
		while (it.hasNext()) {
			String tem = it.next().getDict_value();
			test[i] = tem;
			i++;
		}
	}

	@Override
	public int getSize() {
		// TODO Auto-generated method stub
		return test.length;
	}

	@Override
	public String getElementAt(int index) {
		// TODO Auto-generated method stub
		return test[index];
	}

	@Override
	public void setSelectedItem(Object anItem) {
		// TODO Auto-generated method stub
		sel = (String) anItem;
	}

	@Override
	public Object getSelectedItem() {
		// TODO Auto-generated method stub
		return sel;
	}

}