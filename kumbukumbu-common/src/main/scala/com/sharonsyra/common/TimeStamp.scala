package com.sharonsyra.common

import play.api.libs.json.{Format, Json}

case class TimeStamp(
  seconds: Long,
  nanos: Int
)

object TimeStamp {
  implicit val format: Format[TimeStamp] = Json.format[TimeStamp]
}
