package no.g_v.timesheet.guice.module

import com.google.inject.Inject
import com.google.inject.Provides
import com.google.inject.Singleton
import com.google.inject.name.Names
import com.stormpath.sdk.api.ApiKeys
import com.stormpath.sdk.client.Client
import com.stormpath.sdk.client.Clients
import com.stormpath.shiro.realm.ApplicationRealm
import org.apache.shiro.cache.CacheManager
import org.apache.shiro.cache.MemoryConstrainedCacheManager
import org.apache.shiro.guice.web.ShiroWebModule
import org.apache.shiro.realm.Realm

import javax.servlet.ServletContext

class SecurityWebModule extends ShiroWebModule {

    SecurityWebModule(ServletContext servletContext) {
        super(servletContext)
    }

    @Override
    protected void configureShiroWeb() {
        bindRealm().to(Realm)
        bindConstant().annotatedWith(Names.named('shiro.loginUrl')).to('/login.html')
        addFilterChain('/index.html', ANON)
        addFilterChain('/login.html', ANON)
        addFilterChain('/auth/login/**', ANON)
        addFilterChain('/**', AUTHC)
    }

    @Provides
    @Singleton
    @Inject
    Realm realm(Client client, CacheManager cacheManager) {
        def APPLICATION_ID = System.getProperty('stormpath.application.id')
        def realm = new ApplicationRealm()
        realm.client = client
        realm.applicationRestUrl = "https://api.stormpath.com/v1/applications/${APPLICATION_ID}"
        realm.cacheManager = cacheManager
        realm
    }

    @Provides
    Client client() {
        def properties = new Properties()
        properties.putAll(['apiKey.id'    : System.getProperty('stormpath.apiKey.id'),
                           'apiKey.secret': System.getProperty('stormpath.apiKey.secret')])

        def apiKey = ApiKeys.builder()
                .setProperties(properties)
                .build()

        Clients.builder()
                .setApiKey(apiKey)
                .build()
    }

    @Provides
    CacheManager cacheManager() {
        new MemoryConstrainedCacheManager()
    }
}
