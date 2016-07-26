package org.example.database;

import org.junit.After;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.PropertyPlaceholderAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.liquibase.LiquibaseAutoConfiguration;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.sql.DataSource;
import java.sql.SQLException;

/**
 * Вот здесь используй либо @EnableAutoConfiguration
 * либо явно указывай какие конфигурации поднимать:
 * DataSourceAutoConfiguration.class,
 * LiquibaseAutoConfiguration.class,
 * DatabaseMigrationTest.class
 *
 * В этом тесте я оставила явные конфигурации для наглядности.
 */
//@EnableAutoConfiguration
@SpringApplicationConfiguration(
        classes = {
                DataSourceAutoConfiguration.class,
                LiquibaseAutoConfiguration.class,
                PropertyPlaceholderAutoConfiguration.class
        }
)
@RunWith(SpringJUnit4ClassRunner.class)
public abstract class BaseDatabaseTest {

    private static final String H2DropAllSql = "DROP ALL OBJECTS";

    @Autowired
    protected DataSource dataSource;

    @After
    public void tearDown() throws SQLException {
        dataSource.getConnection().createStatement().execute(H2DropAllSql);
    }

}
