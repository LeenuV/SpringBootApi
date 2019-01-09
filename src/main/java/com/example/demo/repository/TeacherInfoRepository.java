package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.TeacherInformation;

@Repository
public interface TeacherInfoRepository extends JpaRepository<TeacherInformation,Integer>{

}
