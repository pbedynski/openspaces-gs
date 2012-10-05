package com.ubs.openspace.client;

import org.openspaces.core.GigaSpace;
import org.openspaces.core.GigaSpaceConfigurer;
import org.openspaces.core.space.UrlSpaceConfigurer;

import com.ubs.openspace.model.KPI;


public class KPIFeeder {

	
	private final static String SPACE_CONF = "jini://*/*/OS";
	
	protected GigaSpace gigaSpace;
	
	protected void connect() throws Exception{
		if (gigaSpace == null){
			gigaSpace = new GigaSpaceConfigurer(new UrlSpaceConfigurer(SPACE_CONF).space()).gigaSpace();
		}
	}
	
	public void feed() throws InterruptedException {
		int n=0;
		while(true){
			int i=5;
			while (i-- >0) {
				KPI kpi = new KPI(); kpi.setName("kpi"+n++); kpi.setValue(n);
				gigaSpace.write(kpi);
			}
			Thread.sleep(10000);
		}
	}
	
	public static void main(String[] s) throws Exception {
		KPIFeeder feeder = new KPIFeeder();
 		feeder.connect();
		feeder.feed();
	}

}
