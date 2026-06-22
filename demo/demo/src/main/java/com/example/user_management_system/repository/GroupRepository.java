package com.example.user_management_system.repository;

import com.example.user_management_system.entity.Group;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface GroupRepository extends JpaRepository<Group, Long>, JpaSpecificationExecutor<Group> {

}
