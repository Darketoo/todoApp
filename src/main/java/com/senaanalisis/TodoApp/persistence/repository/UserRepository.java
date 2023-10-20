package com.senaanalisis.TodoApp.persistence.repository;

import com.senaanalisis.TodoApp.persistence.entity.UserEntity;
import org.springframework.data.repository.ListCrudRepository;

public interface UserRepository extends ListCrudRepository<UserEntity, Integer> {
}
