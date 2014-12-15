package com.cluster;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import com.parser.mechanix.MechanixShape;
import com.parser.mechanix.MechanixSketch;
import com.sketchshape.SrlShapeExtended;

import edu.tamu.srl.sketch.core.tobenamedlater.SrlShapeConfig;
import net.sf.javaml.clustering.Clusterer;
import net.sf.javaml.clustering.KMeans;
import net.sf.javaml.core.Dataset;
import net.sf.javaml.core.DefaultDataset;
import net.sf.javaml.core.DenseInstance;
import net.sf.javaml.core.Instance;

public class ClusterDataSet {
	public final static int NUMBER_OF_FEATURES = 2;

	public void setClusterId(List<SrlShapeExtended> srlShapeExtendedList) {
		Dataset data = new DefaultDataset();
		Map<Integer, UUID> idMap = new HashMap<Integer, UUID>();
	    for (SrlShapeExtended srlShapeExtended : srlShapeExtendedList) {
	    	double[] values = new double[ClusterDataSet.NUMBER_OF_FEATURES];
	    	values[0] = srlShapeExtended.getAverageSpeed();
	    	values[1] = srlShapeExtended.getAveragePressure();
	        Instance currentInstance = new DenseInstance(values);
	        //currentInstance.setClassValue(values[values.length -1]);
	        idMap.put(currentInstance.getID(), srlShapeExtended.getRecognizerId());
	        data.add(currentInstance);
	    }
	    Dataset[] clusterInstances = getClusteredData(data);
	    Map<UUID, Integer> clusterIds = setCLusterIds(clusterInstances, idMap);
	    populateSrlShapeWithUserType(srlShapeExtendedList, clusterIds);
	}

	public void setClusterIdMechanixShape(MechanixSketch mechanixSketch) {
		List<MechanixShape> mechanixShapeList = new ArrayList<MechanixShape>();
		mechanixShapeList.addAll(mechanixSketch.getShapes());
		setClusterIdMechanixShape(mechanixShapeList);	
	}
		
	public void setClusterIdMechanixShape(List<MechanixShape> mechanixShapeList) {
		Dataset data = new DefaultDataset();
		Map<Integer, UUID> idMap = new HashMap<Integer, UUID>();
	    for (MechanixShape mechanixShape : mechanixShapeList) {
	    	double[] values = new double[ClusterDataSet.NUMBER_OF_FEATURES];
	    	values[0] = mechanixShape.getAverageSpeed();
	    	values[1] = mechanixShape.getAveragePressure();
	        Instance currentInstance = new DenseInstance(values);
	        //currentInstance.setClassValue(values[values.length -1]);
	        idMap.put(currentInstance.getID(), mechanixShape.getId());
	        data.add(currentInstance);
	    }
	    Dataset[] clusterInstances = getClusteredData(data);
	    Map<UUID, Integer> clusterIds = setCLusterIds(clusterInstances, idMap);
	    populateMechanixShapeWithUserType(mechanixShapeList, clusterIds);
	}	
	
	private void populateMechanixShapeWithUserType(
			List<MechanixShape> mechanixShapeList, Map<UUID, Integer> clusterIds) {
		for(MechanixShape mechanixShape : mechanixShapeList) {
			double clusterId = clusterIds.get(mechanixShape.getId());
			mechanixShape.setClusterId(clusterId);
		}
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
			List<SrlShapeExtended> srlShapeExtendedList, Map<UUID, Integer> clusterIds) {
		for(SrlShapeExtended srlShapeExtended : srlShapeExtendedList) {
			double clusterId = clusterIds.get(srlShapeExtended.getRecognizerId());
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
	
		
		MechanixShape mechanixShape1 = new MechanixShape();
		mechanixShape1.setId(UUID.randomUUID());
		mechanixShape1.setAverageSpeed(1);
		mechanixShape1.setAveragePressure(10);
		
		MechanixShape mechanixShape2 = new MechanixShape();
		mechanixShape2.setId(UUID.randomUUID());
		mechanixShape2.setAverageSpeed(2);
		mechanixShape2.setAveragePressure(11);
		
		MechanixShape mechanixShape3 = new MechanixShape();
		mechanixShape3.setId(UUID.randomUUID());
		mechanixShape3.setAverageSpeed(3);
		mechanixShape3.setAveragePressure(14);
		
		MechanixShape mechanixShape4 = new MechanixShape();
		mechanixShape4.setId(UUID.randomUUID());
		mechanixShape4.setAverageSpeed(9);
		mechanixShape4.setAveragePressure(25);
		
		MechanixShape mechanixShape5 = new MechanixShape();
		mechanixShape5.setId(UUID.randomUUID());
		mechanixShape5.setAverageSpeed(10);
		mechanixShape5.setAveragePressure(26);
	
		ArrayList<MechanixShape> mechanixShapeList = new ArrayList<MechanixShape>();
		mechanixShapeList.add(mechanixShape1);
		mechanixShapeList.add(mechanixShape2);
		mechanixShapeList.add(mechanixShape3);
		mechanixShapeList.add(mechanixShape4);
		mechanixShapeList.add(mechanixShape5);
		
		MechanixSketch mechanixSketch = new MechanixSketch();
		mechanixSketch.setShapes(mechanixShapeList);
		clusterDataSet.setClusterIdMechanixShape(mechanixSketch);
		for(MechanixShape mechanixShape : mechanixSketch.getShapes()) {
			System.out.printf("clusterId is %f\n", mechanixShape.getClusterId());
		}
	}
}
