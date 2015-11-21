package no.g_v.timesheet

import no.g_v.timesheet.service.GreeterService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RestController

@RestController
class HelloController {

    @Autowired
    private GreeterService greeterService

    @RequestMapping(value = '/greeting', method = RequestMethod.GET)
    Map<String, String> hello() {
        [greeting: greeterService.greet('Spring controller')]
    }
}