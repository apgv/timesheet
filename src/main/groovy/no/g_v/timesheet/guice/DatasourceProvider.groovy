package no.g_v.timesheet.guice

import com.google.inject.Inject
import com.google.inject.Provider
import com.zaxxer.hikari.HikariDataSource

import javax.sql.DataSource

@Singleton
class DatasourceProvider implements Provider<DataSource> {

    @Inject
    DatasourceConfig datasourceConfig

    @Override
    DataSource get() {
        def dataSource = new HikariDataSource()
        dataSource.driverClassName = datasourceConfig.dataSourceClassName()
        dataSource.jdbcUrl = datasourceConfig.jdbcUrl()
        dataSource.username = datasourceConfig.username()
        dataSource.password = datasourceConfig.password()
        dataSource
    }
}
