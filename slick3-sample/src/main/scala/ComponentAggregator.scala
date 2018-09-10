import slick.jdbc.JdbcProfile

class ComponentAggregator(val jdbcProfile: JdbcProfile)
  extends CoffeesComponent
    with SuppliersComponent {

  import jdbcProfile.api._

  def createSchema = {
    (suppliers.schema ++ coffees.schema).create
  }
}
