package com.sharonsyra.common

import play.api.libs.json.{Format, Json}

import java.util.UUID

case class UpdateKumbuKumbu(
  kumbuKumbuTitle: Option[String],
  kumbuKumbuContent: Option[String]
)

object UpdateKumbuKumbu {
  implicit val format: Format[UpdateKumbuKumbu] = Json.format[UpdateKumbuKumbu]
}
