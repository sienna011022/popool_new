package kr.co.popool.domain.entity;

import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;

@RequiredArgsConstructor
public enum Grade {
    GOLD, SILVER, BRONZE, BLACK, WHITE;
    private final static int MAX_SCORE = 25;
    private final static int BOUNDARY_1 = 20;
    private final static int BOUNDARY_2 = 15;
    private final static int BOUNDARY_3 = 10;


    public static Grade getMedal(BigDecimal average) {
        int avg = average.intValue();
        if (BOUNDARY_1 < avg && avg <= MAX_SCORE) {
            return GOLD;
        } else if (BOUNDARY_2 < avg && avg <= BOUNDARY_1) {
            return SILVER;
        } else if (BOUNDARY_3 < avg && avg <= BOUNDARY_2) {
            return BRONZE;
        } else {
            return BLACK;
        }
    }
}
