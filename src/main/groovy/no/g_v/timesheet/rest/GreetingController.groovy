package no.g_v.timesheet.rest

import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping(value = '/api/greeting')
class GreetingController {

    @RequestMapping(method = RequestMethod.GET)
    String greeting() {
        'Hello Groovy'
    }
}
