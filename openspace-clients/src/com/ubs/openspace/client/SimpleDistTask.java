package com.ubs.openspace.client;

import java.util.List;

import org.openspaces.core.executor.DistributedTask;

import com.gigaspaces.async.AsyncResult;

public class SimpleDistTask implements DistributedTask<Integer, Long> {

	private static final long serialVersionUID = -4534388774238416500L;

	public Integer execute() throws Exception {
		return 1;
	}

	public Long reduce(List<AsyncResult<Integer>> results) throws Exception {
		long sum = 0;
		for (AsyncResult<Integer> result : results) {
			if (result.getException() != null) {
				throw result.getException();
			}
			sum += result.getResult();
		}
		return sum;
	}
}