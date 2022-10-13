package com.sharonsyra.kumbukumbu

import akka.NotUsed
import com.lightbend.lagom.scaladsl.api.ServiceCall
import com.sharonsyra.common.{
  CreateKumbuKumbu,
  DeleteKumbuKumbu,
  KumbuKumbu,
  ListKumbuKumbus,
  TimeStamp,
  UpdateKumbuKumbu
}
import com.sharonsyra.kumbukumbu.api.KumbuKumbuService

import java.time.Instant
import java.util.UUID
import scala.concurrent.{ExecutionContext, Future}

class KumbuKumbuServiceImpl
//(implicit ec: ExecutionContext)
    extends KumbuKumbuService {
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

      println(s"New KumbuKumbu Created: Details -> $newKumbuKumbu")

      Future.successful(
        newKumbuKumbu
      )
    }

//  override def getKumbuKumbu(
//    kumbuKumbuUuid: UUID): ServiceCall[NotUsed, KumbuKumbu] = ???
//
//  override def searchKumbuKumbu(
//    kumbuKumbuTitle: String): ServiceCall[NotUsed, KumbuKumbu] = ???
//
//  override def updateKumbuKumbu(
//    kumbuKumbuUuid: UUID): ServiceCall[UpdateKumbuKumbu, KumbuKumbu] = ???
//
//  override def deleteKumbuKumbu(
//    kumbuKumbuUuid: UUID): ServiceCall[DeleteKumbuKumbu, KumbuKumbu] = ???
//
//  override def listKumbuKumbus: ServiceCall[NotUsed, ListKumbuKumbus] = ???
//
//  override def nextKumbuKumbu(
//    kumbukumbuUuid: UUID): ServiceCall[NotUsed, KumbuKumbu] = ???
}
