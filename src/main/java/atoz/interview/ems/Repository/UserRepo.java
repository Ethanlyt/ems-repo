package atoz.interview.ems.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import atoz.interview.ems.Entity.User;


@Repository
public interface UserRepo extends JpaRepository<User, Long> {
    User findByName(String name);
}
