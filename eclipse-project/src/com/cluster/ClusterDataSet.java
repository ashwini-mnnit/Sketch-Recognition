package com.cluster;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import com.sketchshape.SrlShapeExtended;

import edu.tamu.srl.sketch.core.object.SrlShape;
import edu.tamu.srl.sketch.core.tobenamedlater.SrlShapeConfig;
import net.sf.javaml.clustering.Clusterer;
import net.sf.javaml.clustering.KMeans;
import net.sf.javaml.core.Dataset;
import net.sf.javaml.core.DefaultDataset;
import net.sf.javaml.core.DenseInstance;
import net.sf.javaml.core.Instance;

public class ClusterDataSet {
	public final static int NUMBER_OF_FEATURES = 2;

	public void setClusterId(List<SrlShape> srlShapeList) {
		Dataset data = new DefaultDataset();
		Map<Integer, UUID> idMap = new HashMap<Integer, UUID>();
	    for (SrlShape srlShape : srlShapeList) {
	    	double[] values = new double[ClusterDataSet.NUMBER_OF_FEATURES];
	    	values[0] = (double)(int)(((SrlShapeExtended) srlShape).getAverageSpeed()*100);
	    	values[1] = (double)(int)(((SrlShapeExtended) srlShape).getAveragePressure()*100);
	        Instance currentInstance = new DenseInstance(values);
	        idMap.put(currentInstance.getID(), srlShape.getId());
	        data.add(currentInstance);
	    }
	    Dataset[] clusterInstances = getClusteredData(data);
	    Map<UUID, Integer> clusterIds = setCLusterIds(clusterInstances, idMap);
	    populateSrlShapeWithUserType(srlShapeList, clusterIds);
	}

	private Map<UUID, Integer> setCLusterIds(Dataset[] clusterInstances,
			Map<Integer, UUID> idMap) {
		Map<UUID, Integer> userTypeMap = new HashMap<UUID, Integer>();
		for(int i = 0; i < clusterInstances.length; i++) {
			for(Instance instance : clusterInstances[i]) {
				
				userTypeMap.put(idMap.get(instance.getID()), i+1);
			}
		}
		return userTypeMap;
	}

	private static void populateSrlShapeWithUserType(
			List<SrlShape> srlShapeList, Map<UUID, Integer> clusterIds) {
		for(SrlShape srlShape : srlShapeList) {
			int clusterId = clusterIds.get(srlShape.getId());
			((SrlShapeExtended) srlShape).setClusterId(clusterId);
		}
	}

	private static Dataset[] getClusteredData(Dataset data) {
    	Clusterer km = new KMeans(4);
	    Dataset[] clusters = km.cluster(data);
	    return clusters;
    }
	
	private static SrlShapeExtended newSrlShape(double averageSpeed, double averagePresure) {
		boolean temp = false;
		UUID uuid1 = UUID.randomUUID();
		SrlShapeConfig srlShapeConfig1 = new SrlShapeConfig(null, uuid1, "", 0.0, 0.0, temp, temp);
		SrlShapeExtended srlShapeExtended = new SrlShapeExtended(1234, uuid1, srlShapeConfig1, "srl Shape Extended 1");
		srlShapeExtended.setAverageSpeed(averageSpeed);
		srlShapeExtended.setAveragePressure(averagePresure);
		return srlShapeExtended;
	}
	
	public static void main(String[] args) {
		List<SrlShape> srlShapeList = new ArrayList<SrlShape>();
		SrlShape srlShape1 = newSrlShape(1, 10);
		SrlShape srlShape2 = newSrlShape(2, 11);
		SrlShape srlShape3 = newSrlShape(3, 14);
		SrlShape srlShape4 = newSrlShape(9, 25);
		SrlShape srlShape5 = newSrlShape(10, 26);
		
		srlShapeList. add(srlShape1);
		srlShapeList. add(srlShape2);
		srlShapeList. add(srlShape3);
		srlShapeList. add(srlShape4);
		srlShapeList. add(srlShape5);
		
		ClusterDataSet clusterDataSet = new ClusterDataSet();
		
		clusterDataSet.setClusterId(srlShapeList);
		for(SrlShape srlShape : srlShapeList) 
			System.out.printf("clusterId is %f\n", ((SrlShapeExtended) srlShape).getClusterId());
	
	}
}
