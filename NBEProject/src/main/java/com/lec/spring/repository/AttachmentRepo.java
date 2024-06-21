package com.lec.spring.repository;

import com.lec.spring.domain.Attachment;

import java.util.List;
import java.util.Map;

public interface AttachmentRepo {
    int insert(List<Map<String,Object>> list, Integer postId);
    int save(Attachment file);
    List<Attachment> findByPost(Integer postId);
    Attachment findById(Integer id);
    List<Attachment> findByIds(Integer[] ids);
    int deleteByIds(Integer[] ids);
    int delete(Attachment file);
}
