package kr.communityserver.controller.calendar;

import kr.communityserver.dto.CalendarDTO;
import kr.communityserver.dto.CalendarTypeDTO;
import kr.communityserver.entity.Calendar;
import kr.communityserver.entity.CalendarType;
import kr.communityserver.service.CalendarService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
public class CalendarController {

    private final CalendarService calendarService;

    @GetMapping("/calendar")
    public ResponseEntity<List<Calendar>> getCalendar() {
        return ResponseEntity.ok().body(calendarService.findSchedual("test1"));
    }

    @PostMapping("/calendar/insert")
    public ResponseEntity insert(@RequestBody CalendarDTO calendarDTO) {
        log.info("dd"+calendarDTO);
        Calendar calendar = calendarService.insertCalendar(calendarDTO);
        return ResponseEntity.ok().body(calendar);
    }

    @DeleteMapping("/calendar/delete")
    public ResponseEntity delete(int delId) {
        log.info("dd"+delId);
        calendarService.deleteSchedual(delId);
        return ResponseEntity.ok().body(delId);
    }


    @GetMapping("/calendar/type")
    public ResponseEntity<List<CalendarType>> getCalendarType() {
        return ResponseEntity.ok().body(calendarService.findCalendarTypes("test1"));
    }

    @PostMapping("/calendar/type")
    public ResponseEntity<CalendarType> insertCalendarType(@RequestBody CalendarTypeDTO calendarTypeDTO) {
        log.info("확인"+calendarTypeDTO);
        return ResponseEntity.ok().body(calendarService.insertCalendarType(calendarTypeDTO));
    }

}
