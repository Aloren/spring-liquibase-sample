package org.example.database;

import liquibase.Liquibase;
import liquibase.database.DatabaseFactory;
import liquibase.database.jvm.JdbcConnection;
import liquibase.resource.ClassLoaderResourceAccessor;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
public class RollbackTest extends BaseDatabaseTest {

    private static final String CHANGE_LOG_FILE = "changelog/changelog-current.xml";
    public static final String NO_CONTEXTS = "";

    @Value("${liquibase.changeLog}")
    private String liquibaseChangeLog;

    @Test
    public void shouldUpdateAndRollbackSuccessfully() throws Exception {
        Liquibase liquibase = new Liquibase(
                CHANGE_LOG_FILE,
                new ClassLoaderResourceAccessor(),
                DatabaseFactory.getInstance().findCorrectDatabaseImplementation(new JdbcConnection(dataSource.getConnection()))
        );

        liquibase.update(NO_CONTEXTS);
        liquibase.rollback("version-1.0", NO_CONTEXTS);
    }

}
