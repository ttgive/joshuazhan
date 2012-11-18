package me.joshua.spring.schedule.demo;

import java.util.Date;
import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component("ScheduleTaskExample")
public class ScheduleExample {
	private final static String FIXED_DELAY = "Fixed Delay Schedule";
	private final static String FIXED_RATE = "Fixed Rate Schedule";
	private final static String CRON = "Cron Schedule";

	private AtomicInteger fixedDelayCounter;
	private AtomicInteger fixedRateCounter;
	private AtomicInteger cronCounter;

	public ScheduleExample() {
		fixedDelayCounter = new AtomicInteger();
		fixedRateCounter = new AtomicInteger();
		cronCounter = new AtomicInteger();
	}

	// @Scheduled(fixedDelay = 2000)
	public void fixedDelaySchedule() {
		int id = fixedDelayCounter.incrementAndGet();
		printStartMessage(FIXED_DELAY, id);
		ScheduleUtil.randomSleep(2);
		printEndMessage(FIXED_DELAY, id, fixedDelayCounter.get());
	}

	@Scheduled(fixedRate = 2000)
	public void fixedRateSchedule() {
		int id = fixedRateCounter.incrementAndGet();
		printStartMessage(FIXED_RATE, id);
		ScheduleUtil.randomSleep(16);
		printEndMessage(FIXED_RATE, id, id);
		// printEndMessage(FIXED_RATE, id, fixedRateCounter.get());
		throw new RuntimeException();
	}

	// @Scheduled(cron = "*/2 * * * * *")
	public void cronSchedule() {
		int id = cronCounter.incrementAndGet();
		printStartMessage(CRON, id);
		ScheduleUtil.randomSleep(4);
		printEndMessage(CRON, id, cronCounter.get());
	}

	private void printStartMessage(String title, int id) {
		System.out.printf("[%s ID:%d]Start at %3$tH:%<tM:%<tS-%<tL\n", title,
				id, new Date());
	}

	private void printEndMessage(String title, int id, int count) {
		System.out
				.printf("[%s ID:%d]Finish at %3$tH:%<tM:%<tS-%<tL, totally run %4$d times\n",
						title, id, new Date(), count);
	}
}
