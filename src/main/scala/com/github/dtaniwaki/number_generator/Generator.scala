package com.github.dtaniwaki.number_generator

import com.typesafe.config.{Config, ConfigFactory}

object Generator extends {
  override val config: Config = ConfigFactory.load()
  override val factory: GeneratorFactory.Base = new GeneratorFactory.Generator(config)
  override val DefaultProcessTime = 10
  override val DefaultPort = 9991
} with AppBase
