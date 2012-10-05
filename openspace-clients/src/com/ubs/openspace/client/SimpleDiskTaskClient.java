package com.ubs.openspace.client;

import java.util.concurrent.ExecutionException;

import org.openspaces.core.GigaSpace;
import org.openspaces.core.GigaSpaceConfigurer;
import org.openspaces.core.space.UrlSpaceConfigurer;

import com.gigaspaces.async.AsyncFuture;

public class SimpleDiskTaskClient {

	private final static String SPACE_CONF = "jini://*/*/OS";
	
	protected GigaSpace gigaSpace;
	
	protected void connect() throws Exception{
		if (gigaSpace == null){
			gigaSpace = new GigaSpaceConfigurer(new UrlSpaceConfigurer(SPACE_CONF).space()).gigaSpace();
		}
	}
	
	public void executeTask() throws InterruptedException, ExecutionException {
		
		AsyncFuture<Long> future = gigaSpace.execute(new SimpleDistTask());
		long result = future.get(); 
		System.out.println(result);
	}
	
	public static void main(String[] s) throws Exception {
		SimpleDiskTaskClient taskClient = new SimpleDiskTaskClient();
		taskClient.connect();
		taskClient.executeTask();
	}

}
