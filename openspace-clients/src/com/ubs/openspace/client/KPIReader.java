package com.ubs.openspace.client;

import org.openspaces.core.GigaSpace;
import org.openspaces.core.GigaSpaceConfigurer;
import org.openspaces.core.space.UrlSpaceConfigurer;

import com.j_spaces.core.client.SQLQuery;
import com.ubs.openspace.model.KPI;
import com.ubs.openspace.utils.StackUtils;

public class KPIReader {

	private final static String SPACE_CONF = "jini://*/*/OS";
	private static final boolean VERBOSE = false;
	
	protected GigaSpace gigaSpace;

	protected void connect() throws Exception {
		if (gigaSpace == null) {
			gigaSpace = new GigaSpaceConfigurer(new UrlSpaceConfigurer(
					SPACE_CONF).lookupGroups(System.getenv("LOOKUPGROUPS"))
					.space()).gigaSpace();
		}

	}

	private void printKPIstoSyso(KPI[] kpis) {
		for (KPI kpi : kpis) {
			System.out.println(" " + kpi.toString());
		}
	}

	public static void main(String[] s) throws Exception {
		System.out.println("Lookupgroups: " + System.getenv("LOOKUPGROUPS"));
		KPIReader reader = new KPIReader();
		reader.connect();
		reader.readByValue();
		reader.readByValue();
		reader.readByCategory();	
		reader.readByCategory();
		reader.readOnlyProcessed();
	}

	private void readByValue() {
		long start = System.nanoTime();
		KPI[] results = gigaSpace.readMultiple(new SQLQuery<KPI>(KPI.class, "value > 5 and value < 10"));
		long finish = System.nanoTime();
		System.out.println(String.format(StackUtils.getMethodName() + " Got %d KPIs in %.3f ms", results.length, ((double)(finish - start) / 1000000.0)));
		if (VERBOSE) printKPIstoSyso(results);
	}
	
	private void readByCategory() {
		String categoryToSearch = "category1";
		KPI templateKPI = new KPI();
		templateKPI.setCategory(categoryToSearch);
		long start = System.nanoTime();
		KPI[] results = gigaSpace.readMultiple(templateKPI);
		long finish = System.nanoTime();
		System.out.println(String.format(StackUtils.getMethodName() + " Got %d KPIs in %.3f ms", results.length, ((double)(finish - start) / 1000000.0)));
		if (VERBOSE) printKPIstoSyso(results);
	}
	
	private void readOnlyProcessed() {
		KPI templateKPI = new KPI();
		templateKPI.setProcessed(true);
		long start = System.nanoTime();
		KPI[] results = gigaSpace.readMultiple(templateKPI);
		long finish = System.nanoTime();
		System.out.println(String.format(StackUtils.getMethodName() + " Got %d KPIs in %.3f ms", results.length, ((double)(finish - start) / 1000000.0)));
		if (VERBOSE) printKPIstoSyso(results);
	}
}
