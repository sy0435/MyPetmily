package calendar;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class CalendarDAO implements CalendarService{

	@Autowired private SqlSession sql;

	@Override
	public List<CalendarVO> calendar_list(String id) {
		return sql.selectList("calendar.mapper.calendar", id);
	}
	
	@Override
	public List<String> calendar_petList(String id) {
		return sql.selectList("calendar.mapper.petlist", id);
	}
}
