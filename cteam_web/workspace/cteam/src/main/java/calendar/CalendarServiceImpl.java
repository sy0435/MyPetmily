package calendar;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CalendarServiceImpl implements CalendarService{

	@Autowired private CalendarDAO dao;
	
	@Override
	public List<CalendarVO>  calendar_list(String id) {
		return dao.calendar_list(id);
	}

	@Override
	public List<String> calendar_petList(String id) {
		return dao.calendar_petList(id);
	}
}
