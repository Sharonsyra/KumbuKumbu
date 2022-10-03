package com.sharonsyra.common

import play.api.libs.json.{Format, Json}

import java.util.UUID

case class KumbuKumbuCreated (
  kumbuKumbuUuid: UUID,
  kumbukumbuTitle: String,
  kumbukumbuContent: Option[String]
)

object KumbuKumbuCreated {
  implicit val format: Format[KumbuKumbuCreated] = Json.format[KumbuKumbuCreated]
}
