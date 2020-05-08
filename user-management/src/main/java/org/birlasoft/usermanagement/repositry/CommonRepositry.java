package org.birlasoft.usermanagement.repositry;

import java.util.List;
import java.util.Optional;

public interface CommonRepositry<T,ID> {
	List<T> findByDeleteStatusFalse();
	Optional<T> findByIdAndDeleteStatusFalse(ID id);
}
