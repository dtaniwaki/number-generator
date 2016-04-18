package com.github.dtaniwaki.number_generator.Observer

abstract class Base {
  def observe(time: Long, counts: Map[String, Int]): Unit
}
