package com.github.dtaniwaki.number_generator.Storage

import com.typesafe.config.Config

import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global

abstract class Base(config: Config) {
  def update(counts: Map[String, Int]): Future[Unit]
  def get(key: String): Future[Int]
  def getAll(): Future[Map[String, Int]]
  def purge(): Future[Map[String, Int]]
  def update(counts: Base): Future[Unit] = {
    counts.getAll().flatMap(update(_))
  }
}
