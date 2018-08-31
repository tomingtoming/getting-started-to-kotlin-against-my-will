import slick.jdbc.JdbcProfile

import scala.concurrent.ExecutionContext

class ComponentAggregator(val jdbcProfile: JdbcProfile) extends CoffeesComponent with SuppliersComponent {

  import jdbcProfile.api._

  def createSchema = {
    (suppliers.schema ++ coffees.schema).create
  }

  def select(implicit ex: ExecutionContext): DBIOAction[String, NoStream, Effect.Read] = {
    coffees.filter(_.name === "Espresso").map(_.price).result.headOption map {
      case Some(price) => s"Coffee price is $price"
      case None => "No coffee found!"
    }
  }
}
