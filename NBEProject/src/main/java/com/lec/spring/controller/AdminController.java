package com.lec.spring.controller;

import com.lec.spring.domain.shop.Contact;
import com.lec.spring.domain.shop.ContactImage;
import com.lec.spring.domain.shop.Purchase;
import com.lec.spring.domain.User;
import com.lec.spring.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.net.MalformedURLException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private UserService userService;

    @Autowired
    private ContactService contactService;

    @Autowired
    private ContactImageService contactImageService;

    @Autowired
    private AuthorityService authorityService;

    @Autowired
    private PurchaseService purchaseService;


    private static final String UPLOAD_DIR = "uploads/";

    public AdminController(){
        System.out.println("AdminController() 생성");
    }

    // 관리자 메인 페이지
    @RequestMapping("/main")
    public String adminpage(Model model){
        System.out.println("main 페이지 들어옴");


        Long userCnt = userService.cntUser();
        Long countAll = contactService.countAll();
        Long countUnAnswer = contactService.countUnAnswer();

        // 우측 상단 유저의 닉네임 표시(임시)
        String username = "테스트용ID";

        model.addAttribute("userCnt", userCnt);
        model.addAttribute("cntcontact", countAll);
        model.addAttribute("username", username);
        model.addAttribute("cntunanswer", countUnAnswer);


        return "admin/main";
    }

    // 주문 관리 페이지
    @RequestMapping("/orderpage")
    public String orderPage(@RequestParam(value = "page", defaultValue = "1") int page,
                            @RequestParam(value = "username", required = false) String username
                            ,Model model){
       Long orderCnt = purchaseService.orderCnt();
       List<Purchase> orderList;

        int limit = 10;
        int offset = (page - 1) * limit;

        if (username != null && !username.isEmpty()) {
            orderList = purchaseService.orderUsername(username);
            orderCnt = (long) orderList.size();
        } else {
            orderList = purchaseService.pagination(offset,limit);
            orderCnt = userService.cntUser();
        }

        int totalPages = (int) Math.ceil((double) orderCnt / limit);


       model.addAttribute("orderCnt", orderCnt);
       model.addAttribute("totalPages", totalPages);
       model.addAttribute("currentPage", page);
       model.addAttribute("orderList", orderList);

       if(username != null && !username.isEmpty()){
           List<Purchase> usernameList = purchaseService.orderUsername(username);
           model.addAttribute("username", username);

       }
        return "admin/orderpage";
    }





    // 반품 관리 페이지
    @RequestMapping("/returnpage")
    public void returnPage(){


    }

    // 반품 상세내용 페이지
    @RequestMapping("/returnDetailPage")
    public String returnDetailPage(Model model){



        return "/admin/returnDetailPage";
    }



    // 회원 목록 페이지
    @RequestMapping("/userlist")
    public String userList(@RequestParam(value = "page", defaultValue = "1") int page,
                           @RequestParam(value = "username", required = false) String username,
                           @RequestParam(value = "updateSuccess", required = false) String updateSuccess,
                           Model model) {
        int limit = 10;
        int offset = (page - 1) * limit;
        List<User> users;
        Long userCnt = userService.cntUser();

        if (username != null && !username.isEmpty()) {
//            users = userService.findByUserName(username);
//            userCnt = (long) users.size();
        } else {
            users = userService.pagination(offset, limit);
            userCnt = userService.cntUser();
        }

        int totalPages = (int) Math.ceil((double) userCnt / limit);

//        model.addAttribute("users", users);
        model.addAttribute("userCnt", userCnt);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("updateSuccess", updateSuccess);

        return "admin/userlist";
    }


    // 유저 권한 업데이트
    @PostMapping("/updateStatus")
    public String updateStatus(@RequestParam("userId") int userId, @RequestParam("status") boolean status
                                ,RedirectAttributes redirectAttributes, Model model) {

//        userService.setStatus(userId, status);

        model.addAttribute("status", status);

        return "redirect:/admin/userlist";
    }

    // 회원 등급관리 페이지
    @RequestMapping("/usergrade")
    public String userGrade(@RequestParam(value = "page", defaultValue = "1") int page,
                            @RequestParam(value = "username", required = false) String username,
                            Model model) {

        int limit = 10;
        int offset = (page - 1) * limit;

        List<User> users = userService.pagination(offset, limit);
        Long userCnt = userService.cntUser();

        if (username != null && !username.isEmpty()) {
//            users = userService.findByUserName(username);
            userCnt = (long) users.size();
        } else {
            users = userService.pagination(offset, limit);
            userCnt = userService.cntUser();
        }


        int totalPages = (int) Math.ceil((double) userCnt / limit);
        model.addAttribute("users", users);
        model.addAttribute("userCnt", userCnt);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", totalPages);
        return "admin/usergrade";
    }





