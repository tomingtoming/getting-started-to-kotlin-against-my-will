package jpasample

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import javax.persistence.*

@Repository
interface SupplierRepository : JpaRepository<Suppliers, Int>

@Entity
@Table(name = "SUPPLIERS")
data class Suppliers(
        @Id
        @Column(name = "SUP_ID", nullable = false)
        var id: Int,

        @Column(name = "SUP_NAME", nullable = false)
        var name: String,

        @Column(name = "STREET", nullable = false)
        var street: String,

        @Column(name = "CITY", nullable = false)
        var city: String,

        @Column(name = "STATE", nullable = false)
        var state: String,

        @Column(name = "ZIP", nullable = false)
        var zip: String,

        @OneToMany
        var coffees: MutableList<Coffees>
)
