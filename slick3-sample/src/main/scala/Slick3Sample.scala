import slick.jdbc.H2Profile
import slick.jdbc.H2Profile.api._

import scala.concurrent.Await
import scala.concurrent.duration.Duration

object Slick3Sample extends ComponentAggregator(H2Profile) {
  def main(args: Array[String]): Unit = {
    import scala.concurrent.ExecutionContext.Implicits.global
    val db = Database.forConfig("h2mem")

    val future = for {
      _ <- createSchema
      _ <- suppliers += (150, "The High Ground", "100 Coffee Lane", "Meadows", "CA", "93966")
      _ <- coffees += ("Espresso", 150, 9.99, 0, 0)
      message <- coffees.filter(_.name === "Espresso").map(_.price).result.headOption map {
        case Some(price) => s"Coffee price is $price"
        case None => "No coffee found!"
      }
    } yield println(message)

    Await.ready(db.run(future), Duration.Inf)
  }
}
