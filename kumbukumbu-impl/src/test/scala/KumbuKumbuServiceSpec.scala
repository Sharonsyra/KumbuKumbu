import com.lightbend.lagom.scaladsl.server.{
  LagomApplicationContext,
  LocalServiceLocator
}
import com.lightbend.lagom.scaladsl.testkit.ServiceTest
import com.sharonsyra.common.CreateKumbuKumbu
import com.sharonsyra.kumbukumbu.KumbuKumbuApplication
import com.sharonsyra.kumbukumbu.api.KumbuKumbuService
import org.scalatest.BeforeAndAfterAll
import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

import java.util.UUID
import scala.concurrent.ExecutionContext

class KumbuKumbuServiceSpec()(implicit ec: ExecutionContext)
    extends AnyWordSpec
    with Matchers
    with BeforeAndAfterAll {

  private val server = ServiceTest.startServer(ServiceTest.defaultSetup) {
    ctx: LagomApplicationContext =>
      new KumbuKumbuApplication(ctx) with LocalServiceLocator
  }

  val client: KumbuKumbuService =
    server.serviceClient.implement[KumbuKumbuService]

  override protected def afterAll(): Unit = server.stop()

  "The KumbuKumbu Service" should {
    "create kumbukumbu should successfully create a kumbukumbu" in {
      val kumbuKumbuUuid = UUID.randomUUID()
      val createKumbuKumbuRequest = CreateKumbuKumbu(
        kumbuKumbuUuid = kumbuKumbuUuid,
        kumbuKumbuTitle = "test note",
        kumbuKumbuContent = Some("test description")
      )

      client.createKumbuKumbu.invoke(createKumbuKumbuRequest).map { response =>
        response.kumbuKumbuTitle shouldEqual "test note"
        response.kumbuKumbuContent shouldEqual Some("test description")
        response.updatedAt shouldBe None
      }

    }
  }
}
