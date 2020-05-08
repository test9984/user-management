package org.birlasoft.usermanagement.repositry;

import java.util.List;

import org.birlasoft.usermanagement.bean.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface UserRepositry extends JpaRepository<User, Integer> ,CommonRepositry<User, Integer>{
	List<User> findAllByEmailAddressAndDeleteStatusFalse(String emailAddress);
	User findOneByEmailAddressAndDeleteStatusFalse(String emailAddress);
}
