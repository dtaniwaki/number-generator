package com.github.dtaniwaki.number_generator.GeneratorFactory

import com.github.dtaniwaki.number_generator.Num
import com.typesafe.config.Config
import net.ceedubs.ficus.Ficus._

class Generator(config: Config) extends Base(config) {
  val maxNumber = config.as[Option[Int]]("generator.maxNumber").getOrElse(10)
  val threadNumber = config.as[Option[Int]]("generator.threadNumber").getOrElse(10)

  override def getNumbers(): List[Num.Base] = {
    (1 to threadNumber).map { idx =>
      new Num.Random(maxNumber, idx)
    }.toList
  }
}
