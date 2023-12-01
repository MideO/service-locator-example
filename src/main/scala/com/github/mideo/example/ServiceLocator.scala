package com.github.mideo.example

import org.reflections.Reflections
import org.reflections.scanners.Scanners

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