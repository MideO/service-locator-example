package com.github.mideo.example


import com.github.mideo.example.services.Service
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should


class TestService extends Service {
  override def run(string: String): String = s"test $string"
}


class ServiceLocatorTest extends AnyFlatSpec with should.Matchers {

  behavior of "ServiceLocator"

  val locator = new ServiceLocator[Service]("com.github.mideo.example.services")

  it should "detect and add services in services package" in {
    locator.get("asterix").run("Asterix") should be("*Asterix*")
    locator.get("backtick").run("Backtick") should be("`Backtick`")
  }

  it should "not detect services outside of given package" in {
    an[NoSuchElementException] should be thrownBy locator.get("test")
  }

  it should "detect and add services in given package and nested packages" in {
    val locatorForTestService = new ServiceLocator[Service]("com.github.mideo.example")
    locatorForTestService.get("test").run("test") should be("test test")
    locatorForTestService.get("asterix").run("Asterix") should be("*Asterix*")
  }

  it should "enrich a given string with service" in {
    implicit val locatorForTestService: ServiceLocator[Service] = new ServiceLocator[Service]("com.github.mideo.example")
    import com.github.mideo.example.ServiceLocator.ServiceLocatorSyntax._


    "asterix".service.run("Asterix") should be("*Asterix*")
    "backtick".service.run("Backtick") should be("`Backtick`")

     "test"
      .service
      .and("asterix", "backtick")
      .compile[String]((f,a) => f.run(a), "string") should be("`*test string*`")

  }

}
