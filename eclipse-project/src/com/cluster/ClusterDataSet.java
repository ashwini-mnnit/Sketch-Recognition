package com.cluster;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import com.srlshapeextended.SrlShapeExtended;

import edu.tamu.srl.sketch.core.abstracted.SrlObject;
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

	public int getNumberOfFeatures() {
		return NUMBER_OF_FEATURES;
	}

	public void setClusterId(List<SrlShapeExtended> srlShapeExtendedList) {
		Dataset data = new DefaultDataset();
		Map<Integer, UUID> idMap = new HashMap<Integer, UUID>();
	    for (SrlShapeExtended srlShapeExtended : srlShapeExtendedList) {
	    	double[] values = new double[NUMBER_OF_FEATURES];
	    	values[0] = srlShapeExtended.getAverageSpeed();
	    	values[1] = srlShapeExtended.getAveragePressure();
	        Instance currentInstance = new DenseInstance(values);
	        currentInstance.setClassValue(values[values.length -1]);
	        //System.out.print(currentInstance.classValue());
	        idMap.put(currentInstance.getID(), srlShapeExtended.getSrlShape().getRecognizerId());
	        data.add(currentInstance);
	    }
	    Dataset dataForClassification = data;
	    for (Instance inst : dataForClassification) {
	    	System.out.printf(inst.classValue() + "\n");
	    }
	    Dataset[] clusterInstances = getClusteredData(data);
	    Map<UUID, Integer> clusterIds = setCLusterIds(clusterInstances, idMap);
	    populateSrlShapeWithUserType(srlShapeExtendedList, clusterIds);
	}

	private Map<UUID, Integer> setCLusterIds(Dataset[] clusterInstances,
			Map<Integer, UUID> idMap) {
		Map<UUID, Integer> userTypeMap = new HashMap<UUID, Integer>();
		for(int i = 0; i < clusterInstances.length; i++) {
			for(Instance instance : clusterInstances[i]) {
				
				userTypeMap.put(idMap.get(instance.getID()), i);
			}
		}
		return userTypeMap;
	}

	private static void populateSrlShapeWithUserType(
			List<SrlShapeExtended> srlShapeExtendedList,
			Map<UUID, Integer> clusterIds) {
		for(SrlShapeExtended srlShapeExtended : srlShapeExtendedList) {
			double clusterId = clusterIds.get(srlShapeExtended.getSrlShape().getRecognizerId());
			srlShapeExtended.setClusterId(clusterId);
		}
	}

	private static Dataset[] getClusteredData(Dataset data) {
    	Clusterer km = new KMeans(2);
	    Dataset[] clusters = km.cluster(data);
	    return clusters;
    }
	
	public static void main(String[] args) {
		List<SrlShapeExtended> srlShapeExtendedList = new ArrayList<SrlShapeExtended>();
		boolean temp = false;
		UUID uuid1 = UUID.randomUUID();
		SrlShapeConfig srlShapeConfig1 = new SrlShapeConfig(null, uuid1, "", 0.0, 0.0, temp, temp);
		SrlShapeExtended srlShapeExtended1 = new SrlShapeExtended(1234, uuid1, srlShapeConfig1, "srl Shape Extended 1");
		srlShapeExtended1.setAverageSpeed(1);
		srlShapeExtended1.setAveragePressure(10);
		
		UUID uuid2 = UUID.randomUUID();
		SrlShapeConfig srlShapeConfig2 = new SrlShapeConfig(null, uuid2, "", 0.0, 0.0, temp, temp);
		SrlShapeExtended srlShapeExtended2 = new SrlShapeExtended(1234, uuid1, srlShapeConfig2, "srl Shape Extended 2");
		srlShapeExtended2.setAverageSpeed(2);
		srlShapeExtended2.setAveragePressure(11);

		UUID uuid3 = UUID.randomUUID();
		SrlShapeConfig srlShapeConfig3 = new SrlShapeConfig(null, uuid3, "", 0.0, 0.0, temp, temp);
		SrlShapeExtended srlShapeExtended3 = new SrlShapeExtended(1234, uuid3, srlShapeConfig3, "srl Shape Extended 3");
		srlShapeExtended3.setAverageSpeed(3);
		srlShapeExtended3.setAveragePressure(14);

		UUID uuid4 = UUID.randomUUID();
		SrlShapeConfig srlShapeConfig4 = new SrlShapeConfig(null, uuid4, "", 0.0, 0.0, temp, temp);
		SrlShapeExtended srlShapeExtended4 = new SrlShapeExtended(1234, uuid4, srlShapeConfig4, "srl Shape Extended 4");
		srlShapeExtended4.setAverageSpeed(9);
		srlShapeExtended4.setAveragePressure(25);

		UUID uuid5 = UUID.randomUUID();
		SrlShapeConfig srlShapeConfig5 = new SrlShapeConfig(null, uuid5, "", 0.0, 0.0, temp, temp);
		SrlShapeExtended srlShapeExtended5 = new SrlShapeExtended(1234, uuid5, srlShapeConfig5, "srl Shape Extended 5");
		srlShapeExtended5.setAverageSpeed(10);
		srlShapeExtended5.setAveragePressure(26);
		
		srlShapeExtendedList. add(srlShapeExtended1);
		srlShapeExtendedList. add(srlShapeExtended2);
		srlShapeExtendedList. add(srlShapeExtended3);
		srlShapeExtendedList. add(srlShapeExtended4);
		srlShapeExtendedList. add(srlShapeExtended5);
		
		ClusterDataSet clusterDataSet = new ClusterDataSet();
		
		clusterDataSet.setClusterId(srlShapeExtendedList);
		
	}
}
