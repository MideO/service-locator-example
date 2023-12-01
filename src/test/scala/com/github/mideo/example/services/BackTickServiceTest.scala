package com.github.mideo.example.services

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should

class BackTickServiceTest extends AnyFlatSpec with should.Matchers {

  behavior of "BackTickServiceTest"

  it should "run" in {
    new BackTickService().run("text") should be ("`text`")
  }

}
