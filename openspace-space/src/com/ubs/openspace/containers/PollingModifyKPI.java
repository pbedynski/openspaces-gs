package com.ubs.openspace.containers;

import org.openspaces.events.EventDriven;
import org.openspaces.events.adapter.SpaceDataEvent;
import org.openspaces.events.polling.Polling;

import com.ubs.openspace.model.KPI;

@EventDriven @Polling
public class PollingModifyKPI {

	@SpaceDataEvent
	public KPI eventListener(KPI processedKPI){
		processedKPI.setCategory("processed_" + processedKPI.getCategory());
		processedKPI.setProcessed(true);
		return processedKPI;
	}
	
}
