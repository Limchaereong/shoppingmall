package com.nhnacademy.shoppingmall.entity.pointHistory.repository.Impl;

import com.nhnacademy.shoppingmall.entity.pointHistory.domain.PointHistory;
import com.nhnacademy.shoppingmall.entity.pointHistory.repository.PointHistoryRepository;
import com.nhnacademy.shoppingmall.common.mvc.transaction.DbConnectionThreadLocal;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class PointHistoryRepositoryImpl implements PointHistoryRepository {

    @Override
    public List<PointHistory> getAllPointHistory() {
        List<PointHistory> pointHistoryList = new ArrayList<>();
        Connection connection = DbConnectionThreadLocal.getConnection();

        if (connection == null) {
            throw new RuntimeException("Failed to obtain connection");
        }

        String sql = "SELECT * FROM point_history";

        try (PreparedStatement pstmt = connection.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                int pointHistoryId = rs.getInt("point_history_id");
                String userId = rs.getString("user_id");
                String pointAction = rs.getString("point_action");
                BigDecimal pointAmount = rs.getBigDecimal("point_amount");
                LocalDateTime pointHistoryDate = rs.getTimestamp("point_history_date").toLocalDateTime();

                PointHistory pointHistory = new PointHistory();
                pointHistory.setPointHistoryId(pointHistoryId);
                pointHistory.setUserId(userId);
                pointHistory.setPointAction(pointAction);
                pointHistory.setPointAmount(pointAmount);
                pointHistory.setPointHistoryDate(pointHistoryDate);

                pointHistoryList.add(pointHistory);
            }
        } catch (SQLException e) {
            System.out.println("Error retrieving point history: " + e.getMessage());
        }

        return pointHistoryList;
    }


    @Override
    public void addPointHistory(PointHistory pointHistory) {
        Connection connection = DbConnectionThreadLocal.getConnection();

        if (connection == null) {
            throw new RuntimeException("Failed to obtain connection");
        }

        String sql = "INSERT INTO point_history (user_id, point_action, point_amount, point_history_date) VALUES (?, ?, ?, ?)";

        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, pointHistory.getUserId());
            pstmt.setString(2, pointHistory.getPointAction());
            pstmt.setBigDecimal(3, pointHistory.getPointAmount());
            pstmt.setTimestamp(4, java.sql.Timestamp.valueOf(pointHistory.getPointHistoryDate()));
            pstmt.executeUpdate();
            System.out.println("Added point history: " + pointHistory);
        } catch (SQLException e) {
            System.out.println("Error adding point history: " + e.getMessage());
        }
    }
}
