package no.g_v.timesheet.database.config

import com.google.inject.Inject
import com.google.inject.name.Named

class MySqlConfig implements DatabaseConfig {

    private String dataSourceClassName
    private String host
    private String port
    private String databaseName
    private String username
    private String password

    @Inject
    MySqlConfig(@Named('database.dataSourceClassName') String dataSourceClassName,
                @Named('database.host') String host,
                @Named('database.port') String port,
                @Named('database.databaseName') String databaseName,
                @Named('database.username') String username,
                @Named('database.password') String password) {
        this.dataSourceClassName = dataSourceClassName
        this.host = host
        this.port = port
        this.databaseName = databaseName
        this.username = username
        this.password = password
    }

    @Override
    String dataSourceClassName() {
        dataSourceClassName
    }

    @Override
    String host() {
        host
    }

    @Override
    String port() {
        port
    }

    @Override
    String databaseName() {
        databaseName
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
