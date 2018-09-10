import slick.jdbc.H2Profile
import slick.jdbc.H2Profile.api._

import scala.concurrent.Await
import scala.concurrent.duration.Duration

object Slick3Sample extends ComponentAggregator(H2Profile) {
  def main(args: Array[String]): Unit = {
    import scala.concurrent.ExecutionContext.Implicits.global
    val db = Database.forConfig("h2mem")

    val dbio = for {
      _ <- createSchema
      _ <- suppliers += (150, "The High Ground", "100 Coffee Lane", "Meadows", "CA", "93966")
      _ <- coffees += ("Espresso", 150, 9.99, 0, 0)
      message <- coffees.filter(_.name === "Espresso").map(_.price).result.headOption
    } yield println(message)

    Await.ready(db.run(dbio), Duration.Inf)
  }
}
