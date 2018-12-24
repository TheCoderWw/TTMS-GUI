package xupt.se.ttms.idao;

import java.util.List;

import xupt.se.ttms.model.Play;

public interface iPlayDAO {
	public int insert(Play play);

	public int update(Play play);

	public int delete(int ID);

	public List<Play> select(String play);
}
