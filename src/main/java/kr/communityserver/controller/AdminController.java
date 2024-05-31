package kr.communityserver.controller;

import kr.communityserver.DTO.PageRequestDTO;
import kr.communityserver.service.AdminService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@Slf4j
@RequiredArgsConstructor
public class AdminController {
    private  final AdminService adminService;
    @PostMapping("/admin")
    @ResponseBody
    public ResponseEntity adminMain(@RequestBody  PageRequestDTO pageRequestDTO){
        log.info(pageRequestDTO.toString() +"먼데?");
       if(pageRequestDTO.getCate().equals("user")){
           return adminService.searchUsers(pageRequestDTO);
       }else if(pageRequestDTO.getCate().equals("article")){
            return adminService.searchArticles(pageRequestDTO);
       }else{
           //공지사항은 넘길까..?
           return null;
       }
    }

    @GetMapping("/admin/stopUser")
    @ResponseBody
    public  ResponseEntity adminStopUser(@RequestParam(name = "uid")String uid){
        return adminService.stopUser(uid);
    }

    @GetMapping("/admin/unStopUser")
    @ResponseBody
    public  ResponseEntity unStopUser(@RequestParam(name = "uid")String uid){
        return adminService.unStopUser(uid);
    }

    @GetMapping("/admin/stopArticle")
    @ResponseBody
    public  ResponseEntity stopArticle(@RequestParam(name = "no")int no){
        return adminService.stopArticle(no);
    }

    @GetMapping("/admin/unStopArticle")
    @ResponseBody
    public  ResponseEntity unStopArticle(@RequestParam(name = "no")int no){
        return adminService.unStopArticle(no);
    }

}