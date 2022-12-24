package com.dbms.dto;

import java.util.List;

/*
 * @author ayushkumar
 * @created on 06/12/22
 */
public class AllCategoriesDTO {

    String category;

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public AllCategoriesDTO(String category) {
        this.category = category;
    }
}
