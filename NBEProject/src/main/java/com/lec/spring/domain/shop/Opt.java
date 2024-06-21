package com.lec.spring.domain.shop;

import lombok.Data;

import java.util.List;
@Data
public class Opt {
    List<String> values;
    String option_price;
    String stock_quantity;
}
