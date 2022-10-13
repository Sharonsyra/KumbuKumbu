package com.sharonsyra.kumbukumbu.api

import akka.NotUsed
import com.lightbend.lagom.scaladsl.api.Service.{named, restCall}
import com.lightbend.lagom.scaladsl.api.transport.Method
import com.lightbend.lagom.scaladsl.api.{Descriptor, Service, ServiceCall}
import com.sharonsyra.common._

import java.util.UUID

trait KumbuKumbuService extends Service {

  /** Create a KumbuKumbu
    *
    * @return
    *   KumbuKumbu created
    */
  def createKumbuKumbu: ServiceCall[CreateKumbuKumbu, KumbuKumbu]

  /** Retrieve KumbuKumbu by uuid
    *
    * @param kumbuKumbuUuid
    *   uuid
    * @return
    *   KumbuKumbu
    */
  def getKumbuKumbu(
    kumbuKumbuUuid: UUID
  ): ServiceCall[NotUsed, KumbuKumbu]

  /** Retrieve KumbuKumbu by title
    *
    * @param kumbuKumbuTitle
    *   title
    * @return
    *   KumbuKumbu
    */
  def searchKumbuKumbu(
    kumbuKumbuTitle: String
  ): ServiceCall[NotUsed, KumbuKumbu]

  /** Update KumbuKumbu
    *
    * @param kumbuKumbuUuid
    *   uuid
    * @return
    *   KumbuKumbu updated
    */
  def updateKumbuKumbu(
    kumbuKumbuUuid: UUID
  ): ServiceCall[UpdateKumbuKumbu, KumbuKumbu]

  /** Delete KumbuKumbu
    *
    * @param kumbuKumbuUuid
    *   uuid
    * @return
    *   KumbuKumbu deleted
    */
  def deleteKumbuKumbu(
    kumbuKumbuUuid: UUID
  ): ServiceCall[NotUsed, KumbuKumbu]

  /** List KumbuKumbus
    *
    * @return
    *   List KumbuKumbus
    */
  def listKumbuKumbus: ServiceCall[NotUsed, ListKumbuKumbus]

  // Todo: Define next kumbuKumbu
  /** Next KumbuKumbu
    *
    * @param kumbukumbuUuid
    *   uuid
    * @return
    *   KumbuKumbu
    */
  def nextKumbuKumbu(kumbukumbuUuid: UUID): ServiceCall[NotUsed, KumbuKumbu]

  override def descriptor: Descriptor = {
    named("kumbuKumbu")
      .withCalls(
        restCall(
          method = Method.POST,
          pathPattern = "/api/kumbukumbus",
          scalaMethod = createKumbuKumbu _
        ),
        restCall(
          Method.GET,
          pathPattern = "/api/kumbukumbus/:kumbukumbuUuid",
          scalaMethod = getKumbuKumbu _
        ),
        restCall(
          Method.GET,
          pathPattern = "/api/kumbukumbus/search/:kumbukumbuTitle",
          scalaMethod = searchKumbuKumbu _
        ),
        restCall(
          Method.GET,
          pathPattern = "/api/kumbukumbus/:kumbukumbuUuid/next",
          scalaMethod = nextKumbuKumbu _
        ),
        restCall(
          Method.PATCH,
          pathPattern = "/api/kumbukumbus/:kumbukumbuUuid",
          scalaMethod = updateKumbuKumbu _
        ),
        restCall(
          Method.DELETE,
          pathPattern = "/api/kumbukumbus/:kumbukumbuUuid",
          scalaMethod = deleteKumbuKumbu _
        ),
        restCall(
          Method.GET,
          pathPattern = "/api/kumbukumbus",
          scalaMethod = listKumbuKumbus _
        )
      )
      .withAutoAcl(true)
  }
}
