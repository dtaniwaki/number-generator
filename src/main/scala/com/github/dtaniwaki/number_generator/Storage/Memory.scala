package com.github.dtaniwaki.number_generator.Storage

import com.typesafe.config.Config
import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global

class Memory(config: Config) extends Base(config) {
  private val lock = new Object()
  val numbers = scala.collection.mutable.Map[String, Int]()

  override def update(counts: Map[String, Int]): Future[Unit] = {
    Future {
      lock.synchronized {
        counts.foreach { case (key: String, cnt: Int) =>
          numbers(key) = numbers.getOrElse(key, 0) + cnt
        }
      }
    }
  }

  override def get(key: String): Future[Int] = {
    Future {
      lock.synchronized {
        numbers(key)
      }
    }
  }

  override def getAll(): Future[Map[String, Int]] = {
    Future {
      lock.synchronized {
        numbers.toMap
      }
    }
  }

  override def purge(): Future[Map[String, Int]] = {
    Future {
      lock.synchronized {
        val m = numbers.toMap
        numbers.clear()
        m
      }
    }
  }
}
