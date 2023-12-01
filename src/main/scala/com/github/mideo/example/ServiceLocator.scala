package com.github.mideo.example

import org.reflections.Reflections
import org.reflections.scanners.Scanners

import scala.annotation.tailrec
import scala.jdk.CollectionConverters.CollectionHasAsScala
import scala.reflect.{ClassTag, classTag}


class ServiceLocator[T: ClassTag](packageName: String) {
  private val cache: Map[String, T] = new Reflections(packageName, Scanners.SubTypes)
    .getSubTypesOf(classTag[T].runtimeClass)
    .asScala
    .map(it => {
      val lookUp = it.getSimpleName.toLowerCase.replace("service", "")
      val instance = it.getDeclaredConstructor().newInstance().asInstanceOf[T]
      lookUp -> instance
    })
    .toMap

  def get(lookUpName: String): T = cache(lookUpName)
}

object ServiceLocator {
  object ServiceLocatorSyntax {
    implicit class extensionString[T](lookUp: String) {
      def service(implicit locator: ServiceLocator[T]): T = locator.get(lookUp)

    }

    implicit class extension[T](t: T) {
      def and(other: String*)(implicit locator: ServiceLocator[T]): Seq[T] = t +: other.map(locator.get)
    }

    implicit class extensionSeq[T](seq: Seq[T]) {
      @tailrec
      final def compile[K](f: (T, K) => K, arg: K): K = seq match {
        case Nil => arg
        case x => x.tail.compile(f, f(x.head, arg))
      }
    }
  }
}