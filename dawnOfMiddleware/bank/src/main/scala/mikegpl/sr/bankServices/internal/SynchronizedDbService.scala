package mikegpl.sr.bankServices.internal

import mikegpl.sr.bankServices.DbService
import mikegpl.sr.thrift.{BankClient, ClientDoesNotExistException, ClientExistsException}

import scala.collection.mutable.{HashMap => Map}

class SynchronizedDbService extends DbService {
  private val clientMap = Map[Long, BankClient]()

  override def registerClient(client: BankClient): Unit = {
    clientMap.synchronized {
      if (clientMap.contains(client.guid))
        throw new ClientExistsException(client.guid)

      clientMap += (client.guid -> client)
    }
  }

  def getClient(guid: Long): BankClient = {
    clientMap.synchronized {
      if (!clientMap.contains(guid))
        throw new ClientDoesNotExistException(guid)

      clientMap(guid)
    }
  }
}
