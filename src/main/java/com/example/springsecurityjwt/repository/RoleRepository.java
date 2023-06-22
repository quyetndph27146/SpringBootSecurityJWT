package com.example.springsecurityjwt.repository;

import com.example.springsecurityjwt.entity.ERole;
import com.example.springsecurityjwt.entity.Roles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Roles, Integer> {

    Optional<Roles> findByRoleName(ERole roleName);

    @Query("Select r from Roles r where r.roleId in :ids")
    List<Roles> findByRoleIds(List<Integer> ids);
}
