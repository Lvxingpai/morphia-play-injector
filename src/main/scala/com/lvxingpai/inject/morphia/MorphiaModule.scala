package com.lvxingpai.inject.morphia

import org.mongodb.morphia.Datastore
import play.api.inject.{Binding, Module}
import play.api.{Configuration, Environment}

/**
 * Created by zephyre on 10/30/15.
 */
class MorphiaModule extends Module {
  override def bindings(environment: Environment, configuration: Configuration): Seq[Binding[_]] = {
    val mongoConfig = configuration getConfig "mongo" getOrElse Configuration.empty

    mongoConfig.subKeys.toSeq map (key => {
      bind[Datastore] qualifiedWith key to new MorphiaProvider(key, configuration)
    })
  }
}
