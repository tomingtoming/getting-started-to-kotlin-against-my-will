package jpasample

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class RootController(
        private val supplierRepository: SupplierRepository,
        private val coffeeRepository: CoffeeRepository
) {
    @GetMapping("/")
    fun getRoot(): String {
        val supplier = supplierRepository.save(Suppliers(150, "The High Ground", "100 Coffee Lane", "Meadows", "CA", "93966"))
        coffeeRepository.save(Coffees("Espresso", supplier, 9.99, 0, 0))
        return coffeeRepository.findByName("Espresso").map { it.price }.toString()
    }
}
