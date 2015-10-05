package no.g_v.timesheet.guice

import com.google.inject.AbstractModule
import com.google.inject.name.Names

class PropertiesModule extends AbstractModule {

    @Override
    protected void configure() {
        def ENV_PROFILE = System.getProperty('application.env.profile')
        def properties = new Properties()
        PropertiesModule.getResourceAsStream("/application-${ENV_PROFILE}.properties").withStream {
            properties.load(it)
        }
        Names.bindProperties(binder(), properties)
    }
}
