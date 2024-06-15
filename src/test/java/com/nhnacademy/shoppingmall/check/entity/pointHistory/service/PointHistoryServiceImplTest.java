package com.nhnacademy.shoppingmall.check.entity.pointHistory.service;

import com.nhnacademy.shoppingmall.entity.pointHistory.domain.PointHistory;
import com.nhnacademy.shoppingmall.entity.pointHistory.repository.PointHistoryRepository;
import com.nhnacademy.shoppingmall.entity.pointHistory.service.Impl.PointHistoryServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class PointHistoryServiceImplTest {

    private PointHistoryServiceImpl pointHistoryService;

    @Mock
    private PointHistoryRepository pointHistoryRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        pointHistoryService = new PointHistoryServiceImpl(pointHistoryRepository);
    }

    @Test
    public void testGetAllPointHistory() {
        // Mock data
        List<PointHistory> mockPointHistoryList = new ArrayList<>();
        mockPointHistoryList.add(new PointHistory("user1", "적립", new BigDecimal("1000.00"), LocalDateTime.now()));
        mockPointHistoryList.add(new PointHistory("user2", "사용", new BigDecimal("10000.00"), LocalDateTime.now()));

        // Mock Repository behavior
        when(pointHistoryRepository.getAllPointHistory()).thenReturn(mockPointHistoryList);

        // Call the service method
        List<PointHistory> result = pointHistoryService.getAllPointHistory();

        // Verify the result
        assertEquals(mockPointHistoryList.size(), result.size());
        for (int i = 0; i < mockPointHistoryList.size(); i++) {
            assertEquals(mockPointHistoryList.get(i), result.get(i));
        }

        // Verify Repository method invocation
        verify(pointHistoryRepository, times(1)).getAllPointHistory();
    }

    @Test
    public void testAddPointHistory() {
        // Mock data
        PointHistory mockPointHistory = new PointHistory("user", "적립", new BigDecimal("1000.0"), LocalDateTime.now());

        // Call the service method
        pointHistoryService.addPointHistory(mockPointHistory);

        // Verify Repository method invocation
        verify(pointHistoryRepository, times(1)).addPointHistory(mockPointHistory);
    }
}
