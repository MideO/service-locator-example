package com.github.mideo.example.services
import org.scalatest._
import flatspec._
import matchers._

class AsterixServiceTest extends AnyFlatSpec with should.Matchers {

  behavior of "AsterixService"

  it should "run" in {
    new AsterixService().run("text") should be ("*text*")
  }

}
