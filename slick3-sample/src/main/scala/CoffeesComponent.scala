import slick.jdbc.JdbcProfile

trait CoffeesComponent {
  self: SuppliersComponent =>

  val jdbcProfile: JdbcProfile
  import jdbcProfile.api._

  val coffees = TableQuery[Coffees]

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
