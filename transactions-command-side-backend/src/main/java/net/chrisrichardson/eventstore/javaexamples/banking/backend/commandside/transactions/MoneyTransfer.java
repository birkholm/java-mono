package net.chrisrichardson.eventstore.javaexamples.banking.backend.commandside.transactions;

import net.chrisrichardson.eventstore.Event;
import net.chrisrichardson.eventstore.EventUtil;
import net.chrisrichardson.eventstore.ReflectiveMutableCommandProcessingAggregate;
import net.chrisrichardson.eventstore.javaexamples.banking.backend.common.transactions.*;

import java.util.List;

public class MoneyTransfer extends ReflectiveMutableCommandProcessingAggregate<MoneyTransfer, MoneyTransferCommand> {

  private TransferDetails details;
  private TransferState state;

  public List<Event> process(CreateMoneyTransferCommand cmd) {
	  System.out.println("process 1");
    return EventUtil.events(new MoneyTransferCreatedEvent(cmd.getDetails()));
  }

  public List<Event> process(RecordDebitCommand cmd) {
	  System.out.println("process 2" );
    return EventUtil.events(new DebitRecordedEvent(details));
  }

  public List<Event> process(RecordDebitFailedCommand cmd) {
	  System.out.println("process 3");
    return EventUtil.events(new FailedDebitRecordedEvent(details));
  }

  public List<Event> process(RecordCreditCommand cmd) {
	  System.out.println("process 4");
    return EventUtil.events(new CreditRecordedEvent(details));
  }

  public void apply(MoneyTransferCreatedEvent event) {
	  System.out.println("apply 1");
    this.details = event.getDetails();
    this.state = TransferState.INITIAL;
  }

  public void apply(DebitRecordedEvent event) {
	  System.out.println("apply 2");
    this.state = TransferState.DEBITED;
  }

  public void apply(FailedDebitRecordedEvent event) {
	  System.out.println("apply 3");
    this.state = TransferState.FAILED_DUE_TO_INSUFFICIENT_FUNDS;
  }

  public void apply(CreditRecordedEvent event) {
	  System.out.println("apply 4");
    this.state = TransferState.COMPLETED;
  }


  public TransferState getState() {
    return state;
  }
}
