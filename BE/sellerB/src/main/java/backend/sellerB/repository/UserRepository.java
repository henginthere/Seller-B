package backend.sellerB.repository;

import backend.sellerB.entity.Consultant;
import backend.sellerB.entity.Manager;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<Consultant, Integer> {
    // 아래의 메소드는 username을 기준으로 user정보를 가져올때 권한정보도 같이 가져옴
//    @EntityGraph(attributePaths = "authorities") // EntityGraph annotation은 쿼리가 수행될때 Lazy조회가 아니고 Eager조회로 authorities 정보를 같이 가져옴
//    Optional<User> findOneWithAuthoritiesByUserid(String userid);
//    Optional<User> findOneByUserid(String userid);
//    @EntityGraph(attributePaths = "authorities") // EntityGraph annotation은 쿼리가 수행될때 Lazy조회가 아니고 Eager조회로 authorities 정보를 같이 가져옴
//    Optional<Manager> findOneWithAuthoritiesBymanagerId(String managerId);


}
