package com.lec.spring.controller;

import com.lec.spring.domain.RegisterValidator;
import com.lec.spring.domain.shop.Contact;
import com.lec.spring.domain.shop.ContactImage;
import com.lec.spring.dto.UserDto;
import com.lec.spring.service.ContactImageService;
import com.lec.spring.service.ContactService;
import com.lec.spring.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

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

    @Autowired
    private RegisterValidator registerValidator;

    @RequestMapping("/enterAddress")
    private String enterAddress(){
        return "/enterAddress";
    }

    @RequestMapping("/login")
    public void login(){}

    @GetMapping("/register")
    public void register(){


    }

    @PostMapping("/register")
    public String registerOk(@Valid @ModelAttribute UserDto userDto, BindingResult result,
                             Model model, RedirectAttributes redirectAttributes) {
        registerValidator.validate(userDto, result);
        if(result.hasErrors()){
            redirectAttributes.addFlashAttribute("username", userDto.getUsername());
            redirectAttributes.addFlashAttribute("name", userDto.getName());
            redirectAttributes.addFlashAttribute("phone", userDto.getPhone());
            redirectAttributes.addFlashAttribute("birth", userDto.getBirth());
            redirectAttributes.addFlashAttribute("email", userDto.getEmail());
            redirectAttributes.addFlashAttribute("gender", userDto.getGender());
            redirectAttributes.addFlashAttribute("street_addr", userDto.getStreet_addr());
            redirectAttributes.addFlashAttribute("detail_addr", userDto.getDetail_addr());
            redirectAttributes.addFlashAttribute("addressname", userDto.getAddressName());
            List<FieldError> errList = result.getFieldErrors();
            for(FieldError err : errList) {
                redirectAttributes.addFlashAttribute("error_"+err.getField(), err.getCode());
                break;
            }
            return "redirect:/user/register";
        }
        int cnt = userService.register(userDto);
        String page = "/user/registerOk";
        model.addAttribute("result", cnt);
        return page;
    }

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
                .goods_no(1)
                .title(title)
//                .type(type)
                .content(content)
                .build();

        contactService.addContact(contact);

        saveFile(contact.getId(), file1);
        saveFile(contact.getId(), file2);

        return "redirect:/user/contact";
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

//    @PostMapping("/register")
//    public String registerOk(@Valid @ModelAttribute UserDto userDto, Model model){
//        int cnt = userService.register(userDto);
//
//        String page = "/user/registerOk";
//
//        model.addAttribute("result", cnt);
//        return page;
//    }

    @PostMapping("/loginError")
    public String loginError(){
        return "user/login";
    }

    @RequestMapping("/rejectAuth")
    public String rejectAuth() {
        return "user/rejectAuth";
    }

    @RequestMapping("/test")
    public void userTest(){

    }

    @RequestMapping("/changePassword")
    public void changePassword(){

    }
}
