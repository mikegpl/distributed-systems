package mikegpl.sr

import java.net.InetSocketAddress

import mikegpl.sr.services.internal.{PremiumLoanService, SynchronizedDbService}
import mikegpl.sr.services.remote.{PremiumAccountManagerService, RegistrationManagerService, StandardAccountManagerService, StreamingCurrencyService}
import mikegpl.sr.thrift.{PremiumAccountManager, RegistrationManager, StandardAccountManager}
import org.apache.thrift.TMultiplexedProcessor
import org.apache.thrift.protocol.TBinaryProtocol
import org.apache.thrift.server.{TServer, TSimpleServer}
import org.apache.thrift.transport.TServerSocket

object BankServer {
  private val Host: String = "localhost"
  private val Port: Int = 1919

  def main(args: Array[String]): Unit = {
    val dbService = new SynchronizedDbService
    val currencyService = new StreamingCurrencyService().init()
    val loanService = new PremiumLoanService(dbService, currencyService)

    val registrationServiceProcessor = new RegistrationManager.Processor(new RegistrationManagerService(dbService))
    val standardServiceProcessor = new StandardAccountManager.Processor(new StandardAccountManagerService(dbService))
    val premiumServiceProcessor = new PremiumAccountManager.Processor(new PremiumAccountManagerService(dbService, loanService))

    val socket = new TServerSocket(new InetSocketAddress(Host, Port))
    val protocol = new TBinaryProtocol.Factory()

    val multiplexedServices = new TMultiplexedProcessor
    multiplexedServices.registerProcessor("registration", registrationServiceProcessor)
    multiplexedServices.registerProcessor("standard", standardServiceProcessor)
    multiplexedServices.registerProcessor("premium", premiumServiceProcessor)

    val server = new TSimpleServer(new TServer.Args(socket)
      .protocolFactory(protocol)
      .processor(multiplexedServices))

    println("Starting server")
    server.serve()
  }
}
