package com.daemoing.daemo.repository.init;

import com.daemoing.daemo.domain.Category;
import jakarta.annotation.PostConstruct;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class CategoryInitDb {
    private final InitService initService;


    @PostConstruct
    public void init() {
        initService.dbInit1();
    }


    @Component
    @Transactional
    @RequiredArgsConstructor
    static class InitService {

        private final EntityManager em;

        public void dbInit1() {
            saveCategory(new Category("운동", "축구"));
            saveCategory(new Category("운동", "농구"));
            saveCategory(new Category("운동", "배드민턴"));
            saveCategory(new Category("운동", "테니스"));
            saveCategory(new Category("운동", "등산"));
            saveCategory(new Category("운동", "수영"));
            saveCategory(new Category("운동", "러닝"));
            saveCategory(new Category("운동", "헬스"));

            saveCategory(new Category("음악", "밴드"));
            saveCategory(new Category("음악", "락"));
            saveCategory(new Category("음악", "팝음악"));
            saveCategory(new Category("음악", "힙합/랩"));

            saveCategory(new Category("자격증", "IT"));
            saveCategory(new Category("자격증", "금융"));
            saveCategory(new Category("자격증", "언어"));
            saveCategory(new Category("자격증", "토익/토플"));
            saveCategory(new Category("자격증", "한자"));
            saveCategory(new Category("자격증", "회계"));

            saveCategory(new Category("게임", "PC/콘솔"));
            saveCategory(new Category("게임", "보드"));
            saveCategory(new Category("게임", "e스포츠"));
            saveCategory(new Category("게임", "전략"));

            saveCategory(new Category("취미", "요리"));
            saveCategory(new Category("취미", "그림"));
            saveCategory(new Category("취미", "사진"));
            saveCategory(new Category("취미", "음악 감상"));
            saveCategory(new Category("취미", "독서"));
            saveCategory(new Category("취미", "여행"));

            saveCategory(new Category("전공", "전산학과"));
            saveCategory(new Category("전공", "경영학과"));
            saveCategory(new Category("전공", "의학"));
            saveCategory(new Category("전공", "법학"));
            saveCategory(new Category("전공", "화학/생물학과"));
            saveCategory(new Category("전공", "물리학과"));
            saveCategory(new Category("전공", "인문학과"));
            saveCategory(new Category("전공", "사회과학과"));

            saveCategory(new Category("기타", "책"));
            saveCategory(new Category("기타", "토론"));
            saveCategory(new Category("기타", "영화"));
            saveCategory(new Category("기타", "봉사활동"));
            saveCategory(new Category("기타", "프로그래밍"));
            saveCategory(new Category("기타", "엔터테인먼트"));
        }

        private void saveCategory(Category category) {
            em.persist(category);
        }

    }
}

