package jpasample

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import javax.persistence.*

@Repository
interface CoffeeRepository : JpaRepository<Coffees, String>

@Entity
@Table(name = "COFFEES")
data class Coffees(
        @Id
        @Column(name = "COF_NAME", nullable = false)
        var name: String,

        @ManyToOne
        @JoinColumn(name = "SUP_ID", nullable = false)
        var supplier: Suppliers,

        @Column(name = "PRICE", nullable = false)
        var price: Double,

        @Column(name = "SALES", nullable = false)
        var sales: Int,

        @Column(name = "TOTAL", nullable = false)
        var total: Int
)
