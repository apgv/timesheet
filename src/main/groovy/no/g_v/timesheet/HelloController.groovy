package no.g_v.timesheet

import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RestController

@RestController
class HelloController {

    @RequestMapping(value = '/greeting', method = RequestMethod.GET)
    Map<String, String> hello() {
        [greeting: 'Hello from Spring']
    }
}