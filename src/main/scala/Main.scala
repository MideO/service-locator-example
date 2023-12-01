import com.github.mideo.example.ServiceLocator
import com.github.mideo.example.services.Service

object Main extends App {
  val locator = new ServiceLocator[Service]("com.github.mideo.example.services")

  println(locator.get("asterix").run("Asterix"))
  println(locator.get("backtick").run("Backtick"))

}