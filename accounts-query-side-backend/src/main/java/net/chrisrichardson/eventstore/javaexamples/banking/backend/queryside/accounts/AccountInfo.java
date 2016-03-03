package net.chrisrichardson.eventstore.javaexamples.banking.backend.queryside.accounts;

import java.util.List;

/**
 * Created by cer on 11/21/14.
 */
public class AccountInfo {

  private String id;
  private String account;

private long balance;
  private List<AccountChangeInfo> changes;
  private List<AccountTransactionInfo> transactions;
  private String version;

  private AccountInfo() {
  }

  public AccountInfo(String id, String account, long balance, List<AccountChangeInfo> changes, List<AccountTransactionInfo> transactions, String version) {

    this.id = id;
    this.account = account;
    this.balance = balance;
    this.changes = changes;
    this.transactions = transactions;
    this.version = version;
  }

  public String getId() {
    return id;
  }
  
  public String getAccount() {
	  return account;
  }
  
  public void setAccount(String account) {
	  this.account = account;
  }

  public long getBalance() {
    return balance;
  }

  public List<AccountChangeInfo> getChanges() {
    return changes;
  }

  public List<AccountTransactionInfo> getTransactions() {
    return transactions;
  }

  public String getVersion() {
    return version;
  }
}
