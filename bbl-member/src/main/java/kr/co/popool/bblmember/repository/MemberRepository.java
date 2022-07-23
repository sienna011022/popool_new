package kr.co.popool.bblmember.repository;

import kr.co.popool.bblmember.domain.entity.CorporateEntity;
import kr.co.popool.bblmember.domain.entity.MemberEntity;
import kr.co.popool.bblmember.domain.shared.Phone;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.Option;
import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<MemberEntity, Long> {

    //find
    Optional<MemberEntity> findByIdentity(String identity);
    Optional<CorporateEntity> findByCorporateEntity(CorporateEntity corporateEntity);
    Optional<MemberEntity> findByNameAndPhoneAndBirth(String name, Phone phone, String birth);

    //exists
    Boolean existsByIdentity(String identity);
    Boolean existsByEmail(String email);
    Boolean existsByPhone(Phone phone);
}
