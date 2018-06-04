package mikegpl.sr.services.internal

import mikegpl.sr.services.{BankUtils, CurrencyService, LoanService}
import mikegpl.sr.thrift.{InvalidAccountTypeException, LoanInquiry, LoanOffer}

class PremiumLoanService(dbService: SynchronizedDbService, currencyService: CurrencyService) extends LoanService {
  def loanConditions(inquiry: LoanInquiry): LoanOffer = {
    val client = dbService.getClient(inquiry.guid)
    if (!BankUtils.isEligibleForLoan(client))
      throw new InvalidAccountTypeException(inquiry.guid, s"Invalid account type: ${client.`type`}")

    val conversionRate = currencyService.getConversionRate(inquiry.currency, client.owner.baseCurrency)
    val baseCost = loanCost(inquiry.amount, conversionRate)
    val requestedCost = loanCost(inquiry.amount)
    new LoanOffer(baseCost, requestedCost)
  }

  override protected def loanCost(amount: Double, conversionRate: Double = 1.0): Double = amount * conversionRate * PremiumLoanService.LoanCost
}

object PremiumLoanService {
  private val LoanCost = 0.2
}