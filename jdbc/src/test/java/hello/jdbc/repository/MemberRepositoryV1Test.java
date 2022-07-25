package hello.jdbc.repository;

import com.zaxxer.hikari.HikariDataSource;
import hello.jdbc.connection.ConnectionConst;
import hello.jdbc.domain.Member;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import java.sql.SQLException;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;
@Slf4j
class MemberRepositoryV1Test {

    MemberRepositoryV1 repository;

    @BeforeEach
    void beforeEach() {
        // 기본 DriverManager - 항상 새로운 커넥션 획득
//        DriverManagerDataSource dataSource = new DriverManagerDataSource(ConnectionConst.URL, ConnectionConst.USERNAME, ConnectionConst.PASSWORD);

        // 커넥션 풀
        HikariDataSource dataSource = new HikariDataSource();
        dataSource.setJdbcUrl(ConnectionConst.URL);
        dataSource.setUsername(ConnectionConst.USERNAME);
        dataSource.setPassword(ConnectionConst.PASSWORD);
        repository = new MemberRepositoryV1(dataSource);

    }

    @Test
    void curd() throws SQLException, InterruptedException {
        Member memberV0 = new Member("memberV100", 10000);
        repository.save(memberV0);

        // findById
        Member findMember = repository.findById(memberV0.getMemberId());
        log.info("findMember = {}", findMember);
        Assertions.assertThat(findMember).isEqualTo(memberV0);

        // update: money: 10000 -> 20000
        repository.update(memberV0.getMemberId(), 20000);
        Member updateMember = repository.findById(memberV0.getMemberId());
        Assertions.assertThat(updateMember.getMoney()).isEqualTo(20000);

        // delete
        repository.delete(memberV0.getMemberId());
        Assertions.assertThatThrownBy(() -> repository.findById(memberV0.getMemberId()))
                .isInstanceOf(NoSuchElementException.class);

        Thread.sleep(1000);
    }


}