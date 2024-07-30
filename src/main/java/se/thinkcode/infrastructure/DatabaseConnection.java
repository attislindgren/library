package se.thinkcode.infrastructure;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.apache.tomcat.jdbc.pool.PoolProperties;
import org.flywaydb.core.Flyway;
import org.jdbi.v3.core.Jdbi;
import org.jdbi.v3.sqlobject.SqlObjectPlugin;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import se.thinkcode.BookDao;
import se.thinkcode.BorrowerDao;
import se.thinkcode.LoanDao;
import se.thinkcode.version.VersionDao;

public class DatabaseConnection {
    private static final Logger logger = LoggerFactory.getLogger(DatabaseConnection.class);
    private static DataSource datasource;

    public DatabaseConnection() {
        createDataSource();
    }

    private synchronized void createDataSource() {
        if (datasource == null) {
            logger.info("Creating the datasource");

            String url = "jdbc:h2:mem:tage;MODE=PostgreSQL;DATABASE_TO_LOWER=TRUE;NON_KEYWORDS=VALUE";
            String driverName = "org.h2.Driver";

            PoolProperties p = getPoolProperties(url, driverName);

            datasource = new DataSource();
            datasource.setPoolProperties(p);

            logger.info("The datasource is created");

            prepareDatabase();
        }
    }

    private static PoolProperties getPoolProperties(String url, String driverName) {
        PoolProperties p = new PoolProperties();
        p.setUrl(url);
        p.setDriverClassName(driverName);

        p.setJmxEnabled(true);
        p.setTestWhileIdle(false);
        p.setTestOnBorrow(true);
        p.setValidationQuery("SELECT 1");
        p.setTestOnReturn(false);
        p.setValidationInterval(30000);
        p.setTimeBetweenEvictionRunsMillis(30000);
        p.setMaxActive(30);
        p.setMaxIdle(30);
        p.setInitialSize(1);
        p.setMaxWait(10000);
        p.setRemoveAbandonedTimeout(60);
        p.setMinEvictableIdleTimeMillis(30000);
        p.setMinIdle(2);
        p.setLogAbandoned(true);
        p.setRemoveAbandoned(true);
        p.setJdbcInterceptors(
                "org.apache.tomcat.jdbc.pool.interceptor.ConnectionState;" +
                        "org.apache.tomcat.jdbc.pool.interceptor.StatementFinalizer");
        return p;
    }

    private void prepareDatabase() {
        logger.info("Migrating the database");
        try {
            Flyway flyway = Flyway.configure()
                    .dataSource(datasource)
                    .locations("db/migration")
                    .load();
            flyway.repair();
            flyway.migrate();
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw new RuntimeException(e);
        }
        logger.info("Database migrated");
    }

    public VersionDao getVersionDao() {
        Jdbi jdbi = getJdbi();
        jdbi.installPlugin(new SqlObjectPlugin());

        return jdbi.onDemand(VersionDao.class);
    }

    private synchronized Jdbi getJdbi() {
        return Jdbi.create(datasource);
    }

    public BorrowerDao getBorrowerDao() {
        Jdbi jdbi = getJdbi();
        jdbi.installPlugin(new SqlObjectPlugin());

        return jdbi.onDemand(BorrowerDao.class);
    }

    public BookDao getBookDao() {
        Jdbi jdbi = getJdbi();
        jdbi.installPlugin(new SqlObjectPlugin());

        return jdbi.onDemand(BookDao.class);
    }

    public LoanDao getLoanDao() {
        Jdbi jdbi = getJdbi();
        jdbi.installPlugin(new SqlObjectPlugin());

        return jdbi.onDemand(LoanDao.class);
    }
}