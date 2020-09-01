package com.challenge.repository;

import com.challenge.dbobjects.UserDB;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<UserDB, String> {

    public List<UserDB> findByVipTrue();

}
