package com.gok.kafkaexample.repository;

import com.gok.kafkaexample.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



@Repository
public abstract class OutboxTableRepository implements JpaRepository<User, Integer> {

}
