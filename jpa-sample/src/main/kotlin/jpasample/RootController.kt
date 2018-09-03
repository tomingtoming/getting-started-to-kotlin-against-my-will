package jpasample

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class RootController(
        private val coffeeRepository: CoffeeRepository
) {
    @GetMapping("/")
    fun getRoot(): String {
        return "[ " + coffeeRepository.findAll().map { coffee ->
            coffee.name
        }.joinToString(", ") + " ]"
    }
}
