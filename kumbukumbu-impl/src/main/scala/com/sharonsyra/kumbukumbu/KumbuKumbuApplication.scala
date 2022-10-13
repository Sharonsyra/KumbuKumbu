package com.sharonsyra.kumbukumbu

import com.lightbend.lagom.scaladsl.api.{Descriptor, ServiceLocator}
import com.lightbend.lagom.scaladsl.devmode.LagomDevModeComponents
import com.lightbend.lagom.scaladsl.server.{
  LagomApplication,
  LagomApplicationContext,
  LagomApplicationLoader,
  LagomServer
}
import com.sharonsyra.kumbukumbu.api.KumbuKumbuService
import com.softwaremill.macwire.wire
import play.api.libs.ws.ahc.AhcWSComponents

abstract class KumbuKumbuApplication(context: LagomApplicationContext)
    extends LagomApplication(context)
    with AhcWSComponents {

  override lazy val lagomServer: LagomServer = {
    serverFor[KumbuKumbuService](wire[KumbuKumbuServiceImpl])
  }
}

class KumbuKumbuApplicationLoader extends LagomApplicationLoader {
  override def loadDevMode(context: LagomApplicationContext) =
    new KumbuKumbuApplication(context) with LagomDevModeComponents

  override def load(context: LagomApplicationContext): KumbuKumbuApplication =
    new KumbuKumbuApplication(context) {
      override def serviceLocator: ServiceLocator =
        ServiceLocator.NoServiceLocator
    }

  override def describeService: Option[Descriptor] = Some(
    readDescriptor[KumbuKumbuService])
}
