package jpasample

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*
import javax.persistence.*

@Repository
interface CoffeeRepository : JpaRepository<Coffees, String> {
    fun findByName(name: String): Optional<Coffees>
}

@Entity
@Table(name = "COFFEES")
data class Coffees(
        @Id
        @Column(name = "COF_NAME", nullable = false)
        var name: String = "",

        @ManyToOne
        @JoinColumn(name = "SUP_ID", nullable = false)
        var supplier: Suppliers = Suppliers(),

        @Column(name = "PRICE", nullable = false)
        var price: Double = 0.0,

        @Column(name = "SALES", nullable = false)
        var sales: Int = 0,

        @Column(name = "TOTAL", nullable = false)
        var total: Int = 0
)
