package com.daemoing.daemo.repository;

import com.daemoing.daemo.domain.Club;
import io.lettuce.core.dynamic.annotation.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;


public interface ClubRepository extends JpaRepository<Club, Long> {

    @Query("select c from Club c left join fetch c.userClubs uc where c.id = :id and uc.applicant = :applicant")
    Optional<Club> findClubAndUserClubByIdAndApplicant(@Param("id") Long id, @Param("applicant") String applicant);

}
