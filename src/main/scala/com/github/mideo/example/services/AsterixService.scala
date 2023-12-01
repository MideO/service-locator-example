package com.github.mideo.example.services

class AsterixService extends Service {
  override def run(string: String): String = s"*$string*"
}
