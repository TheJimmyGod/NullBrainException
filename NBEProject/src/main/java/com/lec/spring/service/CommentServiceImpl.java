package com.lec.spring.service;

import com.lec.spring.domain.Comment;
import com.lec.spring.domain.QryCommentList;
import com.lec.spring.domain.QryResult;
import com.lec.spring.repository.CommentRepository;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {
    private CommentRepository commentRepository;
    //private UserRepository userRepository;

    public CommentServiceImpl(SqlSession sqlSession){
        commentRepository = sqlSession.getMapper(CommentRepository.class);
        //userRepository = sql.Session.getMapper(UserRepository.class);
    }

    @Override
    public QryCommentList list(Long postId) {
        QryCommentList list = new QryCommentList();
        List<Comment> comments = commentRepository.findByPost(postId);
        list.setCount(comments.size());
        list.setList(comments);
        list.setStatus("OK");
        return list;
    }

    @Override
    public QryResult write(Long postId, Long userId, String content) {
        return null;
    }

    @Override
    public QryResult delete(Long id) {
        int cnt = commentRepository.deleteById(id);
        String status = "FAIL";
        if(cnt > 0)
            status = "OK";

        QryResult result = QryResult.builder()
                .count(cnt)
                .status(status)
                .build();

        return result;
    }
}
