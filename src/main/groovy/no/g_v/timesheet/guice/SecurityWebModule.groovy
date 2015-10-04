package no.g_v.timesheet.guice

import com.google.inject.Provides
import com.google.inject.name.Names
import com.stormpath.sdk.api.ApiKeys
import com.stormpath.sdk.client.Client
import com.stormpath.sdk.client.Clients
import com.stormpath.shiro.realm.ApplicationRealm
import com.stormpath.shiro.realm.DefaultGroupRoleResolver
import com.stormpath.shiro.realm.GroupRoleResolver
import org.apache.shiro.guice.web.ShiroWebModule
import org.apache.shiro.realm.Realm

import javax.servlet.ServletContext

class SecurityWebModule extends ShiroWebModule {

    SecurityWebModule(ServletContext servletContext) {
        super(servletContext)
    }

    @Override
    protected void configureShiroWeb() {
        bindRealm().toInstance(realm())
        bindConstant().annotatedWith(Names.named("shiro.loginUrl")).to("/login.html")
        addFilterChain("/index.html", ANON)
        addFilterChain("/login.html", ANON)
        addFilterChain("/auth/login/**", ANON)
        addFilterChain("/**", AUTHC)
    }

    @Provides
    Realm realm() {
        def APPLICATION_ID = System.getProperty('stormpath.application.id')
        def realm = new ApplicationRealm()
        realm.client = client()
        realm.applicationRestUrl = "https://api.stormpath.com/v1/applications/${APPLICATION_ID}"
        realm.groupRoleResolver = groupRoleResolver()
        realm
    }

    @Provides
    Client client() {
        final properties = new Properties()
        properties.putAll(['apiKey.id'    : System.getProperty('stormpath.apiKey.id'),
                           'apiKey.secret': System.getProperty('stormpath.apiKey.secret')])

        final apiKey = ApiKeys.builder()
                .setProperties(properties)
                .build()

        Clients.builder()
                .setApiKey(apiKey)
                .build()
    }

    @Provides
    GroupRoleResolver groupRoleResolver() {
        final groupRoleResolver = new DefaultGroupRoleResolver()
        groupRoleResolver.setModes([DefaultGroupRoleResolver.Mode.NAME] as Set)
        groupRoleResolver
    }
}
