import com.github.masahitojp.app._
import org.scalatra.LifeCycle
import javax.servlet.ServletContext

/**
 * This is the Scalatra bootstrap file. You can use it to mount servlets or
 * filters. It's also a good place to put initialization code which needs to
 * run at application start (e.g. database configurations), and init params.
 */
class Scalatra extends LifeCycle {
  override def init(context: ServletContext) {

    // Mount one or more servlets
    context.mount(new MyScalatraServlet, "/*")

    // Set up init params
    // org.scalatra.cors.allowedOrigins = "http://example.com"
  }
}
