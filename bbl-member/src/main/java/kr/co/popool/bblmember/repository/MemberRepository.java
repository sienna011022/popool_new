package kr.co.popool.bblmember.repository;

import kr.co.popool.bblmember.domain.entity.MemberEntity;
import kr.co.popool.bblmember.domain.shared.Phone;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<MemberEntity, Long> {

    //find
    Optional<MemberEntity> findByIdentity(String identity);

    //exists
    Boolean existsByIdentity(String identity);
    Boolean existsByEmail(String email);
    Boolean existsByPhone(Phone phone);
}
