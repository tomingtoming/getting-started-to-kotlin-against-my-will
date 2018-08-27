import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class FizzBuzzTest {
    private val fizzBuzz = FizzBuzz()

    @Test
    fun anyNumberShouldBeExpressedAsNumberString() {
        assertEquals("1", fizzBuzz.exec(1))
        assertEquals("2", fizzBuzz.exec(2))
    }

    @Test
    fun anyNumberDivisibleByThreeShouldBeReplacedByTheWordFizz() {
        assertEquals("fizz", fizzBuzz.exec(3))
        assertEquals("fizz", fizzBuzz.exec(6))
    }

    @Test
    fun anyNumberDivisibleByFiveShouldBeReplacedByTheWordBuzz() {
        assertEquals("buzz", fizzBuzz.exec(5))
        assertEquals("buzz", fizzBuzz.exec(10))
    }

    @Test
    fun anyNumberDivisibleByBothOfThreeAndFiveShouldBeReplacedByTheFizzBuzz() {
        assertEquals("fizz buzz", fizzBuzz.exec(15))
        assertEquals("fizz buzz", fizzBuzz.exec(30))
    }
}
