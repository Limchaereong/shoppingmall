package com.nhnacademy.shoppingmall.entity.pointHistory.service;

import com.nhnacademy.shoppingmall.entity.pointHistory.domain.PointHistory;

import java.util.List;

public interface PointHistoryService {
    List<PointHistory> getAllPointHistory();
    void addPointHistory(PointHistory pointHistory);
}
