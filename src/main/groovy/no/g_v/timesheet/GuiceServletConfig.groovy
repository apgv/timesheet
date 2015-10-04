package no.g_v.timesheet

import com.google.inject.Guice
import com.google.inject.Injector
import com.google.inject.Scopes
import com.google.inject.servlet.GuiceServletContextListener
import com.google.inject.servlet.ServletModule
import org.apache.shiro.guice.web.GuiceShiroFilter
import spark.servlet.SparkFilter

import javax.servlet.ServletContext
import javax.servlet.ServletContextEvent

class GuiceServletConfig extends GuiceServletContextListener {

    private ServletContext servletContext

    @Override
    void contextInitialized(ServletContextEvent servletContextEvent) {
        this.servletContext = servletContextEvent.servletContext
        super.contextInitialized(servletContextEvent)
    }

    @Override
    protected Injector getInjector() {
        Guice.createInjector(
                new ServletModule() {
                    @Override
                    protected void configureServlets() {
                        filter('/*').through(GuiceShiroFilter)

                        bind(SparkFilter).in(Scopes.SINGLETON)
                        filter('/*').through(SparkFilter, [applicationClass: 'no.g_v.timesheet.Application'])
                    }
                },
                new SecurityWebModule(servletContext))
    }


}