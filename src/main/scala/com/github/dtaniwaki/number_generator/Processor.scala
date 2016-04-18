package com.github.dtaniwaki.number_generator

import java.time.ZonedDateTime

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

class Processor(numbers: List[Num.Base], observers: List[Observer.Base]) {
  def process(): Unit = {
    val t = ZonedDateTime.now.toEpochSecond
    Future.sequence(numbers.map { n =>
      n.generate().recover {
        case e: Throwable => Map[String, Int]()
      }
    }).map { counts =>
      val mergedCounts = counts.map(_.keys).flatten.distinct.map { key =>
        (key, counts.map(_.getOrElse(key, 0)).reduceRight { (a, b) =>
          a + b
        })
      }.toMap
      observers.foreach(_.observe(t, mergedCounts))
    }.recover {
      case e: Throwable => throw e
    }
  }
}
