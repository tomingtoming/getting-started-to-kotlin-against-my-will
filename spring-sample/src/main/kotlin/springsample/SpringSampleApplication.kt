package springsample

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController

@SpringBootApplication
class SpringSampleApplication

fun main(args: Array<String>) {
    runApplication<SpringSampleApplication>(*args)
}

@RestController
class SpringSampleController {
    @GetMapping("/hello/{name}")
    fun hello(@PathVariable("name") name: String): String {
        return "Hello, $name"
    }
}
