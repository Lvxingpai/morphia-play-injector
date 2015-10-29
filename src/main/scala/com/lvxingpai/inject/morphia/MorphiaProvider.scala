package com.lvxingpai.inject.morphia

import javax.inject.Provider

import com.lvxingpai.database.MorphiaFactoryImpl
import org.mongodb.morphia.Datastore
import play.api.Configuration

/**
 * Created by zephyre on 10/30/15.
 */
class MorphiaProvider(name: String, configuration: Configuration) extends Provider[Datastore] {
  lazy val get: Datastore = {
    val mongoConfig = configuration getConfig s"mongo.$name" getOrElse Configuration.empty

    val database = mongoConfig getString "database" getOrElse "local"
    val host = mongoConfig getString "host" getOrElse "localhost"
    val port = mongoConfig getInt "port" getOrElse 27017
    val user = mongoConfig getString "user"
    val password = mongoConfig getString "password"
    val adminSource = mongoConfig getString "adminSource"
    val validation = mongoConfig getBoolean "validation" getOrElse false

    MorphiaFactoryImpl.newInstance(host, port, database, adminSource, user, password, validation = validation)
  }
}
