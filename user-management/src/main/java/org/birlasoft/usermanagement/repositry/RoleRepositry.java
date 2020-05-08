package org.birlasoft.usermanagement.repositry;

import java.util.List;

import org.birlasoft.usermanagement.bean.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface RoleRepositry extends JpaRepository<Role, Integer> ,CommonRepositry<Role, Integer>{

	List<Role> findByRoleNameAndDeleteStatusFalse(String roleName);

}
