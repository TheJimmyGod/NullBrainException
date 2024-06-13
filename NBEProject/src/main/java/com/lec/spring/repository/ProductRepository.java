package com.lec.spring.repository;


import com.lec.spring.domain.Prod;

import java.util.List;

public interface ProductRepository {
    List<Prod> selectAll();
    Prod selectByTitle(String title);


}
