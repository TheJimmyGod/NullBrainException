package com.lec.spring.service;

import com.lec.spring.domain.shop.Address;
import com.lec.spring.domain.shop.Profile;
import com.lec.spring.domain.User;
import com.lec.spring.repository.AddressRepo;
import com.lec.spring.repository.UserRepo;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Service
public class MyServiceImpl implements MyService {
    @Value("${app.upload.path}")
    private String uploadDir;
    private UserRepo userRepository;
    private AddressRepo addressRepository;


    @Autowired
    public MyServiceImpl(SqlSession sqlSession) {
        userRepository = sqlSession.getMapper(UserRepo.class);
        addressRepository = sqlSession.getMapper(AddressRepo.class);
        System.out.println("MyService() 생성");
    }

    @Override
    public void showMyPage(Model model) {
        User user = userRepository.selectById(2);
        model.addAttribute("user", user);
    }

    @Override
    public void updateMyPage(Model model, Integer user_id) {
        User user = userRepository.selectById(user_id);
        var addresses = addressRepository.selectAll(user_id);
        model.addAttribute("user", user);
        model.addAttribute("addresses", addresses);
    }

    @Override
    public int updateProfile(Integer user_id, Profile profile) {
        User user = userRepository.selectById(user_id);
        if(user == null)
            return -1;
        for (Address address : profile.getAddresses()) {
            Address exist = addressRepository.selectById(address.getId());
            if(exist != null)
            {
                addressRepository.update(address);
                continue;
            }

            if(address.getIsDefault())
            {
                for(var item : addressRepository.selectAll(user_id))
                {
                    if(item.getIsDefault())
                    {
                        item.setIsDefault(false);
                        addressRepository.update(item);
                    }
                }
            }

            Address newAddress = Address.builder().
                    name(address.getName())
                    .street_addr(address.getStreet_addr())
                    .detail_addr(address.getDetail_addr())
                    .user_id(user_id)
                    .isDefault(address.getIsDefault())
                    .build();

            addressRepository.insert(newAddress);
        }
        System.out.println(profile.getNickName());
        user.setName(profile.getNickName());
        user.setPhone(profile.getPhone());

        if(profile.getProfileImage() != null)
        {
            if(IsImage(profile.getProfileImage().getOriginalFilename()))
            {
                String fileName = imageInput(profile.getProfileImage(), user_id);
                user.setProfileimage((fileName == null || fileName.isEmpty()) ?
                        user.getProfileimage() : fileName);
            }
        }
        else
            user.setProfileimage((user.getProfileimage() != null) ? user.getProfileimage() : null);
        return userRepository.update(user);
    }

    private boolean IsImage(String imageDir){
        String realPath = new File(uploadDir).getAbsolutePath();
        BufferedImage imgData = null;
        File f = new File(realPath, imageDir);
        try{
            imgData = ImageIO.read(f);
            if(imgData == null)
                throw new NullPointerException();
            else
                return true;
        }catch (IOException e){
            System.out.println("파일 존재 안함: " + f.getAbsolutePath() + "["+e.getMessage()+"]");
        }catch (NullPointerException e){
            System.out.println("해당 파일은 이미지가 아님: " + f.getAbsolutePath() + "["+e.getMessage()+"]");
        }
        return false;
    }

    private String imageInput(MultipartFile file, Integer id){
        User user = userRepository.selectById(id);
        if(user == null || file == null)
            return null;
        String fileName = upload(file);
        if(fileName != null && !fileName.isEmpty()){
            return fileName;
        }
        return null;
    }

    private String upload(MultipartFile multipartFile){
        String originalFileName = multipartFile.getOriginalFilename();
        if(originalFileName == null || originalFileName.isEmpty()) return null;
        String sourceName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
        String fileName = sourceName;
        File file = new File(uploadDir, fileName);
        int pos = 0;
        if(file.exists()){
            pos = fileName.lastIndexOf(".");
            if(pos > -1){
                String name = fileName.substring(0, pos);
                String ext = fileName.substring(pos + 1);
                fileName = name + "_profile_" + System.currentTimeMillis() + "." + ext;
            }
            else
            {
                fileName = "_" + System.currentTimeMillis();
            }
        }
        else
        {
            pos = fileName.lastIndexOf(".");
            if(pos > -1){
                String name = fileName.substring(0, pos);
                String ext = fileName.substring(pos + 1);
                fileName = name + "_profile_" + "." + ext;
            }
        }
        Path copyOfLocation = Paths.get(new File(uploadDir, fileName).getAbsolutePath());
        try{
            Files.copy(multipartFile.getInputStream(),
                    copyOfLocation,
                    StandardCopyOption.REPLACE_EXISTING);
        }
        catch (IOException e){
            throw new RuntimeException(e);
        }
        return fileName;
    }
}
