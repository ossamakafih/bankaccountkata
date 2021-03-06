package soat.bankaccount
import java.time.LocalDate

import org.scalamock.scalatest.MockFactory
import org.scalatest.{BeforeAndAfter, FunSuite}
import soat.bankaccount.model.Transaction


class AccountTest extends FunSuite with BeforeAndAfter with MockFactory {

  var account: Account = _
  val statementPrinter = stub[MockablePrintAccount]
  val date = new DateTransaction

  before{
    account = new Account(date, statementPrinter)
  }

  test("no transaction yet") {
    assert(account.history().isEmpty)
  }

  test("new deposit on account") {
    account.deposit(1000)
    val transactions = account.history()
    assert(transactions.head.amount === 1000)
    assert(transactions.size === 1)
  }

  test("new withdraw from account") {
    account.withdraw(500)
    val transactions = account.history()
    assert(transactions.head.amount === -500)
    assert(transactions.size === 1)
  }

  test("account is a list of transactions") {
    assert(account.history().isInstanceOf[List[Transaction]])
  }

  test("date is a LocalDate format") {
    assert(date.today().isInstanceOf[LocalDate])
  }

  class MockablePrintAccount extends PrintAccount()
  class MockableDateTransaction extends DateTransaction()
}
