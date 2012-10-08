package com.ubs.openspace.dist;

import java.util.concurrent.ExecutionException;

import org.openspaces.core.GigaSpace;
import org.openspaces.core.GigaSpaceConfigurer;
import org.openspaces.core.executor.Task;
import org.openspaces.core.executor.TaskGigaSpace;
import org.openspaces.core.executor.support.SumTask;
import org.openspaces.core.space.UrlSpaceConfigurer;

import com.gigaspaces.async.AsyncFuture;
import com.ubs.openspace.model.KPI;

public class CoutingDistTaskClient {

	private final static String SPACE_CONF = "jini://*/*/OS";

	protected GigaSpace gigaSpace;

	protected void connect() throws Exception {
		if (gigaSpace == null) {
			gigaSpace = new GigaSpaceConfigurer(new UrlSpaceConfigurer(
					SPACE_CONF).lookupGroups(System.getenv("LOOKUPGROUPS"))
					.space()).gigaSpace();
		}
	}

	private void executeTask() throws InterruptedException, ExecutionException {
		AsyncFuture<Integer> future = gigaSpace
				.execute(new SumTask<Integer, Integer>(Integer.class,
						new CounterKPITask<KPI>(new KPI())));
		int result = future.get();
		System.out.println(String.format("Result: %d", result));
	}

	public static void main(String[] args) throws Exception {
		CoutingDistTaskClient client = new CoutingDistTaskClient();
		client.connect();
		client.executeTask();
	}

	public static class CounterKPITask<T> implements Task<Integer> {

		
		
		private static final long serialVersionUID = 3790480597165575882L;

		private T template;
		
		@TaskGigaSpace
		private GigaSpace gigaSpace;
		
		public CounterKPITask(T template) {
			this.template = template;
		}

		@Override
		public Integer execute() throws Exception {
			return gigaSpace.count(template);
		}
	}
}
