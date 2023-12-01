package com.github.mideo.example.services

class BackTickService extends Service {
  override def run(string: String): String = s"`$string`"
}
