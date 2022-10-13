package com.sharonsyra.common

import play.api.libs.json.{Format, Json}

import java.util.UUID

case class CreateKumbuKumbu(
  kumbuKumbuUuid: UUID,
  kumbuKumbuTitle: String,
  kumbuKumbuContent: Option[String]
)

object CreateKumbuKumbu {
  implicit val format: Format[CreateKumbuKumbu] = Json.format[CreateKumbuKumbu]
}
