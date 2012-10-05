package com.ubs.openspace.client;

import org.openspaces.core.GigaSpace;
import org.openspaces.core.GigaSpaceConfigurer;
import org.openspaces.core.space.UrlSpaceConfigurer;

import com.ubs.openspace.model.KPI;

public class KPIFeeder {

	private static final long SLEEP_TIME = 10000;
	private static final boolean SHOULD_STOP = false;
	private static final int KPIS_PER_ITERATION = 5;

	private final static String SPACE_CONF = "jini://*/*/OS";

	protected GigaSpace gigaSpace;

	protected void connect() throws Exception {
		if (gigaSpace == null) {
			gigaSpace = new GigaSpaceConfigurer(new UrlSpaceConfigurer(
					SPACE_CONF).lookupGroups(System.getenv("LOOKUPGROUPS"))
					.space()).gigaSpace();
		}

	}

	public void feed() throws InterruptedException {
		int kpiCounter = 0;
		while (!SHOULD_STOP || kpiCounter < 100) {
			int i = KPIS_PER_ITERATION;
			while (i-- > 0) {
				KPI kpi = new KPI();
				kpi.setName("KPI_" + kpiCounter++);
				kpi.setValue(kpiCounter);
				gigaSpace.write(kpi);
			}
			System.out.println(String.format(
					"Inserted %d new KPIs, sleeping (%d s)",
					KPIS_PER_ITERATION, SLEEP_TIME));
			Thread.sleep(SLEEP_TIME);
		}
	}

	public static void main(String[] s) throws Exception {
		System.out.println("Lookupgroups: " + System.getenv("LOOKUPGROUPS"));
		KPIFeeder feeder = new KPIFeeder();
		feeder.connect();
		feeder.feed();
	}

}
