package com.hr.async;

import org.springframework.aop.framework.AopContext;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.CompletableFuture;

/**
 * @author huangran <huangran@kuaishou.com>
 * created on 2022-05-01
 */
@Service
public class AsyncBeanService {

	@Async
	private CompletableFuture<Void> execute(String s) {
		return CompletableFuture.runAsync(() -> System.out.println(Thread.currentThread().getName() + "---" + s));
	}

	public void doubleString(Collection<String> names) {
		System.out.println(Thread.currentThread().getName());
		List<CompletableFuture<Void>> futureList = new ArrayList<>(names.size());
		AsyncBeanService proxy = (AsyncBeanService) AopContext.currentProxy();
		for (String name : names) {
			futureList.add(proxy.execute(name));
		}
		CompletableFuture.allOf(futureList.toArray(new CompletableFuture[0]))
				.thenRun(() -> System.out.println("all task done!")).join();
		System.out.println(names);
	}

	@Async
	public CompletableFuture<String> doubleString(String s) {
		System.out.println(Thread.currentThread().getName());
		return CompletableFuture.supplyAsync(() -> Thread.currentThread().getName() + " : " + String.format("%s-%s", s, s));
	}
}
