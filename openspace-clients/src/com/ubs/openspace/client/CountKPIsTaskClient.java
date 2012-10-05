package com.ubs.openspace.client;

import java.util.concurrent.ExecutionException;

import org.openspaces.core.GigaSpace;
import org.openspaces.core.GigaSpaceConfigurer;
import org.openspaces.core.executor.support.SumTask;
import org.openspaces.core.space.UrlSpaceConfigurer;

import com.gigaspaces.async.AsyncFuture;
import com.ubs.openspace.model.KPI;

public class CountKPIsTaskClient {

	private final static String SPACE_CONF = "jini://*/*/OS";
	
	protected GigaSpace gigaSpace;
	
	protected void connect() throws Exception{
		if (gigaSpace == null){
			gigaSpace = new GigaSpaceConfigurer(new UrlSpaceConfigurer(SPACE_CONF).space()).gigaSpace();
		}
	}
	
	public void executeTask() throws InterruptedException, ExecutionException {
		
		AsyncFuture<Integer> future = gigaSpace.execute(
				new SumTask<Integer, Integer>(Integer.class, new CounterTask<KPI>(new KPI())));
		long result = future.get(); 
		System.out.println(result);
	}
	
	public static void main(String[] s) throws Exception {
		CountKPIsTaskClient taskClient = new CountKPIsTaskClient();
		taskClient.connect();
		taskClient.executeTask();
	}

}
