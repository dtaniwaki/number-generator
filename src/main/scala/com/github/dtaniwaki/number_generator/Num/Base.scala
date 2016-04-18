package com.github.dtaniwaki.number_generator.Num

import scala.concurrent.Future

abstract class Base {
  def generate(): Future[Map[String, Int]]
}
