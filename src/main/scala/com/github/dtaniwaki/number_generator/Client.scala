package com.github.dtaniwaki.number_generator

import com.typesafe.scalalogging.StrictLogging

import java.net.{InetSocketAddress, Socket, InetAddress}
import scala.io.BufferedSource
import spray.json._
import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global

class Client(host: String, port: Int) extends DefaultJsonProtocol with StrictLogging {
  logger.info(s"Connecting ${host}:${port}...")

  def request(): Future[Map[String, Int]] = {
    Future {
      val socket = new Socket()
      try {
        socket.connect(new InetSocketAddress(InetAddress.getByName(host), port), 10)
        val body = new BufferedSource(socket.getInputStream()).getLines().mkString
        logger.debug(s"Retrieve: ${body}")
        body.parseJson.convertTo[Map[String, Int]]
      } finally {
        socket.close()
      }
    }
  }
}
