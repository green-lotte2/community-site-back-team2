package kr.communityserver.service;

import kr.communityserver.dto.*;
import kr.communityserver.entity.*;
import kr.communityserver.repository.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Log4j2
@RequiredArgsConstructor
@Service
public class ProjectService {

    private final ProjectItemRepository projectItemRepository;
    private final ProjectRepository projectRepository;
    private final ProjectBoardRepository projectBoardRepository;
    private final ProjectUserRepository projectUserRepository;
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;


    //프로젝트 아이템 불러오기
    public void selectItem(String member){

    }

    //프로젝트 리스트 불러오기
    public PageResponseDTO<ProjectDTO> selectProject(String userId, PageRequestDTO pageRequestDTO) {

        log.info("selectProject 시작");

        Pageable pageable = pageRequestDTO.getPageable("projectNo");

        return projectUserRepository.selectUserProject(userId, pageRequestDTO, pageable);
    }

    //프로젝트 보드 불러오기
    public List<ProjectBoard> selectProjectBoard(int projectNo) {
        log.info("projectDTOList 출력2!!!! : projectNo : " + projectNo);

        List<ProjectBoard> projectBoards = projectBoardRepository.findByProjectNo(projectNo);

        log.info("projectBoardDTO List : 이것좀 보소 : " + projectBoards);

        return projectBoards;
    }

    //프로젝트 저장
    public ResponseEntity<Project> addProject(ProjectDTO projectDTO){

        Project project = new Project();
        project.setProjectTitle(projectDTO.getProjectTitle());
        project.setProjectInfo(projectDTO.getProjectInfo());
        project.setUserId(projectDTO.getUserId());
        project.setProjectStatus(projectDTO.getProjectStatus());

        Project addProject = projectRepository.save(project);

        log.info("프로젝트 저장 : " +addProject);
        projectDTO.setProjectNo(addProject.getProjectNo());
        addProjectUser(projectDTO);

        log.info("프로젝트유저추가 넘김");

        return ResponseEntity.ok().body(addProject);

    }

    
    //프로젝트 유저 추가
    public ResponseEntity addProjectUser(ProjectDTO projectDTO){

        log.info("addProjectUser 시작");

        ProjectUser projectUser = new ProjectUser();
        projectUser.setUserId(projectDTO.getUserId());
        projectUser.setProjectNo(projectDTO.getProjectNo());
        projectUser.setInvitationStatus("yes");

        log.info("projectUser : " +projectUser);

        ProjectUser addProjectUser = projectUserRepository.save(projectUser);

        log.info("projectUser 등록완료 : " +projectUser);

        return ResponseEntity.ok().body(addProjectUser);
    }

    //프로젝트 보드 저장
    public void insertBoard(ProjectBoardDTO projectBoardDTO){

        log.info("프로젝트 보드 서비스 대머리깎아라 : " +projectBoardDTO );

        ProjectBoard projectBoard = new ProjectBoard();
        projectBoard.setProjectNo(projectBoardDTO.getProjectNo());
        projectBoard.setBoardName(projectBoardDTO.getBoardName());
        projectBoard.setCreateUserId(projectBoardDTO.getCreateUserId());
        projectBoard.setBoardPosition(projectBoard.getBoardPosition());

        ProjectBoard insertProjectBoard = projectBoardRepository.save(projectBoard);
        log.info("프로젝트 보드 저장 : " +insertProjectBoard );

    }

    //프로젝트 아이템 제목저장
    public void insertTitle(ProjectItemDTO projectItemDTO){

        ProjectItem projectItem =new ProjectItem();
        projectItem.setTitle1(projectItemDTO.getTitle1());
        projectItem.setMember(projectItemDTO.getMember());
        projectItem.setStatus(projectItemDTO.getStatus());

        ProjectItem titleProject = projectItemRepository.save(projectItem);

        log.info("제목저장 : " +titleProject );

    }


    //프로젝트 멤버초대
    public ResponseEntity inviteUser(String userEmail, int projectNo){
        log.info("projectNo : " +projectNo);
        User user = userRepository.findByEmail(userEmail);

        log.info("userEmail : " +userEmail);

        Map<String, Integer> map = new HashMap<>();
        log.info("이거다"+projectUserRepository.findProjectUserByProjectNoAndUserId(projectNo,user.getUid()));
        if(user == null){
            map.put("result", 0);
        }else if(projectUserRepository.findProjectUserByProjectNoAndUserId(projectNo,user.getUid()) != null){
            map.put("result", -1);

        }else{
            log.info("projectNo1 : " +projectNo);
            ProjectUser projectUser = new ProjectUser();
            projectUser.setUserId(user.getUid());
            projectUser.setProjectNo(projectNo);
            projectUser.setInvitationStatus("no");

            log.info("projectNo2 : " +projectNo);
            projectUserRepository.save(projectUser);

            log.info("projectNo3 : " +projectNo);
            map.put("result", 1);
        }
        return  ResponseEntity.ok().body(map);

    }


    /*
    //프로젝트 리스트 불러오기
    public PageResponseDTO<ProjectDTO> selectProject(PageRequestDTO pageRequestDTO){

        log.info("pageRequestDTO １ ： " +pageRequestDTO);
        Pageable pageable = PageRequest.of(
                pageRequestDTO.getPg() -1,
                pageRequestDTO.getSize(),
                Sort.by("projectNo").descending());
        Page<Project> pageProject = null;
        pageProject = projectRepository.findAll(pageable);

        List<ProjectDTO> dtoList = pageProject.getContent().stream()
                .map(entity -> {
                    ProjectDTO dto = modelMapper.map(entity, ProjectDTO.class);
                    log.info("pageRequestDTO４： " +pageRequestDTO);
                    return dto;
                })
                .toList();

        int total = (int) pageProject.getTotalElements();

        PageResponseDTO<ProjectDTO> responseDTO = PageResponseDTO.<ProjectDTO>builder()
                .dtoList(dtoList)
                .pageRequestDTO(pageRequestDTO)
                .total(total)
                .build();
        log.info("pageRequestDTO５： " +pageRequestDTO);

        return responseDTO;
    }
*/




}
