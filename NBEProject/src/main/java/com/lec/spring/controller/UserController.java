package com.lec.spring.controller;

import com.lec.spring.domain.shop.Contact;
import com.lec.spring.domain.shop.ContactImage;
import com.lec.spring.service.ContactImageService;
import com.lec.spring.service.ContactService;
import com.lec.spring.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/user")
public class UserController {

    @Value("${app.upload.path}")
    private String uploadPath;

    @Autowired
    private UserService userService;

    @Autowired
    private ContactService contactService;

    @Autowired
    private ContactImageService contactImageService;

    @RequestMapping("/login")
    public void login(){}

    @GetMapping("/register")
    public void register(){}

    @RequestMapping("/contact")
    public String contact(Model model){
       List<Contact> contact = contactService.allContacts();

       model.addAttribute("contact", contact);

        return "/user/contact";
    }

    @RequestMapping("/contactOk")
    public String contactOk(@RequestParam("title") String title,
                            @RequestParam("type") String type,
                            @RequestParam("content") String content,
                            @RequestParam("file1") MultipartFile file1,
                            @RequestParam("file2") MultipartFile file2
                           ) throws IOException {

        Contact contact = Contact.builder()
                .user_id(2)
                .goods_id(1)
                .title(title)
//                .type(type)
                .content(content)
                .build();

        contactService.addContact(contact);

        return "redirect:/user/contact";
    }

    private void saveFile(int contactId, MultipartFile file) {
        if (!file.isEmpty()) {
            try {
                String originalFilename = file.getOriginalFilename();
                String ext = originalFilename.substring(originalFilename.lastIndexOf('.'));
                String uuid = UUID.randomUUID().toString();
                String savedFilename = uuid + ext;

                Path uploadDir = Paths.get(uploadPath);
                if (!uploadDir.toFile().exists()) {
                    uploadDir.toFile().mkdirs();
                }

                File targetFile = new File(uploadPath, savedFilename);
                file.transferTo(targetFile);

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



//    @PostMapping("/register")  //아직 검증로직 x 두번째 레지스터부터는 오류 존재함
//    public String registerOk(@Valid @ModelAttribute UserDto userDto, Model model){
//        int cnt = userService.register(userDto);
//
//        String page = "/user/registerOk";
//
//        model.addAttribute("result", cnt);
//        return page;
//    }

}
