package test;

import daos.ResourceDao;
import tables.Resource;

public class InsertResources {

	public void insertResources() {
		ResourceDao rdao = new ResourceDao();
		//TODO change to getResourceByName()
		Resource resource = new Resource();
		resource.setResourceId(1);
		resource.setNazwa("Pytanie");
		rdao.add(resource);
		
	}
	
	public static void main(String[] args) {
		InsertResources ir = new InsertResources();
		ir.insertResources();
	}
	
}
