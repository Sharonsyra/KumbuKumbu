package com.sharonsyra.common

import play.api.libs.json.{Format, Json}

import java.util.UUID

case class KumbuKumbu(
  kumbuKumbuUuid: UUID,
  kumbuKumbuTitle: String,
  kumbuKumbuContent: Option[String],
  createdAt: TimeStamp,
  updatedAt: Option[TimeStamp]
)

object KumbuKumbu {
  implicit val format: Format[KumbuKumbu] = Json.format[KumbuKumbu]
}
