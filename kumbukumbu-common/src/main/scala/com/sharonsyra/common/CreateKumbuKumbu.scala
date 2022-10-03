package com.sharonsyra.common

import play.api.libs.json.{Format, Json}

import java.util.UUID

case class CreateKumbuKumbu(
  kumbuKumbuUuid: UUID,
  kumbukumbuTitle: String,
  kumbukumbuContent: Option[String]
)

object CreateKumbuKumbu {
  implicit val format: Format[CreateKumbuKumbu] = Json.format[CreateKumbuKumbu]
}
