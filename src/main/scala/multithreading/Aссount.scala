package multithreading

class Account (private var amount: Int) {
  def deposit(money: Int) = this.amount += money
  def withdraw(money: Int) = this.amount -= money

  def balance = amount
}
