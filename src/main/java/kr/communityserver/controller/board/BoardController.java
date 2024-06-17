package kr.communityserver.controller.board;


import jakarta.servlet.http.HttpServletRequest;
import kr.communityserver.dto.BoardDTO;
import kr.communityserver.dto.PageRequestDTO;
import kr.communityserver.dto.PageResponseDTO;
import kr.communityserver.dto.ReportDTO;
import kr.communityserver.entity.Board;
import kr.communityserver.repository.BoardRepository;
import kr.communityserver.service.BoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
@RestController
public class BoardController {

    private final BoardService boardService;
    private final BoardRepository boardRepository;
    private ModelMapper modelMapper;



    // 글목록
    @GetMapping("/board/list")
    public PageResponseDTO<BoardDTO> list(Model model, String cate, PageRequestDTO pageRequestDTO){
        log.info("pageRequsestDTO : " + pageRequestDTO);

        PageResponseDTO pageResponseDTO = null;

        if(pageRequestDTO.getKeyword() == null) {
            // 일반 글 목록 조회
            pageResponseDTO = boardService.list(pageRequestDTO);
            log.info("pageResponseDTO : " + pageResponseDTO);
        }else {
            // 검색 글 목록 조회
            pageResponseDTO = boardService.searchArticles(pageRequestDTO);
        }

        log.info("pageResponseDTO : " + pageResponseDTO);
       // model.addAttribute(pageResponseDTO);

        return pageResponseDTO;
    }

    // 글보기
    @GetMapping("/board/view/{cate}/{no}")
    public ResponseEntity<BoardDTO> boardView(@PathVariable(name ="cate") String cate, @PathVariable(name ="no") int no){
        BoardDTO boardDTO = boardService.get(cate, no);
        log.info("cate뭐야 : " + cate);
        log.info("no뭐야 : " + no);

        return ResponseEntity.ok(boardDTO);
    }



    // 글쓰기
    //HttpServletRequest 객체는 클라이언트의 HTTP 요청 정보를 담고있음
    //@RequestBody가 지정된 boardDTO객체는 클라이언트에서 전송된 JSON 데이터를 자동으로 역직렬화하여 자바 객체로 변환
    @PostMapping("/board/write")
    public Map<String, Integer> boardWrite(HttpServletRequest req, @RequestBody BoardDTO boardDTO){

        //클라이언트의 IP 주소를 추출하여 BoardDTO 객체의 regip 속성에 설정
        boardDTO.setRegip(req.getRemoteAddr());


        //게시글이 성공적으로 등록되면 해당 게시글의 고유 번호 반환
        int no = boardService.register(boardDTO);
        log.info((boardDTO.toString()));

        //게시글의 고유 번호를 포함한 맵 객체를 생성하여 반환
        return Map.of("of", no);
    }

    // 이미지 저장
    @Value("${file.upload.path}/bImg")
    private String uploadDir;

    @PostMapping("/upload/image")
    public ResponseEntity<String> uploadImg(@RequestParam("file") MultipartFile file) {
        log.info("여기왔니? 이미지 ");
        if(file.isEmpty()){
            return new ResponseEntity<>("File is empty", HttpStatus.BAD_REQUEST);
        }
        try {
            // 고유한 파일 이름 생성
            String uniqueFileName = UUID.randomUUID().toString() + "_" + file.getOriginalFilename();
            Path path = Paths.get(uploadDir + File.separator + uniqueFileName);
            Files.copy(file.getInputStream(), path);

            log.info("uuid :" + uniqueFileName);
            log.info("path :" + path);

            // 이미지 URL 반환
            String fileUrl = "/uploads/bImg/" + uniqueFileName;
            log.info("fileUrl : " + fileUrl);
            return new ResponseEntity<>(fileUrl, HttpStatus.OK);
        } catch (IOException e) {
            e.printStackTrace();
            return new ResponseEntity<>("Image upload failed", HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    // 글 수정
    @GetMapping("/board/modify/{cate}/{no}")
    public ResponseEntity<BoardDTO> boardModify(@PathVariable(name ="cate") String cate, @PathVariable(name ="no") int no){
        BoardDTO boardDTO = boardService.modify(cate, no);
        log.info("글수정cate : " + cate);
        log.info("글수정no : " + no);

        return ResponseEntity.ok().body(boardDTO);
    }

    @PostMapping("/board/modify/{cate}/{no}")
    public ResponseEntity<BoardDTO> modifyBoard(
            @PathVariable("cate") String cate,
            @PathVariable("no") int no,
            @RequestBody BoardDTO boardDTO) {

        BoardDTO updatedBoard = boardService.modify(cate, no, boardDTO);
        return ResponseEntity.ok(updatedBoard);
    }


    @Transactional
    @PostMapping("/board/delete/{cate}/{no}")
    public ResponseEntity<String> deleteBoard(@PathVariable String cate, @PathVariable int no) {
        log.info("여기왔니?");
        boardService.deleteBoard(cate, no);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body("게시글이 삭제되었습니다.");
    }


    @PostMapping("/board/report")
    public String  reportBoard(@RequestBody Map<String, Object> report) {
        int no =  Integer.parseInt(String.valueOf(report.get("no")));
        String uid = (String) report.get("uid");
        String reason = (String) report.get("reason");

        log.info("no : " + no);
        log.info("uid : " + uid);
        log.info("reason : " + reason);

        boardService.reportBoard(no, uid, reason);
        return "성공";
    }

    // 공지사항 최신글 5개
    @GetMapping("/board/notice")
    public List<Board> getNotices(){
        log.info("메인 공지사항");
        return boardService.getLatestNotices();
    }


}
