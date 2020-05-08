package org.birlasoft.usermanagement.repositry;

import org.birlasoft.usermanagement.bean.WorkSpace;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface WorkSpaceRepositry extends JpaRepository<WorkSpace, Integer> ,CommonRepositry<WorkSpace, Integer>{

}
