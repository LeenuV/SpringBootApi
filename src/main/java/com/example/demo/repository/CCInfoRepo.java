package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.CC;

@Repository
public interface CCInfoRepo extends JpaRepository<CC,Integer>{

}
