package com.github.dtaniwaki.number_generator

import com.typesafe.scalalogging.StrictLogging

import scala.concurrent.Await
import scala.concurrent.duration._
import spray.json._

class RequestHandler(storage: Storage.Base) extends DefaultJsonProtocol with StrictLogging {
  def handleRequest(): String = {
    val s = Await.result(storage.purge(), 1 seconds).toJson.toString
    logger.debug(s"Response: ${s}")
    s
  }
}
