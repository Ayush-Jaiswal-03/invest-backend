package com.invest.recommendation.repository;

import com.invest.recommendation.model.Answers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Repository
public interface AnswersRepository extends JpaRepository<Answers, Long> {
    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(value = "INSERT INTO answers(id, phone_number, answer1, answer2, answer3, answer4, answer5, answer6) VALUES(:id, :phone_number, :answer1, :answer2, :answer3, :answer4, :answer5, :answer6)", nativeQuery = true)
    int insertAnswersIntoDb(Long id, String phone_number, String answer1, String answer2, String answer3, String answer4, String answer5, String answer6);

    @Query(value = "SELECT count(*) FROM answers WHERE id = :id",nativeQuery = true)
    int checkExistInDb(Long id);

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(value = "UPDATE answers SET answer1 = :answer1, answer2 = :answer2, answer3 = :answer3, answer4 = :answer4, answer5 = :answer5, answer6 = :answer6 WHERE id = :id", nativeQuery = true)
    int updateAnswersIntoDb(Long id, String answer1, String answer2, String answer3, String answer4, String answer5, String answer6);

}
