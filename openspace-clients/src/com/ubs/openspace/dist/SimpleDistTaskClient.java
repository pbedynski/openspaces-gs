package com.ubs.openspace.dist;

import java.util.List;
import java.util.concurrent.ExecutionException;

import org.openspaces.core.GigaSpace;
import org.openspaces.core.GigaSpaceConfigurer;
import org.openspaces.core.executor.DistributedTask;
import org.openspaces.core.space.UrlSpaceConfigurer;

import com.gigaspaces.async.AsyncFuture;
import com.gigaspaces.async.AsyncResult;

public class SimpleDistTaskClient {

	private final static String SPACE_CONF = "jini://*/*/OS";

	protected GigaSpace gigaSpace;

	protected void connect() throws Exception {
		if (gigaSpace == null) {
			gigaSpace = new GigaSpaceConfigurer(new UrlSpaceConfigurer(
					SPACE_CONF).lookupGroups(System.getenv("LOOKUPGROUPS"))
					.space()).gigaSpace();
		}
	}

	private void executeTask() throws ExecutionException, InterruptedException {
		AsyncFuture<Long> future = gigaSpace.execute(new SimpleDistTask());
		long result = future.get();
		System.out.println(String.format("Result: %d", result));
	}

	public static void main(String[] args) throws Exception {
		SimpleDistTaskClient client = new SimpleDistTaskClient();
		client.connect();
		client.executeTask();
	}

	public static class SimpleDistTask implements
			DistributedTask<Integer, Long> {
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
}
