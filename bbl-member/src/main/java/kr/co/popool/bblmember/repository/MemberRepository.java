package kr.co.popool.bblmember.repository;

import kr.co.popool.bblmember.domain.entity.CorporateEntity;
import kr.co.popool.bblmember.domain.entity.MemberEntity;
import kr.co.popool.bblmember.domain.shared.Phone;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<MemberEntity, Long> {

    //find
    Optional<MemberEntity> findByIdentity(String identity);
    Optional<MemberEntity> findByNameAndPhoneAndBirth(String name, Phone phone, String birth);
    Optional<MemberEntity> findByEmailAndProviderAndName(String email, String provider, String name);

    //exists
    boolean existsByIdentity(String identity);
    boolean existsByEmail(String email);
    boolean existsByPhone(Phone phone);
}
