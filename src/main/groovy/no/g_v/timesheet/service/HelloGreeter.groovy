package no.g_v.timesheet.service

import org.springframework.stereotype.Service

@Service
class HelloGreeter implements GreeterService {

    @Override
    String greet(String name) {
        "Hello from ${name}!"
    }
}
