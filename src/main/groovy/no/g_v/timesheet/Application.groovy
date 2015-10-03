package no.g_v.timesheet

import org.apache.shiro.SecurityUtils
import org.apache.shiro.authc.UsernamePasswordToken
import spark.servlet.SparkApplication

import static spark.Spark.*

class Application implements SparkApplication {

    @Override
    void init() {
        staticFileLocation('/public')

        get('/hello', { request, response -> 'Hello from Spark' })

        post('/auth/login', { request, response ->

            def usernamePasswordToken = new UsernamePasswordToken(
                    request.raw().getParameter('username'),
                    request.raw().getParameter('password')
            )
            try {
                SecurityUtils.subject.login(usernamePasswordToken)
                response.redirect('/secure/secret.html')
            } catch (Exception e) {
                response.status(400)

                'Wrong username/password'
            }
        })

    }
}
