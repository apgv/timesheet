package no.g_v.timesheet.database.config

interface DatabaseConfig {

    String dataSourceClassName()

    String host()

    String port()

    String databaseName()

    String username()

    String password()
}