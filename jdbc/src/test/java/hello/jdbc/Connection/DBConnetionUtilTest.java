package hello.jdbc.Connection;

import hello.jdbc.connection.DBConnectionUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.sql.Connection;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
public class DBConnetionUtilTest {

    @Test
    void connetion() {
        Connection connection = DBConnectionUtil.getConnetion();
        assertThat(connection).isNotNull();
    }
}
