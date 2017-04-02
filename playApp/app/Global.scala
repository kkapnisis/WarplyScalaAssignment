import common.utils.cassandra.{Helper, CassandraConnectionUri}
import play.api._

object Global extends GlobalSettings {

  override def onStart(app: Application) {

    val cassandraUri = CassandraConnectionUri("cassandra://localhost:9042/test")
    Helper.createSessionAndInitKeyspace(cassandraUri)

  }

}