package com.nhnacademy.shoppingmall.entity.pointHistory.domain;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
public class PointHistory {
    private int pointHistoryId;
    private String userId;
    private String pointAction;
    private BigDecimal pointAmount;
    private LocalDateTime pointHistoryDate;

    public PointHistory(String userId, String pointAction, BigDecimal pointAmount, LocalDateTime pointHistoryDate) {
        this.userId = userId;
        this.pointAction = pointAction;
        this.pointAmount = pointAmount;
        this.pointHistoryDate = LocalDateTime.now();
    }

    public PointHistory() {
        //
    }

    @Override
    public String toString() {
        return "PointHistory{" +
                "userId=" + userId +
                ", pointAction=" + pointAction +
                ", pointAmount=" + pointAmount +
                ", pointHistoryDate='" + pointHistoryDate + '\'' +
                '}';
    }
}