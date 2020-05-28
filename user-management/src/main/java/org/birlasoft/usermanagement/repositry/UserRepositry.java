package org.birlasoft.usermanagement.repositry;

import java.util.List;

import org.birlasoft.usermanagement.bean.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
@Repository
public interface UserRepositry extends JpaRepository<User, Integer> ,CommonRepositry<User, Integer>{
	List<User> findAllByEmailAddressAndDeleteStatusFalse(String emailAddress);
	User findOneByEmailAddressAndDeleteStatusFalse(String emailAddress);
	@Modifying
	@Query("Update User t SET t.password=:password WHERE t.id=:id")
	public void updatePassword(@Param("id") Integer id, @Param("password") String password);
}
