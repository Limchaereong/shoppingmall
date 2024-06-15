package com.nhnacademy.shoppingmall.common.page;

import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
public class Page<T> {

    private final List<T> content;

    private final long totalCount;

    public Page(List<T> content, long totalCount) {
        this.content = content;
        this.totalCount = totalCount;
        log.debug("상품 총 갯수 : " + totalCount);
    }

    public List<T> getContent() {
        return content;
    }

    public long getTotalCount() {
        return totalCount;
    }

}
