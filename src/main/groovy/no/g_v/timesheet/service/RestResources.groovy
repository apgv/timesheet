package no.g_v.timesheet.service

import groovy.json.JsonOutput
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import spark.servlet.SparkApplication

import static spark.Spark.get

@Service
class RestResources implements SparkApplication {

    @Autowired
    private GreeterService greeterService

    @Override
    void init() {

        get('/hello', { request, response ->
            response.type('application/json')
            JsonOutput.toJson([greeeting: greeterService.greet('Spark')])
        })
    }
}
