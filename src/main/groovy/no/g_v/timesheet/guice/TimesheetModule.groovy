package no.g_v.timesheet.guice

import com.google.inject.AbstractModule

class TimesheetModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(DatasourceConfig).to(HikariConfig)
    }
}
