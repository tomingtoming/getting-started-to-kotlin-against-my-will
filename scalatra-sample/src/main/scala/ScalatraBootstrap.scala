import javax.servlet.ServletContext
import org.scalatra._


class ScalatraBootstrap extends LifeCycle {
  override def init(context: ServletContext) {
    context.mount(new SampleScalatraServlet, "/*")
  }
}

class SampleScalatraServlet extends ScalatraServlet {
  get("/hello/:name") {
    s"Hello, ${params("name")}"
  }
}
