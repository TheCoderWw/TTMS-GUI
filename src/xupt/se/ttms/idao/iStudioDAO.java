/**
 * 
 */
package xupt.se.ttms.idao;

import java.util.List;

import xupt.se.ttms.model.Studio;

/**
 * @author Administrator
 *
 */
public interface iStudioDAO {
	public int insert(Studio stu);

	public int update(Studio stu);

	public int delete(int ID);

	public List<Studio> select(String condt);
}
