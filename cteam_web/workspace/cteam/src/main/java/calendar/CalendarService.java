package calendar;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public interface CalendarService {

	List<CalendarVO>  calendar_list(String id);
	List<String> calendar_petList(String id);
}
