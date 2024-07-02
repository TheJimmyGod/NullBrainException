package com.lec.spring.service;

import com.lec.spring.domain.shop.Contact;
import com.lec.spring.domain.User;
import com.lec.spring.repository.ContactRepo;
import com.lec.spring.repository.UserRepo;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ContactServiceImpl implements ContactService {

    private UserRepo userRepo;
    private ContactRepo contactRepo;



    @Autowired
    public ContactServiceImpl(SqlSession sqlSession){
        contactRepo = sqlSession.getMapper(ContactRepo.class);
        userRepo = sqlSession.getMapper(UserRepo.class);
    }




    // 문의사항 추가
    @Override
    public int addContact(Contact contact) {
        return contactRepo.insert(contact);
    }

    // 문의사항 수정
    @Override
    public int updateContact(Contact contact) {
        return contactRepo.update(contact);
    }

    // 문의사항 삭제
    @Override
    public int deleteContact(int id) {
        return contactRepo.delete(id);
    }

    // 문의사항 답변 업데이트
    @Override
    public int updateanswer(Contact contact) {
        return contactRepo.answerupdate(contact);
    }

    // 문의 글 id로 찾기
    @Override
    public Contact getContactById(int id) {
        return contactRepo.showById(id);
    }

    // 유저의 id 값으로 찾기
    @Override
    public List<Contact> getContactsByUserId(int userId) {
        return contactRepo.showMyContact(userId);
    }

    // 모든 문의글 리스트
    @Override
    public List<Contact> allContacts() {
        List<Contact> contacts = contactRepo.allContacts();
        for (Contact contact : contacts){
            User user = userRepo.selectById(contact.getUser_id());
            contact.setUser(user);
        }
        return contacts;
    }

    // 유저 이름으로 문의글 찾기
    @Override
    public List<Contact> findContactsByUsername(String username) {
        List<User> users = userRepo.allUser(username);
        List<Contact> contacts = new ArrayList<>();
        for(User user : users){
            List<Contact> userContacts = contactRepo.showMyContact(user.getId());
            for(Contact contact : userContacts) {
                contact.setUser(user);
                contacts.add(contact);
            }
        }
        return contacts;
    }

    // 전체 문의사항 카운트
    @Override
    public Long countAll() {
        return contactRepo.countAllContacts();
    }

    // 미답변 문의사항 카운트
    @Override
    public Long countUnAnswer() {
        return contactRepo.countUnanswered();
    }

    // 유저의 문의 유형 확인
    @Override
    public String type() {
        return contactRepo.type();
    }



    // 상태별 문의사항 리스트 조회 (페이징 포함)
    @Override
    public List<Contact> findContactsByStatus(String status, int offset, int limit) {
        List<Contact> contacts = contactRepo.findContactsByStatus(status, offset, limit);
        for (Contact contact : contacts) {
            User user = userRepo.selectById(contact.getUser_id());
            contact.setUser(user);
        }
        return contacts;
    }

    // 상태별 문의사항 카운트
    @Override
    public Long countContactsByStatus(String status) {
        return contactRepo.countContactsByStatus(status);
    }

    @Override
    public String status() {
        return null;
    }

    // 취소문의 카운트
    @Override
    public Long cancelOrder() {
        return contactRepo.cancel();
    }

    // 타입별 문의사항 리스트 조회 (페이징 포함)
    @Override
    public List<Contact> findContactsByType(String type, int offset, int limit) {
        List<Contact> contacts = contactRepo.findContactsByType(type, offset, limit);
        for (Contact contact : contacts) {
            User user = userRepo.selectById(contact.getUser_id());
            contact.setUser(user);
        }
        return contacts;
    }

    // 타입별 문의사항 카운트
    @Override
    public Long countContactsByType(String type) {
        return contactRepo.countContactsByType(type);
    }

    // 상태와 타입별 문의사항 리스트 조회 (페이징 포함)
    @Override
    public List<Contact> findContactsByStatusAndType(String status, String type, int offset, int limit) {
        List<Contact> contacts = contactRepo.findContactsByStatusAndType(status, type, offset, limit);
        for (Contact contact : contacts) {
            User user = userRepo.selectById(contact.getUser_id());
            contact.setUser(user);
        }
        return contacts;
    }

    // 상태와 타입별 문의사항 카운트
    @Override
    public Long countContactsByStatusAndType(String status, String type) {
        return contactRepo.countContactsByStatusAndType(status, type);
    }

    @Override
    public Long countProductInquiries() {
        return contactRepo.countProductInquiries();
    }

    @Override
    public Long countProductAndType() {
        return contactRepo.countProductAndType();
    }


}
