package com.daemoing.daemo.repository.custom;

import com.daemoing.daemo.dto.CategoryDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface CategoryRepositoryCustom {

    Page<CategoryDto.ClubListDto> searchPage(CategoryDto.ReadReqDto readReqDto, Pageable pageable);
}
