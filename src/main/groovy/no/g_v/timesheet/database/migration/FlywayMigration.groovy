package no.g_v.timesheet.database.migration

import com.google.inject.Inject
import com.zaxxer.hikari.HikariDataSource
import org.flywaydb.core.Flyway

class FlywayMigration implements DatabaseMigration {

    private Flyway flyway

    @Inject
    FlywayMigration(HikariDataSource dataSource) {
        this.flyway = new Flyway()
        flyway.dataSource = dataSource
    }

    @Override
    void migrate() {
        flyway.migrate()
    }
}
