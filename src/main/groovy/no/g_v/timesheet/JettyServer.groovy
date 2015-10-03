package no.g_v.timesheet

import org.apache.shiro.web.env.EnvironmentLoaderListener
import org.apache.shiro.web.servlet.ShiroFilter
import org.eclipse.jetty.server.Server
import org.eclipse.jetty.webapp.WebAppContext
import spark.servlet.SparkFilter

import javax.servlet.DispatcherType

class JettyServer {

    static void main(String... args) {
        def server = new Server(8080)

        def webAppContext = new WebAppContext()
        webAppContext.setContextPath('/')
        webAppContext.setWar('/')

        webAppContext.addEventListener(new EnvironmentLoaderListener())

        webAppContext.addFilter(ShiroFilter, '/*', EnumSet.of(
                DispatcherType.REQUEST,
                DispatcherType.FORWARD,
                DispatcherType.INCLUDE,
                DispatcherType.ERROR
        ))

        def filterHolder = webAppContext.addFilter(SparkFilter, '/*', EnumSet.of(DispatcherType.REQUEST))
        filterHolder.setInitParameter('applicationClass', 'no.g_v.timesheet.Application')

        server.setHandler(webAppContext)

        server.start()
        server.join()
    }
}
