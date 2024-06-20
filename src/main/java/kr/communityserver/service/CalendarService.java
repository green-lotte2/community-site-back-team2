package kr.communityserver.service;

import kr.communityserver.dto.CalendarDTO;
import kr.communityserver.dto.CalendarTypeDTO;
import kr.communityserver.entity.Calendar;
import kr.communityserver.entity.CalendarType;
import kr.communityserver.repository.CalendarRepository;
import kr.communityserver.repository.CalendarTypeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class CalendarService {

    private final CalendarRepository calendarRepository;
    private final CalendarTypeRepository calendarTypeRepository;
    private final ModelMapper modelMapper;

    public Calendar insertCalendar(CalendarDTO calendarDTO) {
        Calendar calendar = modelMapper.map(calendarDTO, Calendar.class);
        return calendarRepository.save(calendar);
    }

    public List<Calendar> findSchedual(String uid) {
        return calendarRepository.findByUid(uid);
    }

    public void deleteSchedual(int id) {
        calendarRepository.deleteById(id);
    }

    public List<CalendarType> findCalendarTypes(String uid) {
        return calendarTypeRepository.findByUid(uid);
    }
    public CalendarType insertCalendarType(CalendarTypeDTO calendarTypeDTO) {
        CalendarType calendarType = modelMapper.map(calendarTypeDTO, CalendarType.class);
        return calendarTypeRepository.save(calendarType);
    }

    public ResponseEntity<List<Calendar>> findTodaysCalendars(String uid, LocalDateTime start, LocalDateTime end) {

        List<Calendar> calendarList = calendarRepository.findByStartBetweenOrEndBetweenAndUid(start, end, start, end, uid);
        return ResponseEntity.ok().body(calendarList);
    }

}
