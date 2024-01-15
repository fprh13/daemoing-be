package com.daemoing.daemo.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "category")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Category {

    @Id
    @GeneratedValue
    @Column(name = "category_id")
    private Long id;

    private String parentCategory;

    private String childCategory;

    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL)
    private List<Club> clubs = new ArrayList<>();


    public Category(String parentCategory, String childCategory) {
        this.parentCategory = parentCategory;
        this.childCategory = childCategory;
    }

    //==업데이트==//
    public void update(String changeParentCategory, String changeChildCategory) {
        this.parentCategory = changeParentCategory;
        this.childCategory = changeChildCategory;
    }


}
