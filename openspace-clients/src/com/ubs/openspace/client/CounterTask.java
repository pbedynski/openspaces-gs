package com.ubs.openspace.client;

import org.openspaces.core.GigaSpace;
import org.openspaces.core.executor.Task;
import org.openspaces.core.executor.TaskGigaSpace;

public class CounterTask<T> implements Task<Integer> {

	private static final long serialVersionUID = -2181874143695813182L;

	private Object template;

	@TaskGigaSpace
	private transient GigaSpace gigaSpace;
	
	public CounterTask(T template) {
		this.template = template;
	}

	public Integer execute() throws Exception {
		return gigaSpace.count(template);
	}
}
