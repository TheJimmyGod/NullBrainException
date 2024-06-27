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

    @Override
    public List<Contact> findContactsByUsername(String username) {
        List<User> users = (List<User>) userRepo.selectByUsername(username);
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


    @Override
    public Long countAll() {
        return contactRepo.countAllContacts();
    }

    @Override
    public Long countUnAnswer() {
        return contactRepo.countUnanswered();
    }

    @Override
    public String type() {
        return contactRepo.type();
    }

    @Override
    public List<Contact> findAllContacts(int offset, int limit) {
        List<Contact> contacts = contactRepo.findAllContacts(offset, limit);
        for (Contact contact : contacts){
            User user = userRepo.selectById(contact.getUser_id());
            contact.setUser(user);
        }
        return contacts;
    }


    @Override
    public List<Contact> findContactsByStatus(String status, int offset, int limit) {
        List<Contact> contacts = contactRepo.findContactsByStatus(status, offset, limit);
        for (Contact contact : contacts) {
            User user = userRepo.selectById(contact.getUser_id());
            contact.setUser(user);
        }
        return contacts;
    }

    @Override
    public Long countContactsByStatus(String status) {
        return contactRepo.countContactsByStatus(status);
    }

    @Override
    public Long cancelOrder() {
        return contactRepo.cancel();
    }
}
