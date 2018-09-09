import org.scalatra.ScalatraServlet

class SampleScalatraServlet extends ScalatraServlet {
  get("/hello/:name") {
    s"Hello, ${params("name")}"
  }
}
