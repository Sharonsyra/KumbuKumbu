package com.sharonsyra.common

import play.api.libs.json.{Format, Json}

case class ListKumbuKumbus (
  kumbuKumbus: Seq[KumbuKumbu]
)

object ListKumbuKumbus {
  implicit val format: Format[ListKumbuKumbus] = Json.format[ListKumbuKumbus]
}
