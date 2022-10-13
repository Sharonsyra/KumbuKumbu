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

  /** Create a KumbuKumbu
    *
    * @return
    *   KumbuKumbu created
    */
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

  /** Retrieve KumbuKumbu by uuid
    *
    * @param kumbuKumbuUuid
    *   uuid
    * @return
    *   KumbuKumbu
    */
  override def getKumbuKumbu(
    kumbuKumbuUuid: UUID): ServiceCall[NotUsed, KumbuKumbu] =
    ServiceCall { _ =>
      val kumbuKumbuResult = kumbuKumbus
        .find(kumbu => kumbu.kumbuKumbuUuid.equals(kumbuKumbuUuid)) match {
        case Some(kumbuKumbu) => kumbuKumbu
        case None =>
          throw new Exception(s"KumbuKumbu with id $kumbuKumbuUuid not found")
      }

      Future.successful(
        kumbuKumbuResult
      )

    }

  /** Retrieve KumbuKumbu by title
    *
    * @param kumbuKumbuTitle
    *   title
    * @return
    *   KumbuKumbu
    */
  override def searchKumbuKumbu(
    kumbuKumbuTitle: String): ServiceCall[NotUsed, KumbuKumbu] =
    ServiceCall { _ =>
      val kumbuKumbuResult = kumbuKumbus
        .find(kumbu => kumbu.kumbuKumbuTitle.equals(kumbuKumbuTitle)) match {
        case Some(kumbuKumbu) => kumbuKumbu
        case None =>
          throw new Exception(
            s"KumbuKumbu with title $kumbuKumbuTitle not found")
      }

      Future.successful(
        kumbuKumbuResult
      )

    }

  /** Update KumbuKumbu
    *
    * @param kumbuKumbuUuid
    *   uuid
    * @return
    *   KumbuKumbu updated
    */
  override def updateKumbuKumbu(
    kumbuKumbuUuid: UUID): ServiceCall[UpdateKumbuKumbu, KumbuKumbu] =
    ServiceCall { request =>
      val timeNow = Instant.now()

      val kumbuKumbuToUpdate: KumbuKumbu = kumbuKumbus
        .find(kumbu => kumbu.kumbuKumbuUuid.equals(kumbuKumbuUuid)) match {
        case Some(kumbuKumbu) => kumbuKumbu
        case None =>
          throw new Exception(s"KumbuKumbu with id $kumbuKumbuUuid not found")
      }

      val updatedKumbuKumbu: KumbuKumbu = kumbuKumbuToUpdate.copy(
        kumbuKumbuTitle = request.kumbuKumbuTitle match {
          case Some(value) => value
          case None        => kumbuKumbuToUpdate.kumbuKumbuTitle
        },
        kumbuKumbuContent = request.kumbuKumbuContent match {
          case Some(value) => Some(value)
          case None        => kumbuKumbuToUpdate.kumbuKumbuContent
        },
        updatedAt = Some(
          TimeStamp(seconds = timeNow.getEpochSecond, nanos = timeNow.getNano))
      )

      // Remove old kumbukumbu
      kumbuKumbus -= kumbuKumbuToUpdate

      // Update with the new kumbukumbu
      kumbuKumbus += updatedKumbuKumbu

      Future.successful(
        updatedKumbuKumbu
      )

    }

  /** Delete KumbuKumbu
    *
    * @param kumbuKumbuUuid
    *   uuid
    * @return
    *   KumbuKumbu deleted
    */
  override def deleteKumbuKumbu(
    kumbuKumbuUuid: UUID): ServiceCall[NotUsed, KumbuKumbu] =
    ServiceCall { _ =>
      val kumbuKumbuToDelete: KumbuKumbu = kumbuKumbus
        .find(kumbu => kumbu.kumbuKumbuUuid.equals(kumbuKumbuUuid)) match {
        case Some(kumbuKumbu) => kumbuKumbu
        case None =>
          throw new Exception(s"KumbuKumbu with id $kumbuKumbuUuid not found")
      }

      kumbuKumbus -= kumbuKumbuToDelete

      Future.successful(
        kumbuKumbuToDelete
      )

    }

  /** List KumbuKumbus
    *
    * @return
    *   List KumbuKumbus
    */
  override def listKumbuKumbus: ServiceCall[NotUsed, ListKumbuKumbus] =
    ServiceCall { _ =>
      Future.successful(
        ListKumbuKumbus(kumbuKumbus)
      )

    }

  /** Next KumbuKumbu
    *
    * @param kumbuKumbuUuid
    *   uuid
    * @return
    *   KumbuKumbu
    */
  override def nextKumbuKumbu(
    kumbuKumbuUuid: UUID): ServiceCall[NotUsed, KumbuKumbu] =
    ServiceCall { _ =>
      val kumbuKumbuResult: KumbuKumbu = kumbuKumbus
        .find(kumbu => kumbu.kumbuKumbuUuid.equals(kumbuKumbuUuid)) match {
        case Some(kumbuKumbu) => kumbuKumbu
        case None =>
          throw new Exception(s"KumbuKumbu with id $kumbuKumbuUuid not found")
      }

      Future.successful(
        kumbuKumbuResult
      )

    }

}
