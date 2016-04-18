package com.github.dtaniwaki.number_generator

import com.typesafe.config.{Config, ConfigFactory}

object Aggregator extends {
  override val config: Config = ConfigFactory.load()
  override val factory: GeneratorFactory.Base = new GeneratorFactory.Aggregator(config)
  override val DefaultPort = 9990
} with AppBase

