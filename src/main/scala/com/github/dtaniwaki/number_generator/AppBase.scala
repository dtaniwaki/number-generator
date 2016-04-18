package com.github.dtaniwaki.number_generator

import com.typesafe.scalalogging.StrictLogging
import com.typesafe.config.{Config, ConfigFactory}
import net.ceedubs.ficus.Ficus._
import scala.concurrent.ExecutionContext.Implicits.global

trait AppBase extends App with StrictLogging {
  protected val config: Config = ConfigFactory.load()
  protected val factory: GeneratorFactory.Base
  val DefaultPort: Int
  val DefaultProcessTime = 1000

  protected val numbers = factory.getNumbers()
  protected val storage = factory.getStorage()
  protected val observers = factory.getObservers(storage)
  protected val handler = factory.getHandler(storage)
  protected val processor = new Processor(numbers, observers)

  private val port = config.as[Option[Int]]("app.port").getOrElse(DefaultPort)
  Server.run(port, handler).recover { case e: Throwable =>
    throw(e)
  }

  private val processTime = config.as[Option[Int]]("app.processTime").getOrElse(DefaultProcessTime)
  private val t = new java.util.Timer()
  private val task = new java.util.TimerTask {
    def run() = {
      try {
        processor.process()
      } catch {
        case e: Throwable =>
          logger.error(e.getMessage)
      }
    }
  }
  t.schedule(task, processTime, processTime)
}
