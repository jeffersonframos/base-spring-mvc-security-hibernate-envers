package ramos.jefferson.base.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.history.RevisionRepository;
import ramos.jefferson.base.entity.User;

public interface UserRepository extends JpaRepository<User, Long>, RevisionRepository<User, Long, Long> {

    public User findByUsername(String username);

}
