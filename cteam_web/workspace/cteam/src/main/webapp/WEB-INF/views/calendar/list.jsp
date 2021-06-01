<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href='css/fullcalendar.css' rel='stylesheet' />
<script src='js/fullcalendar.js'></script>
<script>

if("${msg}"!=""){
	var msg = '${msg}';
	var returnUrl = '${url}';
	alert(msg);
	document.location.href= returnUrl;
}

document.addEventListener('DOMContentLoaded', function() {
	var calendarEl = document.getElementById('calendar');
	
	var calendar = new FullCalendar.Calendar(calendarEl, {
    	headerToolbar: {
		left: 'prevYear,prev,next,nextYear today', 
		center: 'title',
		right: 'dayGridMonth,dayGridWeek,dayGridDay'
	},
	buttonText: {
		today: '오늘',
		month: '월',
		week: '주',
		day: '일',
		list: '주간 일정표'
	},
	initialDate: '2020-09-12',
	navLinks: true, // can click day/week names to navigate views
	editable: true,
	dayMaxEvents: true, // allow "more" link when too many events
	timeZone: 'local',
	weekends: true,
	nowIndicator: true,
	dayPopoverFormat: 'MM/DD dddd',
	longPressDelay: 0,
	eventLongPressDelay: 0,
	selectLongPressDelay: 0,  
	views: {
		month : {
			columnFormat : 'dddd'
		},
		agendaWeek : {
			columnFormat : 'M/D ddd',
			titleFormat  : 'YYYY년 M월 D일',
			eventLimit   : false
		},
		agendaDay : {
			columnFormat : 'dddd',
			eventLimit   : false
		},
		listWeek : {
			columnFormat : ''
		}
	},
	events: function (start, end, timezone, callback) {
		$.ajax({
			type: "get",
			url: "data.json",
			data: {
				// 화면이 바뀌면 Date 객체인 start, end 가 들어옴
				//startDate : moment(start).format('YYYY-MM-DD'),
				//endDate   : moment(end).format('YYYY-MM-DD')
			},
			success: function (response) {
				var fixedDate = response.map(function (array) {
					if (array.allDay && array.start !== array.end) {
					array.end = moment(array.end).add(1, 'days'); // 이틀 이상 AllDay 일정인 경우 달력에 표기시 하루를 더해야 정상출력
					}
				return array;
				});
				callback(fixedDate);
			}
		});
	},
	eventAfterAllRender: function (view) {
		if (view.name == "month") $(".fc-content").css('height', 'auto');
	},
		//일정 리사이즈
		eventResize: function (event, delta, revertFunc, jsEvent, ui, view) {
			$('.popover.fade.top').remove();

			/** 리사이즈시 수정된 날짜반영
			* 하루를 빼야 정상적으로 반영됨. */
			var newDates = calDateWhenResize(event);

			//리사이즈한 일정 업데이트
			$.ajax({
				type: "get",
				url: "",
				data: {
					//id: event._id,
					//....
				},
				success: function (response) {
					alert('수정: ' + newDates.startDate + ' ~ ' + newDates.endDate);
				}
			});

		},
		eventDragStart: function (event, jsEvent, ui, view) {
			draggedEventIsAllDay = event.allDay;
		},
		events: [

			<c:forEach items="${vo}" var ="pet">

				{
					title: '${pet.calendar_memo}',
					start: '${pet.calendar_date}'
				},
			
			</c:forEach>

		]
		
		/*events: [
        	{
			title: 'Long Event',
			start: '2020-09-07',
			end: '2020-09-10'
        	},
			{
			groupId: 999,
			title: 'Repeating Event',
			start: '2020-09-09T16:00:00'
        	},
			{
			groupId: 999,
			title: 'Repeating Event',
			start: '2020-09-16T16:00:00'
			},
			{
          title: 'Conference',
          start: '2020-09-11',
          end: '2020-09-13'
        },
        {
          title: 'Meeting',
          start: '2020-09-12T10:30:00',
          end: '2020-09-12T12:30:00'
        },
        {
          title: 'Lunch',
          start: '2020-09-12T12:00:00'
        },
        {
          title: 'Meeting',
          start: '2020-09-12T14:30:00'
        },
        {
          title: 'Happy Hour',
          start: '2020-09-12T17:30:00'
        },
        {
          title: 'Dinner',
          start: '2020-09-12T20:00:00'
        },
        {
          title: 'Birthday Party',
          start: '2020-09-13T07:00:00'
        },
        {
          title: 'Click for Google',
          url: 'http://google.com/',
          start: '2020-09-28'
        }
      ] */
      
	});

	calendar.render();
});



</script>
<style>
body {
	text-align: center;
	margin: 40px auto;
	padding: 0;
	font-family: Arial, Helvetica Neue, Helvetica, sans-serif;
	font-size: 14px;
}

#calendar {
	max-width: 95%;
	margin: 0 auto;
}

table {
	margin: 0 auto;
	border: 1px solid black;
	text-align: center;
	
}

.right {
	text-align: right;
}

#petselect {
	width: 95%;
	margin-bottom: 20px;
}
</style>
</head>
<body>
<div id='petselect' class='right'>
	<select style= "width: 80px; text-align: center;">
	<c:forEach items="${petList}" var ="pet">
		<option value="${pet}">${pet}</option>
	</c:forEach>
	</select>
</div>
	<div id='calendar'></div>
</body>
</html>