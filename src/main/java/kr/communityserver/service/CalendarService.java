package kr.communityserver.service;

import kr.communityserver.DTO.CalendarDTO;
import kr.communityserver.entity.Calendar;
import kr.communityserver.repository.CalendarRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class CalendarService {

    private final CalendarRepository calendarRepository;
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

}
