package org.birlasoft.usermanagement.repositry;

import java.util.List;

import org.birlasoft.usermanagement.bean.PasswordsHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PasswordsHistoryRepository extends JpaRepository<PasswordsHistory, Integer> {
	List<PasswordsHistory> findTop3ByUidOrderByCreatedDateDesc(Integer userId);

	
}