// 유저 등급 변경
    @PostMapping("/updateGrade")
    public String updateGrade(@RequestParam("userId") int userId, @RequestParam("grade") String grade, RedirectAttributes redirectAttributes) {

        userService.updateGrade(userId, grade);

        return "redirect:/admin/usergrade";
    }


    // 관리자 공지사항 페이지
    @RequestMapping("/notice")
    public String adminNotice(Model model){

        return "admin/notice";
    }

    // 관리자 공지사항 작성페이지
    @RequestMapping("/writenotice")
    public void writeNotice(){

    }

    // 관리자 공지사항 상세보기 페이지
    @RequestMapping("/detailnotice")
    public void detailNotice(){

    }


    // 문의사항 목록 페이지
    @RequestMapping("/inquirylist")
    public String inquiryList(
                            @RequestParam(value = "page" ,defaultValue= "1") int page,
                            @RequestParam(value = "username", required = false) String username,
                              Model model) {
        List<Contact> contacts;
        Long countAll = contactService.countAll();
//        String type = contactService.type();

        int limit = 10;
        int offset = (page - 1) * limit;


        if (username != null && !username.isEmpty()) {
            contacts = contactService.findContactsByUsername(username);
            countAll = (long) contacts.size();
        } else {
            contacts = contactService.allContacts();
            countAll = contactService.countAll();
        }

        int totalPages = (int) Math.ceil((double) countAll / limit);


//        model.addAttribute("contactType", type);
        model.addAttribute("contacts", contacts);
        model.addAttribute("cntcontact", countAll);
        model.addAttribute("username", username);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", totalPages);
        return "admin/inquirylist";
    }

    // 문의사항 상세보기 페이지
    @RequestMapping("/inquirydetail")
    public String inquiryDetail(@RequestParam("id") int id,
            Model model){
        
        // 문의사항 id를 가져온다
        Contact contact = contactService.getContactById(id);
        if (contact != null) {
            User user = userService.findById(contact.getUser_id());
            contact.setUser(user);
            List<ContactImage> images = contactImageService.getImagesByContactId(id);
            model.addAttribute("images", images);
        }
        model.addAttribute("contact", contact);
        return "/admin/inquirydetail";
    }

    // 답변 버튼 클릭 시
    @RequestMapping("/inquiryAnswerOk")
    public String inquiryAnswer(@RequestParam("id") int id, @RequestParam("answer") String answer, RedirectAttributes redirectAttributes){
        Contact contact = contactService.getContactById(id);
        if(contact != null){
            contact.setAnswer(answer);
            contact.setStatus("답변 완료");
            contactService.updateanswer(contact);
            redirectAttributes.addFlashAttribute("message", "답변이 저장되었습니다.");
        } else {
            redirectAttributes.addFlashAttribute("message", "문의사항을 찾을 수 없습니다.");
        }

        return "redirect:/admin/inquirylist";

    }


    // 문의사항 삭제 버튼 클릭 시
    @RequestMapping("/inquirydeleteOk")
    public String inquiryDelete(@RequestParam("id") int id, RedirectAttributes redirectAttributes){
        try{
            contactService.deleteContact(id);
            redirectAttributes.addFlashAttribute("message", "삭제되었습니다.");
        } catch (Exception e){
            redirectAttributes.addFlashAttribute("message", "삭제에 실패했습니다.");
        }

        return "redirect:/admin/inquirylist";

    }




    // 문의사항 이미지 제공 메서드
    @GetMapping("/inquirydetailimage/{filename:.+}")
    @ResponseBody
    public ResponseEntity<Resource> inquiryImage(@PathVariable String filename) {
        try {
            Path file = Paths.get(UPLOAD_DIR).resolve(filename);
            Resource resource = new UrlResource(file.toUri());

            if (resource.exists() || resource.isReadable()) {
                return ResponseEntity.ok()
                        .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"" + resource.getFilename() + "\"")
                        .body(resource);
            } else {
                throw new RuntimeException("Could not read file: " + filename);
            }
        } catch (MalformedURLException e) {
            throw new RuntimeException("Could not read file: " + filename, e);
        }
    }





    @RequestMapping("/test")
    public void test(){
        System.out.println("test 페이지 들어옴");
    }

}
