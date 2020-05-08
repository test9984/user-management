package org.birlasoft.usermanagement.repositry;

import org.birlasoft.usermanagement.bean.Permission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface PermissionRepositry extends JpaRepository<Permission, Integer> ,CommonRepositry<Permission, Integer>{

}
