package com.nhnacademy.shoppingmall.check.entity.pointHistory.repository;

import com.nhnacademy.shoppingmall.entity.pointHistory.domain.PointHistory;
import com.nhnacademy.shoppingmall.entity.pointHistory.repository.Impl.PointHistoryRepositoryImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class PointHistoryRepositoryImplTest {

    private PointHistoryRepositoryImpl repository;
    private Connection connection;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;

    @BeforeEach
    public void setUp() throws SQLException {
        repository = new PointHistoryRepositoryImpl();

        // Mock objects
        connection = Mockito.mock(Connection.class);
        preparedStatement = Mockito.mock(PreparedStatement.class);
        resultSet = Mockito.mock(ResultSet.class);

        // Mock behavior
        when(connection.prepareStatement(anyString())).thenReturn(preparedStatement);
        when(preparedStatement.executeQuery()).thenReturn(resultSet);
    }

    // 기존 코드는 생략

    @Test
    public void testGetAllPointHistory() throws SQLException {
        // Mock 데이터 생성
        int pointHistoryId = 1;
        String userId = "user";
        String pointAction = "적립";
        BigDecimal pointAmount = new BigDecimal("1000.00");
        LocalDateTime pointHistoryDate = LocalDateTime.now(); // 현재 시각을 가져옴

        when(resultSet.next()).thenReturn(true).thenReturn(false);
        when(resultSet.getInt("point_history_id")).thenReturn(pointHistoryId);
        when(resultSet.getString("user_id")).thenReturn(userId);
        when(resultSet.getString("point_action")).thenReturn(pointAction);
        when(resultSet.getBigDecimal("point_amount")).thenReturn(pointAmount);
        when(resultSet.getTimestamp("point_history_date")).thenReturn(Timestamp.valueOf(pointHistoryDate));

        // 테스트
        List<PointHistory> pointHistoryList = repository.getAllPointHistory();

        // 확인
        assertEquals(14, pointHistoryList.size());
        PointHistory pointHistory = pointHistoryList.get(0);
        assertEquals(pointHistoryId, pointHistory.getPointHistoryId());
        assertEquals(userId, pointHistory.getUserId());
        assertEquals(pointAction, pointHistory.getPointAction());
        assertEquals(pointAmount, pointHistory.getPointAmount());

        // 밀리초를 무시하고 LocalDateTime 객체 비교
        assertEquals(pointHistoryDate.withNano(0), pointHistory.getPointHistoryDate().withNano(0));

        // Mock과의 상호 작용 확인
        verify(connection).prepareStatement(anyString());
        verify(preparedStatement).executeQuery();
        verify(resultSet, times(2)).next(); // 존재 여부 확인 및 데이터 읽기에 한 번씩 호출
        verify(resultSet).getInt("point_history_id");
        verify(resultSet).getString("user_id");
        verify(resultSet).getString("point_action");
        verify(resultSet).getBigDecimal("point_amount");
        verify(resultSet).getTimestamp("point_history_date");
    }


}
