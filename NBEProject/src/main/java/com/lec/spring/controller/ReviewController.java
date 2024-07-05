package com.lec.spring.controller;


import com.lec.spring.domain.ReviewGoods;
import com.lec.spring.domain.User;
import com.lec.spring.domain.shop.Review;
import com.lec.spring.domain.shop.ReviewImage;
import com.lec.spring.service.RequestService;
import com.lec.spring.service.ReviewImageService;
import com.lec.spring.service.ReviewService;
import com.lec.spring.service.UserService;
import com.lec.spring.util.U;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Controller
public class ReviewController {

    @Value("${app.upload.path}")
    private String uploadPath;

    @Autowired
    private ReviewService reviewService;

    @Autowired
    private RequestService requestService;

    @Autowired
    private UserService userService;

    @Autowired
    private ReviewImageService reviewImageService;

    @RequestMapping("/review")
    public String review(@RequestParam("id") Integer id, Model model){
        ReviewGoods purchase = reviewService.getPurchaseInfo(id);

        model.addAttribute("purchase", purchase);
        System.out.println("purchase는 뭐가 들어오지?: " + purchase);
        return "review";
    }

    @RequestMapping("/reviewOk")
    public String contactOk( String title,
                             String goodsId,
                             Integer rate,
                             String content,
                             MultipartFile file1,
                             MultipartFile file2,
                             Integer user_id
    ) throws IOException {

        User user = U.getLoggedUser();
        user = userService.findById(user.getId());
//        // 예시로 유저 ID를 1로 가정
//        Integer userId = 1;
//
//        // User 객체 생성
//        User user = User.builder()
//                .id(userId)
//                .build();

        Review review = Review.builder()
//                .user_id(1)
                .goodsId("65")
                .title(title)
//                .type(type)
                .user_id(user.getId())
                .content(content)
                .goodsId(goodsId)
                .rate(rate)
                .build();

        reviewService.writeReview(review);

        saveFile(review.getId(), file1);
        saveFile(review.getId(), file2);
//        System.out.println(title);

        return "redirect:/request?userId=" + user.getId();
    }

    private void saveFile(int reviewId, MultipartFile file) {
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

                ReviewImage reviewImage = ReviewImage.builder()
                        .review_id(reviewId)
                        .file_name(savedFilename)
                        .build();

                reviewImageService.addImage(reviewImage);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }



}

