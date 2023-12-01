import com.github.mideo.example.ServiceLocator
import com.github.mideo.example.ServiceLocator.ServiceLocatorSyntax._
import com.github.mideo.example.services.Service

object Main extends App {
  implicit val locator: ServiceLocator[Service] = new ServiceLocator[Service]("com.github.mideo.example.services")

  println(locator.get("asterix").run("Asterix"))
  println(locator.get("backtick").run("Backtick"))

  println("asterix"
    .service
    .and("backtick")
    .compile[String]((f,a) => f.run(a), "AsterixAndBacktick"))

}