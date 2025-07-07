package org.scoula.common.pagination;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class PageRequest {
    private int page; //요청페이지

    private int amount;//한 페이지당 데이터 건수

    public PageRequest() {
        page = 1;
        amount = 10;
    }
    public static PageRequest of(int page, int amount) {
        return new PageRequest(page, amount);
    }
    public int getOffset() { // offset값계산

        return (page - 1) * amount;
    }
}