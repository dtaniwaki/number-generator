package com.github.dtaniwaki.number_generator

import java.io.PrintStream
import java.net.ServerSocket

import com.typesafe.scalalogging.StrictLogging

import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global

class Server(port: Int, handler: RequestHandler) extends Runnable with StrictLogging {
  val server = new ServerSocket(port)
  logger.info(s"Waiting for requests on ${port}")

  def run: Unit = {
    while (true) {
      val s = server.accept()
      val out = new PrintStream(s.getOutputStream())
      out.println(handler.handleRequest())
      out.flush()
      s.close()
    }
  }
}

object Server {
  def run(port: Int, handler: RequestHandler): Future[Unit] = {
    Future {
      new Server(port, handler).run()
    }
  }
}
