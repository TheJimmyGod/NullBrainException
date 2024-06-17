package com.lec.spring.service;

import com.lec.spring.domain.QryCommentList;
import com.lec.spring.domain.QryResult;
import com.lec.spring.domain.shop.Comment;
import com.lec.spring.domain.shop.User;
import com.lec.spring.repository.CommentRepo;
import com.lec.spring.repository.UserRepo;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {
    private CommentRepo commentRepository;
    private UserRepo userRepository;

    public CommentServiceImpl(SqlSession sqlSession){
        commentRepository = sqlSession.getMapper(CommentRepo.class);
        userRepository = sqlSession.getMapper(UserRepo.class);
    }

    @Override
    public QryCommentList list(Integer postId) {
        QryCommentList list = new QryCommentList();
        List<Comment> comments = commentRepository.findByPost(postId);
        list.setCount(comments.size());
        list.setList(comments);
        list.setStatus("OK");
        return list;
    }

    @Override
    public QryResult write(Integer postId, Integer userId, String content) {
        //User user = userRepository.findById(userId);
        User user = null;

        Comment comment = Comment.builder()
                .user(user)
                .content(content)
                .post_id(postId)
                .build();

        int cnt = commentRepository.save(comment);

        QryResult result = QryResult.builder()
                .count(cnt)
                .status("OK")
                .build();

        return result;
    }

    @Override
    public QryResult delete(Integer id) {
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
