package kr.co.popool.career;

import kr.co.popool.domain.dto.career.CareerUpdateRequest;
import kr.co.popool.domain.entity.Career;
import kr.co.popool.repository.career.CareerRepository;
import kr.co.popool.service.career.CareerService;
import kr.co.popool.service.career.CareerServiceImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static java.util.Optional.of;
import static kr.co.popool.career.CareerFixture.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CareerServiceTest {

    @Mock
    CareerRepository careerRepository;
    @InjectMocks
    private CareerServiceImpl careerService;

    @Test
    @DisplayName("모든 인사 내역을 조회한다")
    public void career_전체_조회(){
        List<Career> careers = Arrays.asList(createCareer(),createCareer(),createCareer());
        when(careerRepository.findAll()).thenReturn(
            careers);
        assertThat(careerService.showAll()).hasSameSizeAs(3);
    }

    @Test
    @DisplayName("아이디로 인사 내역을 조회하고 인사 내역을 불러온다.")
    public void career_조회() {

        when(careerRepository.findByMemberId(MEMBER_ID))
            .thenReturn(of(createCareer()));

        assertThat(careerService.showCareer(MEMBER_ID))
            .isEqualTo(createCareerInfo());
    }

    @Test
    @DisplayName("인사 내역을 수정할 수 있다")
    public void 인사내역_수정() {

        when(careerRepository.findByMemberId(MEMBER_ID))
            .thenReturn(of(createCareer()));

        CareerUpdateRequest request = CareerFixture.createUpdateRequest();
        careerService.updateCareer(MEMBER_ID, request);

        assertThat(careerService.showCareer(MEMBER_ID)).isEqualTo(updateCareer());
    }
}
