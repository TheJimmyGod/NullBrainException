package com.lec.spring.controller;


import com.lec.spring.domain.ReviewGoods;
import com.lec.spring.domain.User;
import com.lec.spring.domain.shop.Purchase;
import com.lec.spring.domain.shop.Review;
import com.lec.spring.domain.shop.ReviewImage;
import com.lec.spring.service.*;
import com.lec.spring.util.U;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

@Controller
public class ReviewController {

    @Value("${app.upload.path}")
    private String uploadPath;

    @Autowired
    private ReviewService reviewService;

    @Autowired
    private UserService userService;

    @Autowired
    private ReviewImageService reviewImageService;

    @Autowired
    private PurchaseService purchaseService;

    @RequestMapping("/review")
    public String review(@RequestParam("id") Integer id, Model model){
        ReviewGoods purchase = reviewService.getPurchaseInfo(id);
        model.addAttribute("purchase", purchase);
        model.addAttribute("PurchaseId", id);
        System.out.println("purchase는 뭐가 들어오지?: " + purchase);
        return "review";
    }

    @RequestMapping("/reviewOk")
    public String contactOk( Integer id,
                             String title,
                             String goodsId,
                             Integer rate,
                             String content,
                             MultipartFile file1,
                             MultipartFile file2
    ) throws IOException {

        User user = U.getLoggedUser();
        user = userService.findById(user.getId());



        Review review = Review.builder()
                .goodsId(goodsId)
                .title(title)
                .user_id(user.getId())
                .content(content)
                .goodsId(goodsId)
                .rate(rate)
                .build();

        reviewService.writeReview(review);

        saveFile(review.getId(), file1);
        saveFile(review.getId(), file2);
        Purchase p = purchaseService.findById(id);
        p.setStatus("REVIEW");
        if(purchaseService.updateStatus(p) == 0) {
            System.out.println("리뷰 변경실패");
        }

        return "redirect:/request";
    }

    private void saveFile(int reviewId, MultipartFile file) {
        if (!file.isEmpty()) {

            String originalFilename = file.getOriginalFilename();
            String ext = originalFilename.substring(originalFilename.lastIndexOf('.'));
            String uuid = UUID.randomUUID().toString();
            String savedFilename = uuid + ext;

            // 상대 경로를 절대 경로로 변환
//                Path uploadDir = Paths.get(uploadPath).toAbsolutePath().normalize();
            Path uploadDir = Paths.get(new File(uploadPath, savedFilename).getAbsolutePath());
            System.out.println(uploadDir);

            try {
                Files.copy(file.getInputStream(),
                        uploadDir,
                        StandardCopyOption.REPLACE_EXISTING);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }


            ReviewImage reviewImage = ReviewImage.builder()
                    .review_id(reviewId)
                    .file_name(savedFilename)
                    .build();

            reviewImageService.addImage(reviewImage);

        }


    }
}

