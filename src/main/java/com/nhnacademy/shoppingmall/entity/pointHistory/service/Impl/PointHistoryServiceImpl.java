package com.nhnacademy.shoppingmall.entity.pointHistory.service.Impl;

import com.nhnacademy.shoppingmall.entity.pointHistory.domain.PointHistory;
import com.nhnacademy.shoppingmall.entity.pointHistory.repository.PointHistoryRepository;
import com.nhnacademy.shoppingmall.entity.pointHistory.service.PointHistoryService;

import java.util.List;

public class PointHistoryServiceImpl implements PointHistoryService {
    private PointHistoryRepository pointHistoryRepository;

    public PointHistoryServiceImpl(PointHistoryRepository pointHistoryRepository) {
        this.pointHistoryRepository = pointHistoryRepository;
    }

    @Override
    public List<PointHistory> getAllPointHistory() {
        return pointHistoryRepository.getAllPointHistory();
    }

    @Override
    public void addPointHistory(PointHistory pointHistory) {
        pointHistoryRepository.addPointHistory(pointHistory);
    }
}
