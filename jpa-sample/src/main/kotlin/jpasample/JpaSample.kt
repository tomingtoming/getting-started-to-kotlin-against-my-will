package jpasample

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication

@SpringBootApplication
class JpaSample(
        private val supplierRepository: SupplierRepository,
        private val coffeeRepository: CoffeeRepository
) {
    fun run() {
        val supplier = supplierRepository.save(Suppliers(150, "The High Ground", "100 Coffee Lane", "Meadows", "CA", "93966"))
        coffeeRepository.save(Coffees("Espresso", supplier, 9.99, 0, 0))
        println(coffeeRepository.findByName("Espresso").map { it.price })
    }
}

fun main(args: Array<String>) {
    SpringApplication.run(JpaSample::class.java, *args).getBean(JpaSample::class.java).run()
}
