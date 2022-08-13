package backend.sellerB.repository;

import backend.sellerB.entity.Authority;
import backend.sellerB.entity.MemberAuth;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AuthorityRepository extends JpaRepository<Authority, String> {
    Optional<Authority> findByAuthorityName(MemberAuth authorityName);

}
