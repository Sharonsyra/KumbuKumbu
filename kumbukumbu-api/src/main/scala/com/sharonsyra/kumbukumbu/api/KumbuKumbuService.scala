package com.sharonsyra.kumbukumbu.api

import akka.NotUsed
import com.lightbend.lagom.scaladsl.api.Service.{named, restCall}
import com.lightbend.lagom.scaladsl.api.transport.Method
import com.lightbend.lagom.scaladsl.api.{Descriptor, Service, ServiceCall}
import com.sharonsyra.common._

import java.util.UUID

trait KumbuKumbuService extends Service {

  def createKumbuKumbu: ServiceCall[CreateKumbuKumbu, KumbuKumbu]

  def getKumbuKumbu(
    kumbuKumbuUuid: UUID
  ): ServiceCall[NotUsed, KumbuKumbu]

  def searchKumbuKumbu(
    kumbuKumbuTitle: String
  ): ServiceCall[NotUsed, KumbuKumbu]

  def updateKumbuKumbu(
    kumbuKumbuUuid: UUID
  ): ServiceCall[UpdateKumbuKumbu, KumbuKumbu]

  def deleteKumbuKumbu(
    kumbuKumbuUuid: UUID
  ): ServiceCall[DeleteKumbuKumbu, KumbuKumbu]

  def listKumbuKumbus: ServiceCall[NotUsed, ListKumbuKumbus]

  // next by created at or by updated at??
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
          pathPattern = "/api/kumbukumbus/:kumbukumbuTitle",
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
