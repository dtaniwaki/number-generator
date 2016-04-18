package com.github.dtaniwaki.number_generator.GeneratorFactory

import com.typesafe.config.Config
import net.ceedubs.ficus.Ficus._
import com.github.dtaniwaki.number_generator.{Num, Observer, Storage}

class Aggregator(config: Config) extends Base(config) {
  val hosts = config.as[Array[String]]("aggregator.hosts")

  override def getNumbers(): List[Num.Base] = {
    hosts.map { s =>
      val host = s.split(":")(0)
      val port = s.split(":")(1)
      new Num.External(host, Option(port).map(_.toInt).getOrElse(9999))
    }.toList
  }

  override def getObservers(storage: Storage.Base): List[Observer.Base] = super.getObservers(storage) ++ List(new Observer.JsonNumberPrinter)
}
