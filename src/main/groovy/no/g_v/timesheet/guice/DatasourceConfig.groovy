package no.g_v.timesheet.guice

interface DatasourceConfig {
    String dataSourceClassName()
    String jdbcUrl()
    String username()
    String password()
}