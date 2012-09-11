package com.github.masahitojp.app

import org.scalatra._
import scalate.ScalateSupport
import liftjson.LiftJsonSupport

import net.liftweb.json._

case class Person(id: Long, name: String, age: Int)

class MyScalatraServlet extends ScalatraServlet with ScalateSupport with LiftJsonSupport {

  get("/") {
    <html>
      <body>
        <h1>Hello, world!</h1>
        Say <a href="hello-scalate">hello to Scalate</a>.
      </body>
    </html>
  }

  get("/json") {
    Extraction.decompose(
      Person(1, "ぱみゅぱみゅ", 19)
    )
  }
  post("/json") {
    parsedBody match {
      case JNothing ⇒ halt(400, "invalid json")
      case json: JObject ⇒ {
        //(json \ "name").extract[String]

        val pamyu: Person = json.extract[Person]
        pamyu.name
      }
      case _ ⇒ halt(400, "unknown json")
    }
  }
  notFound {
    // remove content type in case it was set through an action
    contentType = null 
    // Try to render a ScalateTemplate if no route matched
    findTemplate(requestPath) map { path =>
      contentType = "text/html"
      layoutTemplate(path)
    } orElse serveStaticResource() getOrElse resourceNotFound() 
  }
}
