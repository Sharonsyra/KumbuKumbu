package com.sharonsyra.common

import play.api.libs.json.{Format, Json}

import java.util.UUID

// To revisit

case class DeleteKumbuKumbu (
  kumbuKumbuUuid: UUID
)

object DeleteKumbuKumbu {
  implicit val format: Format[DeleteKumbuKumbu] = Json.format[DeleteKumbuKumbu]
}
