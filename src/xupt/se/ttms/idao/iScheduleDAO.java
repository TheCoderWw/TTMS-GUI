package xupt.se.ttms.idao;

import java.util.List;

import xupt.se.ttms.model.Schedule;

public interface iScheduleDAO {
	public int insert(Schedule stu);
	public int update(Schedule stu);
	public int delete(int ID);
	public List<Schedule> select(String condt);
	public List<Schedule> selectPlay(String condt); 
}

