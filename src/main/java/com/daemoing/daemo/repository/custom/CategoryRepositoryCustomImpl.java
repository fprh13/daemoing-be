package com.daemoing.daemo.repository.custom;

import com.daemoing.daemo.domain.Category;
import com.daemoing.daemo.domain.QClub;
import com.daemoing.daemo.dto.QCategoryDto_ClubListDto;
import com.daemoing.daemo.global.common.ErrorCode;
import com.daemoing.daemo.global.common.exception.CustomException;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.daemoing.daemo.domain.QCategory.*;
import static com.daemoing.daemo.domain.QClub.*;
import static com.daemoing.daemo.dto.CategoryDto.*;
import static org.springframework.util.StringUtils.hasText;

@Repository
public class CategoryRepositoryCustomImpl implements CategoryRepositoryCustom{

    private final JPAQueryFactory queryFactory;

    public CategoryRepositoryCustomImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }


    private BooleanExpression univEq(String univ) {
        return hasText(univ) ? club.univ.name.eq(univ) : null;
    }

    private BooleanExpression cityEq(String city) {
        return hasText(city) ? club.univ.city.eq(city) : null;
    }

    private BooleanExpression parentCategoryEq(String parentCategory) {
        return hasText(parentCategory) ? category.parentCategory.eq(parentCategory) : null;
    }

    private BooleanExpression childCategoryEq(String childCategory) {
        return hasText(childCategory) ? category.childCategory.eq(childCategory) : null;
    }

    private BooleanExpression isOnlineEq(Boolean isOnline) {
        return isOnline != null ? club.isOnline.eq(isOnline) : null;
    }

    private BooleanExpression nameCt(String keyword) {
        return hasText(keyword) ? club.name.contains(keyword) : null;
    }

    private OrderSpecifier<?> getOrderSpecifier(String order) {
        if (order != null) {
            switch (order) {
                case "mostView":
                    return club.viewCount.desc();
                case "recent":
                    return club.createDate.desc();
                case "popular":
                    return club.applicantCount.desc();
                default:
                    return club.createDate.desc();
            }
        } else {
            return club.createDate.desc();
        }
    }


    @Override
    public Page<ClubListDto> searchPage(ReadReqDto readReqDto, Pageable pageable) {
        List<ClubListDto> content = queryFactory
                .select(new QCategoryDto_ClubListDto(
                        club.name.as("clubName"),
                        club.viewCount.as("viewCount"),
                        club.activeDate.as("activeDate"),
                        club.isOnline.as("isOnline"),
                        club.applicantCount.as("applicantCount"),
                        club.participantCount.as("participantCount"),
                        club.participantMax.as("participantMax"),
                        club.univ.as("univ")
                ))
                .from(category)
                .leftJoin(category.clubs, club)
                .where(
                        univEq(readReqDto.getUniv()),
                        cityEq(readReqDto.getCity()),
                        parentCategoryEq(readReqDto.getParentCategory()),
                        childCategoryEq(readReqDto.getChildCategory()),
                        isOnlineEq(readReqDto.getIsOnline()),
                        nameCt(readReqDto.getKeyword()),
                        club.isNotNull()
                )
                .orderBy(getOrderSpecifier(readReqDto.getOrder()))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();// fetch -> content

        JPAQuery<Category> countQuery = queryFactory
                .select(category)
                .from(category)
                .leftJoin(category.clubs, club)
                .where(
                        parentCategoryEq(readReqDto.getParentCategory()),
                        childCategoryEq(readReqDto.getChildCategory()),
                        nameCt(readReqDto.getKeyword()),
                        club.isNotNull()
                );

//        return PageableExecutionUtils.getPage(content, pageable, () -> countQuery.fetchCount());
        return PageableExecutionUtils.getPage(content, pageable, countQuery::fetchCount);
    }
}
