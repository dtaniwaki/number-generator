package com.github.dtaniwaki.number_generator.GeneratorFactory

import com.github.dtaniwaki.number_generator.{Num, Storage, Observer, Handler}
import com.typesafe.config.Config

abstract class Base(config: Config) {
  def getNumbers(): List[Num.Base]
  def getStorage(): Storage.Base = new Storage.Memory(config)
  def getObservers(storage: Storage.Base): List[Observer.Base] = List(new Observer.StorageUpdater(storage))
  def getHandler(storage: Storage.Base): Handler.Base = new Handler.Base(storage)
}
