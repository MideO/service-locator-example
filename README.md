A simple service locator pattern with all defined services detected via reflection

# Usage:
See [Main.scala](src%2Fmain%2Fscala%2FMain.scala)

```scala 
// Define an abstract service

abstract class Service {
  def run(string: String): String
}

// Implement the service 
package com.github.mideo.example

class TestService extends Service {
  override def run(string: String): String = s"test $string"
}

// Create a locator see implementation in src/main/scala/com/github/mideo/example/ServiceLocator.scala 
val locator = new ServiceLocator[Service]("com.github.mideo.example")

// execute
locator.get("test").run("test") should be("test test")

// execute with dsl
implicit val locator = new ServiceLocator[Service]("com.github.mideo.example")
import com.github.mideo.example.ServiceLocator.ServiceLocatorSyntax._

"test".service.run("test") should be("test test")


//NB:  All new implementations of Service will be registered on initialisation of the ServiceLocator 
```
