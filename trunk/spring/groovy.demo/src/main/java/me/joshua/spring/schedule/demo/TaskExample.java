package me.joshua.spring.schedule.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.task.TaskExecutor;
import org.springframework.stereotype.Component;

@Component("taskExecutorExample")
public class TaskExample {

	@Autowired
	@Qualifier("taskExecutor")
	private TaskExecutor taskExecutor;

	public void printMessages() {
		System.out.println("[Basic]Initializing tasks");
		for (int i = 0; i < 25; i++) {
			taskExecutor.execute(new MessagePrinterTask("Message" + i));
		}
		System.out.println("[Basic]Tasks initialization finished");
	}

	private class MessagePrinterTask implements Runnable {

		private String message;

		public MessagePrinterTask(String message) {
			this.message = message;
		}

		public void run() {
			ScheduleUtil.randomSleep(5);
			System.out.println("[Basic]" + message);
		}

	}
}