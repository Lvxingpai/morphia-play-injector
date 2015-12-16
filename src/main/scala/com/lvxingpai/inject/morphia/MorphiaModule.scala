package com.lvxingpai.inject.morphia

import play.api.inject.{ Binding, Module }
import play.api.{ Configuration, Environment }

/**
 * Created by zephyre on 10/30/15.
 */
class MorphiaModule extends Module {
  override def bindings(environment: Environment, configuration: Configuration): Seq[Binding[_]] = {
    Seq(bind[MorphiaMap] to new MorphiaProvider)
  }
}
