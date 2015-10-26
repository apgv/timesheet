package no.g_v.timesheet.security

import com.stormpath.sdk.client.Client
import com.stormpath.spring.security.client.ClientFactory
import com.stormpath.spring.security.provider.StormpathAuthenticationProvider
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.io.FileSystemResourceLoader
import org.springframework.security.authentication.AuthenticationProvider
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter

@Configuration
@EnableWebSecurity
class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    void configureGlobal(AuthenticationManagerBuilder auth, AuthenticationProvider authenticationProvider) {
        auth.authenticationProvider(authenticationProvider)
    }

    @Bean
    @Autowired
    AuthenticationProvider authenticationProvider(Client client, Properties stormpathProperties) {
        def provider = new StormpathAuthenticationProvider()
        provider.client = client
        provider.applicationRestUrl = stormpathProperties.getProperty('application.resturl')
        provider
    }

    @Bean
    @Autowired
    Client client(Properties stormpathProperties) {
        def factory = new ClientFactory()
        factory.apiKeyProperties = stormpathProperties
        factory.clientBuilder.build()
    }

    @Bean
    Properties stormpathProperties(@Value('${stormpath.properties.path}') String stormpathPropertiesPath) {
        def resource = new FileSystemResourceLoader().getResource(stormpathPropertiesPath)
        def properties = new Properties()
        properties.load(resource.inputStream)
        properties
    }
}
