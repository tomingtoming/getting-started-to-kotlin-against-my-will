import slick.jdbc.H2Profile.api._

import scala.concurrent.duration.Duration
import scala.concurrent.{Await, ExecutionContext}

object Slick3Sample {
  def main(args: Array[String]): Unit = {
    import scala.concurrent.ExecutionContext.Implicits.global
    val db = Database.forConfig("h2mem")
    val future = db.run(schema >> insert >> select map println)
    Await.ready(future, Duration.Inf)
  }

  def schema() = DBIO.seq(
    (suppliers.schema ++ coffees.schema).create
  )

  def insert() = DBIO.seq(
    suppliers += (150, "The High Ground", "100 Coffee Lane", "Meadows", "CA", "93966"),
    coffees += ("Espresso", 150, 9.99, 0, 0)
  )

  def select(implicit ex: ExecutionContext) = {
    coffees.filter(_.name === "Espresso").map(_.price).result.headOption map {
      case Some(price) => s"Coffee price is $price"
      case None => "No coffee found!"
    }
  }

  val suppliers = TableQuery[Suppliers]
  val coffees = TableQuery[Coffees]

  class Suppliers(tag: Tag) extends Table[(Int, String, String, String, String, String)](tag, "SUPPLIERS") {
    def id = column[Int]("SUP_ID", O.PrimaryKey) // This is the primary key column
    def name = column[String]("SUP_NAME")
    def street = column[String]("STREET")
    def city = column[String]("CITY")
    def state = column[String]("STATE")
    def zip = column[String]("ZIP")
    def * = (id, name, street, city, state, zip)
  }

  class Coffees(tag: Tag) extends Table[(String, Int, Double, Int, Int)](tag, "COFFEES") {
    def name = column[String]("COF_NAME", O.PrimaryKey)
    def supID = column[Int]("SUP_ID")
    def price = column[Double]("PRICE")
    def sales = column[Int]("SALES")
    def total = column[Int]("TOTAL")
    def * = (name, supID, price, sales, total)
    def supplier = foreignKey("SUP_FK", supID, suppliers)(_.id)
  }

}
