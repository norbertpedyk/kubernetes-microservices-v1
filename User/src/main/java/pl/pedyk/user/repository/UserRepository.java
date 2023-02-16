package pl.pedyk.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.pedyk.user.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

}
