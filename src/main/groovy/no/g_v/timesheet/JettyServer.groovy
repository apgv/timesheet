package no.g_v.timesheet

import com.google.inject.servlet.GuiceFilter
import no.g_v.timesheet.guice.GuiceServletConfig
import org.eclipse.jetty.server.Server
import org.eclipse.jetty.webapp.WebAppContext

import javax.servlet.DispatcherType

class JettyServer {

    static void main(String... args) {
        def server = new Server(8080)

        def webAppContext = new WebAppContext()
        webAppContext.setContextPath('/')
        webAppContext.setWar('/')
        webAppContext.addFilter(GuiceFilter, '/*', EnumSet.of(DispatcherType.REQUEST))
        webAppContext.addEventListener(new GuiceServletConfig())

        server.setHandler(webAppContext)

        server.start()
        server.join()
    }
}
