import org.scalatest._

class FizzBuzzTest extends FlatSpec with Matchers {
  val fizzBuzz = new FizzBuzz
  "Any number" should "be expressed as number string" in {
    fizzBuzz.exec(1) should be ("1")
    fizzBuzz.exec(2) should be ("2")
  }
  "Any number divisible by three" should "be replaced by the word fizz" in {
    fizzBuzz.exec(3) should be ("fizz")
    fizzBuzz.exec(6) should be ("fizz")
  }
  "Any number divisible by five" should "be replaced by the word buzz" in {
    fizzBuzz.exec(5) should be ("buzz")
    fizzBuzz.exec(10) should be ("buzz")
  }
  "Any number divisible by both of three and five" should "be replaced by the fizz buzz" in {
    fizzBuzz.exec(15) should be ("fizz buzz")
    fizzBuzz.exec(30) should be ("fizz buzz")
  }
}
