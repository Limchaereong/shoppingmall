package com.nhnacademy.shoppingmall.common.mvc.transaction;

import com.nhnacademy.shoppingmall.common.util.DbUtils;
import lombok.extern.slf4j.Slf4j;

import java.sql.Connection;
import java.sql.SQLException;
@Slf4j
public class DbConnectionThreadLocal {
    private static final ThreadLocal<Connection> connectionThreadLocal = new ThreadLocal<>();
    private static final ThreadLocal<Boolean> sqlErrorThreadLocal = ThreadLocal.withInitial(() -> false);

    public static void initialize() {
        try {
            Connection con = DbUtils.getDataSource().getConnection();
            configureConnection(con);
            connectionThreadLocal.set(con); //변수 설정
        } catch (SQLException e) {
            log.error("DB 연결 초기화 중 오류 발생: {}", e.getMessage(), e);
            // 여기서 RuntimeException을 던지면 getConnection에서 새로운 연결 시도가 실패했음을 알 수 있음.
            throw new RuntimeException("DB 연결 초기화 실패", e);
        }
    }

    private static void configureConnection(Connection con) throws SQLException {
        con.setAutoCommit(false);
        con.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
    }

    public static Connection getConnection() {
        Connection con = connectionThreadLocal.get();
        if (con == null) {
            log.warn("ThreadLocal에서 null Connection 반환됨, 새로 초기화 시도.");
            try {
                initialize(); // 새로운 연결 초기화 시도
                con = connectionThreadLocal.get();
                if (con == null) {
                    throw new RuntimeException("새로운 DB 연결 초기화 실패.");
                }
            } catch (Exception e) {
                log.error("DB 연결 초기화 재시도 실패: {}", e.getMessage(), e);
                throw e;
            }
        }
        return con;
    }

    public static void setSqlError(boolean sqlError) {
        sqlErrorThreadLocal.set(sqlError);
    }

    public static boolean getSqlError() {
        return sqlErrorThreadLocal.get();
    }

    public static void reset() {
        Connection conn = connectionThreadLocal.get();
        if (conn != null) {
            try {
                if (Boolean.TRUE.equals(sqlErrorThreadLocal.get())) {
                    conn.rollback();
                    log.info("트랜잭션 롤백 성공.");
                } else {
                    conn.commit();
                    log.info("트랜잭션 커밋 성공.");
                }
            } catch (SQLException e) {
                log.error("트랜잭션 롤백 또는 커밋 중 오류 발생", e);
                throw new RuntimeException("트랜잭션 처리 실패", e);
            } finally {
                try {
                    conn.close();
                } catch (SQLException e) {
                    log.error("Connection 닫기 실패", e);
                    throw new RuntimeException("Connection 닫기 실패", e);
                }
                connectionThreadLocal.remove();
                sqlErrorThreadLocal.remove();
            }
        } else {
            log.warn("reset 메서드가 호출되었지만, 연결이 이미 null입니다.");
        }
    }

    @Override
    protected void finalize() throws Throwable {
        try {
            Connection con = connectionThreadLocal.get();
            if (con != null && !con.isClosed()) {
                log.warn("연결 누수 감지. 연결이 제대로 닫히지 않음.");
                con.close();
            }
        } finally {
            super.finalize();
        }
    }
}
