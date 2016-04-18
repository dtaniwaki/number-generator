package com.github.dtaniwaki.number_generator.Observer

import com.github.dtaniwaki.number_generator.Storage
import spray.json._

class StorageUpdater(storage: Storage.Base) extends Base with DefaultJsonProtocol {
  def observe(time: Long, counts: Map[String, Int]): Unit = {
    storage.update(counts)
  }
}
