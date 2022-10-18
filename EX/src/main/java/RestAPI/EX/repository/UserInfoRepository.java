package RestAPI.EX.repository;

import RestAPI.EX.repository.entity.UserInfoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserInfoRepository extends JpaRepository<UserInfoEntity, Long> {

    Optional<UserInfoEntity> findByUserId(String userId);

    Optional<UserInfoEntity> findByUserIdAndPassword(String userId, String password);
}
