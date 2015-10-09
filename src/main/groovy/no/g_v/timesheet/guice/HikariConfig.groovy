package no.g_v.timesheet.guice

import com.google.inject.Inject
import com.google.inject.name.Named

class HikariConfig implements DatasourceConfig {
    String dataSourceClassName
    String jdbcUrl
    String username
    String password

    @Inject
    HikariConfig(@Named('datasource.class-name') String datasourceClassName,
                 @Named('datasource.url') jdbcUrl,
                 @Named('datasource.username') String username,
                 @Named('datasource.password') String password) {
        this.dataSourceClassName = datasourceClassName
        this.jdbcUrl = jdbcUrl
        this.username = username
        this.password = password
    }

    @Override
    String dataSourceClassName() {
        dataSourceClassName
    }

    @Override
    String jdbcUrl() {
        jdbcUrl
    }

    @Override
    String username() {
        username
    }

    @Override
    String password() {
        password
    }
}
