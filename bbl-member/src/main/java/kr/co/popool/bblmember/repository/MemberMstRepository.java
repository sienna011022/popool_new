package kr.co.popool.bblmember.repository;

import kr.co.popool.bblmember.domain.entity.MemberMstEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MemberMstRepository extends JpaRepository<MemberMstEntity, Long> {

    //find
    Optional<MemberMstEntity> findByIdentity(String identity);

    //exists
    Boolean existsByIdentity(String identity);
    Boolean existsByEmail(String email);
}
