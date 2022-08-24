package project_501.kk.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import project_501.kk.domain.Account;

import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account, Long> {
    Optional<Account> findByUsername(String username);
}
