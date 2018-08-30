import slick.jdbc.H2Profile.api._

import scala.concurrent.Await
import scala.concurrent.duration.Duration

object Slick3Sample {
  def main(args: Array[String]): Unit = {
    import scala.concurrent.ExecutionContext.Implicits.global
    val db = Database.forConfig("h2mem")
    val setup = DBIO.seq(
      (suppliers.schema ++ coffees.schema).create,
      suppliers ++= Seq(
        (101, "Acme, Inc.", "99 Market Street", "Groundsville", "CA", "95199"),
        (49, "Superior Coffee", "1 Party Place", "Mendocino", "CA", "95460"),
        (150, "The High Ground", "100 Coffee Lane", "Meadows", "CA", "93966")
      ),
      coffees ++= Seq(
        ("Colombian", 101, 7.99, 0, 0),
        ("French_Roast", 49, 8.99, 0, 0),
        ("Espresso", 150, 9.99, 0, 0),
        ("Colombian_Decaf", 101, 8.99, 0, 0),
        ("French_Roast_Decaf", 49, 9.99, 0, 0)
      )
    )
    val setupFuture = db.run(setup)
    Await.ready(setupFuture, Duration.Inf)

    val select = coffees.filter(_.name === "Espresso").map(_.price).result.headOption map {
      case Some(price) =>
        DBIO.successful(println(s"Coffee price is $price"))
      case None =>
        DBIO.failed(new Exception("No coffee found!"))
    }
    val selectFuture = db.run(select)
    Await.ready(selectFuture, Duration.Inf)
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

    // Every table needs a * projection with the same type as the table's type parameter
    def * = (id, name, street, city, state, zip)
  }

  // Definition of the COFFEES table
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
