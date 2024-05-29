package kr.communityserver.controller.calendar;

import kr.communityserver.DTO.CalendarDTO;
import kr.communityserver.entity.Calendar;
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
        log.info("dd"+calendarDTO.getUid());
        Calendar calendar = calendarService.insertCalendar(calendarDTO);
        return ResponseEntity.ok().body(calendar);
    }

}
