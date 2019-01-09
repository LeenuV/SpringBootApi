package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.model.UserInformation;

@Repository
public interface UserInfoRepository extends JpaRepository<UserInformation,Integer>{
	
	@Query("Select u from UserInformation u where u.phone =:phone and u.role=:role")
	public UserInformation findByRoleAndPhone(@Param("phone") String phone,@Param("role") String role);
	
	@Query("Select count(u) from UserInformation u where u.id =:id and u.role =:role")
	public int getCountByIDAndRole(@Param("id") Integer id,@Param("role") String role);
	
	

}
