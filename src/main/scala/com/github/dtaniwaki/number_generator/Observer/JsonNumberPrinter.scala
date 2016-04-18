package com.github.dtaniwaki.number_generator.Observer

import spray.json._

class JsonNumberPrinter extends Base with DefaultJsonProtocol {
  def observe(time: Long, counts: Map[String, Int]): Unit = {
    println(JsObject("time" -> JsNumber(time), "counts" -> counts.toJson).toString)
  }
}
