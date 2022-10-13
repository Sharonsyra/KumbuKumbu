package com.sharonsyra.kumbukumbu

import akka.NotUsed
import com.lightbend.lagom.scaladsl.api.ServiceCall
import com.sharonsyra.common._
import com.sharonsyra.kumbukumbu.api.KumbuKumbuService

import java.time.Instant
import java.util.UUID
import scala.collection.mutable.ArrayBuffer
import scala.concurrent.Future

class KumbuKumbuServiceImpl extends KumbuKumbuService {

  val kumbuKumbus: ArrayBuffer[KumbuKumbu] = ArrayBuffer[KumbuKumbu]()

  override def createKumbuKumbu: ServiceCall[CreateKumbuKumbu, KumbuKumbu] =
    ServiceCall { request =>
      val kumbuKumbuUuid = UUID.randomUUID()
      val timeNow = Instant.now()

      val newKumbuKumbu: KumbuKumbu =
        KumbuKumbu(
          kumbuKumbuUuid = kumbuKumbuUuid,
          kumbuKumbuTitle = request.kumbuKumbuTitle,
          kumbuKumbuContent = request.kumbuKumbuContent,
          createdAt = TimeStamp(
            seconds = timeNow.getEpochSecond,
            nanos = timeNow.getNano),
          updatedAt = None
        )

      kumbuKumbus.+=(newKumbuKumbu)

      println(s"Kumbukumbus are -> $kumbuKumbus")

      Future.successful(
        newKumbuKumbu
      )
    }

  override def getKumbuKumbu(
    kumbuKumbuUuid: UUID): ServiceCall[NotUsed, KumbuKumbu] = ???

  override def searchKumbuKumbu(
    kumbuKumbuTitle: String): ServiceCall[NotUsed, KumbuKumbu] = ???

  override def updateKumbuKumbu(
    kumbuKumbuUuid: UUID): ServiceCall[UpdateKumbuKumbu, KumbuKumbu] = ???

  override def deleteKumbuKumbu(
    kumbuKumbuUuid: UUID): ServiceCall[DeleteKumbuKumbu, KumbuKumbu] = ???

  override def listKumbuKumbus: ServiceCall[NotUsed, ListKumbuKumbus] = ???

  override def nextKumbuKumbu(
    kumbukumbuUuid: UUID): ServiceCall[NotUsed, KumbuKumbu] = ???
}
