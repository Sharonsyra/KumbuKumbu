package com.sharonsyra.common

import play.api.libs.json.{Format, Json}

import java.util.UUID

case class GetKumbuKumbu (
  kumbuKumbuUuid: UUID
)

object GetKumbuKumbu {
  implicit val format: Format[GetKumbuKumbu] = Json.format[GetKumbuKumbu]
}
