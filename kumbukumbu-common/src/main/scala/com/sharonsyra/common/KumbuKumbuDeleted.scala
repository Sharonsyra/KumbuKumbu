package com.sharonsyra.common

import play.api.libs.json.{Format, Json}

import java.util.UUID

case class KumbuKumbuDeleted (
  kumbuKumbuUuid: UUID
)

object KumbuKumbuDeleted {
  implicit val format: Format[KumbuKumbuDeleted] = Json.format[KumbuKumbuDeleted]
}
