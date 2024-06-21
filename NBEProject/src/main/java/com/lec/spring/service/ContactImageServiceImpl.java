package com.lec.spring.service;

import com.lec.spring.domain.shop.ContactImage;
import com.lec.spring.repository.ContactImageRepo;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ContactImageServiceImpl implements ContactImageService {

    private ContactImageRepo contactImageRepo;

    @Autowired
    public ContactImageServiceImpl(SqlSession sqlSession) {
        contactImageRepo = sqlSession.getMapper(ContactImageRepo.class);
    }



    // 이미지 추가
    @Override
    public int addImage(ContactImage image) {
        return contactImageRepo.save(image);
    }


    // 여러 이미지 추가

    @Override
    public int addImages(List<Map<String, Object>> images, int contact_id) {
        return contactImageRepo.insert(images, contact_id);
    }

    @Override
    public ContactImage getImageById(int id) {
        return contactImageRepo.findById(id);
    }

    @Override
    public List<ContactImage> getImagesByContactId(int contactId) {
        return contactImageRepo.findByContact(contactId);
    }

    @Override
    public int deleteImageById(int id) {
        return contactImageRepo.deleteByIds(new int[]{id});
    }

    @Override
    public int deleteImagesByContactId(int contactId) {
        List<ContactImage> images = getImagesByContactId(contactId);
        int[] ids = images.stream().mapToInt(ContactImage::getId).toArray();
        return contactImageRepo.deleteByIds(ids);
    }
}
