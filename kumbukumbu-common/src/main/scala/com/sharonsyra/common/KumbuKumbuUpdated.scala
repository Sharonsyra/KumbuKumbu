package com.sharonsyra.common

import play.api.libs.json.{Format, Json}

import java.util.UUID

case class KumbuKumbuUpdated (
  kumbuKumbuUuid: UUID,
  kumbuKumbuTitle: Option[String],
  kumbuKumbuContent: Option[String]
)

object KumbuKumbuUpdated {
  implicit val format: Format[KumbuKumbuUpdated] = Json.format[KumbuKumbuUpdated]
}
