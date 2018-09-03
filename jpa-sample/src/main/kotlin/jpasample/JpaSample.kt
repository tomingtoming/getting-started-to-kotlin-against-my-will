package jpasample

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class JpaSample

fun main(args: Array<String>) {
    runApplication<JpaSample>(*args)
}
