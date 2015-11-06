package com.lvxingpai.inject.morphia

import javax.inject.{ Inject, Provider, Singleton }

import com.lvxingpai.database.MorphiaFactoryImpl
import play.api.Configuration
import play.api.inject.{ BindingKey, Injector }

/**
 * Created by zephyre on 10/30/15.
 */
@Singleton
class MorphiaProvider extends Provider[MorphiaMap] {

  @Inject private var injector: Injector = _

  lazy val get = {
    // 获得默认的Configuration, 然后找到包含mongo设置的Configuration
    val confGlobal = injector instanceOf BindingKey(classOf[Configuration])
    // 如果未指定morphia_play.configuration, 则在默认的Configuration中查找key
    val bindingKey = (for {
      keyName <- confGlobal getString "morphiaPlay.configuration.key"
    } yield {
      BindingKey(classOf[Configuration]) qualifiedWith keyName
    }) getOrElse BindingKey(classOf[Configuration])

    val conf = injector instanceOf bindingKey

    // 根据"mongo"路径下的内容, 获得Datastore对象
    val pairs = conf.getConfig("morphiaPlay.mongo") map (_.subKeys.toSeq) getOrElse Seq() map (name => {
      val mongoConfig = conf getConfig s"morphiaPlay.mongo.$name" getOrElse Configuration.empty

      val database = mongoConfig getString "database" getOrElse "local"
      val host = mongoConfig getString "host" getOrElse "localhost"
      val port = mongoConfig getInt "port" getOrElse 27017
      val user = mongoConfig getString "user"
      val password = mongoConfig getString "password"
      val adminSource = mongoConfig getString "adminSource"
      val validation = mongoConfig getBoolean "validation" getOrElse false

      val ds = MorphiaFactoryImpl.newInstance(host, port, database, adminSource, user, password, validation = validation)
      name -> ds
    })

    new MorphiaMap(Map(pairs: _*))
  }
}
