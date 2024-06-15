package com.nhnacademy.shoppingmall.entity.pointHistory.repository;

import com.nhnacademy.shoppingmall.entity.pointHistory.domain.PointHistory;

import java.util.List;

public interface PointHistoryRepository {
    //int save(PointHistory pointHistory);
    List<PointHistory> getAllPointHistory();
    void addPointHistory(PointHistory pointHistory);
    // Other methods if needed
}