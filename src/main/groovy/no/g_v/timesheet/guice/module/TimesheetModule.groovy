package no.g_v.timesheet.guice.module

import com.google.inject.AbstractModule
import com.google.inject.Inject
import com.google.inject.Provides
import com.google.inject.Singleton
import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import no.g_v.timesheet.database.config.DatabaseConfig
import no.g_v.timesheet.database.config.MySqlConfig
import no.g_v.timesheet.database.migration.DatabaseMigration
import no.g_v.timesheet.database.migration.FlywayMigration

class TimesheetModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(DatabaseConfig).to(MySqlConfig)
        bind(DatabaseMigration).to(FlywayMigration)
    }

    @Provides
    @Singleton
    @Inject
    HikariDataSource hikariDataSource(HikariConfig hikariConfig) {
        new HikariDataSource(hikariConfig)
    }

    @Provides
    @Inject
    HikariConfig hikariConfig(DatabaseConfig databaseConfig) {
        def hikariConfig = new HikariConfig()
        hikariConfig.with {
            dataSourceClassName = databaseConfig.dataSourceClassName()
            addDataSourceProperty('serverName', databaseConfig.host())
            addDataSourceProperty('port', databaseConfig.port())
            addDataSourceProperty('databaseName', databaseConfig.databaseName())
            addDataSourceProperty('user', databaseConfig.username())
            addDataSourceProperty('password', databaseConfig.password())
        }
        hikariConfig
    }
}
