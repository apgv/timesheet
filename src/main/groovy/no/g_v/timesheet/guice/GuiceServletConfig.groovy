package no.g_v.timesheet.guice

import com.google.inject.Guice
import com.google.inject.Injector
import com.google.inject.Singleton
import com.google.inject.servlet.GuiceServletContextListener
import com.google.inject.servlet.ServletModule
import com.zaxxer.hikari.HikariDataSource
import no.g_v.timesheet.database.migration.DatabaseMigration
import no.g_v.timesheet.guice.module.PropertiesModule
import no.g_v.timesheet.guice.module.SecurityWebModule
import no.g_v.timesheet.guice.module.TimesheetModule
import org.apache.shiro.guice.web.GuiceShiroFilter
import spark.servlet.SparkFilter

import javax.servlet.ServletContext
import javax.servlet.ServletContextEvent

class GuiceServletConfig extends GuiceServletContextListener {

    private ServletContext servletContext

    @Override
    void contextInitialized(ServletContextEvent servletContextEvent) {
        this.servletContext = servletContextEvent.servletContext
        def databaseMigration = getInjector().getInstance(DatabaseMigration)
        databaseMigration.migrate()
    }

    @Override
    protected Injector getInjector() {
        Guice.createInjector(
                new ServletModule() {
                    @Override
                    protected void configureServlets() {
                        filter('/*').through(GuiceShiroFilter)

                        bind(SparkFilter).in(Singleton)
                        filter('/*').through(SparkFilter, [applicationClass: 'no.g_v.timesheet.spark.Application'])
                    }
                },
                new SecurityWebModule(servletContext),
                new PropertiesModule(),
                new TimesheetModule()
        )
    }

    @Override
    void contextDestroyed(ServletContextEvent servletContextEvent) {
        def hikariDataSource = getInjector().getInstance(HikariDataSource)
        hikariDataSource.close()
        super.contextDestroyed(servletContextEvent)
    }
}
