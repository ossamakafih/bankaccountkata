package soat.bankaccount

import com.google.common.util.concurrent.AtomicDouble
import soat.bankaccount.model.Transaction


class PrintAccount {

  //header for printing statement
  val header = "operation || date || transaction_amount || type || balance"

  //format and print statement with account balance
  def showTransaction(transactions: List[Transaction]): Unit = {
    val balance = new AtomicDouble(0)

    transactions.map { transaction =>
      val newBalnce = balance.addAndGet(transaction.amount)
      if (transaction.amount < 0){
        s"${transaction.operation} || ${transaction.Date} ||  ${transaction.amount} || withdraw || $newBalnce"
      }
      else{
        s"${transaction.operation} || ${transaction.Date} ||  ${transaction.amount} || deposit || $newBalnce"
      }
    }.foreach(println)
  }
}
