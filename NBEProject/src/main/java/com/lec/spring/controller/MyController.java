package com.lec.spring.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lec.spring.domain.User;
import com.lec.spring.domain.shop.*;
import com.lec.spring.service.ContactImageService;
import com.lec.spring.service.ContactService;
import com.lec.spring.service.MyService;
import com.lec.spring.service.UserService;
import com.lec.spring.util.U;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/mypage")
public class MyController {
    @Autowired
    private MyService myService;

    @Autowired
    private UserService userService;

    @Autowired
    private ContactImageService contactImageService;

    @Autowired
    private ContactService contactService;


    @Value("${app.upload.path}")
    private String uploadPath;

    public MyController() {
        System.out.println("MyController() 생성");
    }

    @GetMapping("/detail")
    public void detail(Model model)
    {
        myService.showMyPage(model);
    }

    @GetMapping("/update")
    public void update(Model model){
        myService.updateMyPage(model);
    }

    @ResponseBody
    @PostMapping("/update")
    public String updateOk(
            @RequestParam(value = "file", required = false) MultipartFile file,
            @RequestParam("nickname") String nickname,
            @RequestParam("phone") String phone,
            @RequestParam("addresses") String addressesJson,
            Model model
    ){
        ObjectMapper objectMapper = new ObjectMapper();
        Address[] addresses;
        try {
            addresses = objectMapper.readValue(addressesJson, Address[].class); // JSON 문자열을 Address 배열로 변환
        } catch (JsonProcessingException e) {
            return "redirect:/mypage/update";
        }
        Profile profile = new Profile(addresses,nickname, phone, file);
        model.addAttribute("result", myService.updateProfile(profile));
        return "mypage/update";
    }

    @GetMapping("/createAddress")
    public void createAddress(Model model){
    }

    @GetMapping("/updateAddress")
    public void updateAddress(Model model){
    }

    @RequestMapping("/contact")
    public String contact(Model model){
        User user = U.getLoggedUser();
        user = userService.findById(user.getId());

        List<Contact> contact = contactService.allContacts();

        model.addAttribute("contact", contact);
        model.addAttribute("username", user.getUsername());

        return "mypage/contact";
    }

    @PostMapping("/contactOk")
    public String contactOk(
                            @RequestParam(value = "goods_no", required = false) Integer goods_no,
                            @RequestParam("title") String title,
                            @RequestParam("type") String type,
                            @RequestParam("content") String content,
                            @RequestParam("file1") MultipartFile file1,
                            @RequestParam("file2") MultipartFile file2
    ) throws IOException {

        User user = U.getLoggedUser();
        user = userService.findById(user.getId());
        int userId = user.getId();

        Contact contact = Contact.builder()
                .user_id(userId)
                .goods_no((goods_no == null) ? 100073 : goods_no)
                .title(title)
                .type(type)
                .content(content)
                .build();

        contactService.addContact(contact);

        saveFile(contact.getId(), file1);
        saveFile(contact.getId(), file2);

        return "mypage/contactOk";
    }

    private void saveFile(int contactId, MultipartFile file) {
        if (!file.isEmpty()) {
            try {
                String originalFilename = file.getOriginalFilename();
                String ext = originalFilename.substring(originalFilename.lastIndexOf('.'));
                String uuid = UUID.randomUUID().toString();
                String savedFilename = uuid + ext;

                // 상대 경로를 절대 경로로 변환
                Path uploadDir = Paths.get(uploadPath).toAbsolutePath().normalize();
                if (!Files.exists(uploadDir)) {
                    Files.createDirectories(uploadDir);
                    System.out.println("Created directories: " + uploadDir.toString());
                }

                Path targetPath = uploadDir.resolve(savedFilename).normalize();
                file.transferTo(targetPath.toFile());
                System.out.println("Saved file to: " + targetPath.toString());

                ContactImage contactImage = ContactImage.builder()
                        .contact_id(contactId)
                        .file_name(savedFilename)
                        .build();

                contactImageService.addImage(contactImage);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
