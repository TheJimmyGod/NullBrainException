package com.lec.spring.service;

import com.lec.spring.domain.Attachment;
import com.lec.spring.repository.AttachmentRepo;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AttachmentServiceImpl implements AttachmentService {

    private AttachmentRepo attachmentRepository;

    @Autowired
    public AttachmentServiceImpl(SqlSession sqlSession) {
        attachmentRepository = sqlSession.getMapper(AttachmentRepo.class);
    }

    @Override
    public Attachment findById(Integer id) {
        return attachmentRepository.findById(id);
    }
}
