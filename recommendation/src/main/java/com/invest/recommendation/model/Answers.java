package com.invest.recommendation.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "answers")
public class Answers {
    @Id
    @Column(name = "id")
    public Long id;

    @Column(name = "phone_number")
    public String phoneNumber;

    @Column(name = "answer1")
    public String answer1;

    @Column(name = "answer2")
    public String answer2;

    @Column(name = "answer3")
    public String answer3;

    @Column(name = "answer4")
    public String answer4;

    @Column(name = "answer5")
    public String answer5;

    @Column(name = "answer6")
    public String answer6;

    public Long getId(){
        return id;
    }

    public String getAnswer1(){
        return answer1;
    }

    public String getAnswer2(){
        return answer2;
    }

    public String getAnswer3(){
        return answer3;
    }

    public String getAnswer4(){
        return answer4;
    }

    public String getAnswer5(){
        return answer5;
    }

    public String getAnswer6(){
        return answer6;
    }

    public String getPhoneNumber(){
        return phoneNumber;
    }
}
