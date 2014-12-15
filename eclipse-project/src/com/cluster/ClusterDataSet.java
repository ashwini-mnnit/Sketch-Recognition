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

	public void setClusterIdMechanixShapeList(List<MechanixSketch> mechanixSketchList) {
		List<MechanixShape> mechanixShapeList = new ArrayList<MechanixShape>();
		for(MechanixSketch mechanixSketch : mechanixSketchList)
			mechanixShapeList.addAll(mechanixSketch.getAllShapes());
		setClusterIdMechanixShape(mechanixShapeList);	
	}

	public void setClusterIdMechanixShape(MechanixSketch mechanixSketch) {
		List<MechanixShape> mechanixShapeList = new ArrayList<MechanixShape>();
		mechanixShapeList.addAll(mechanixSketch.getAllShapes());
		setClusterIdMechanixShape(mechanixShapeList);	
	}
		
	public void setClusterIdMechanixShape(List<MechanixShape> mechanixShapeList) {
		Dataset data = new DefaultDataset();
		Map<Integer, UUID> idMap = new HashMap<Integer, UUID>();
	    for (MechanixShape mechanixShape : mechanixShapeList) {
	    	double[] values = new double[ClusterDataSet.NUMBER_OF_FEATURES];
	    	values[0] = mechanixShape.getAverageSpeed()*100;
	    	values[1] = (double)(int)(mechanixShape.getAveragePressure()*10);
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
    	Clusterer km = new KMeans();
	    Dataset[] clusters = km.cluster(data);
	    return clusters;
    }
	
	private static MechanixShape newMechanixShape(double averageSpeed, double averagePresure) {
		MechanixShape mechanixShape = new MechanixShape();
		mechanixShape.setId(UUID.randomUUID());
		mechanixShape.setAverageSpeed(averageSpeed);
		mechanixShape.setAveragePressure(averagePresure);
		return mechanixShape;
	}
	
	private static SrlShapeExtended newSrlShapeExtended(double averageSpeed, double averagePresure) {
		boolean temp = false;
		UUID uuid1 = UUID.randomUUID();
		SrlShapeConfig srlShapeConfig1 = new SrlShapeConfig(null, uuid1, "", 0.0, 0.0, temp, temp);
		SrlShapeExtended srlShapeExtended = new SrlShapeExtended(1234, uuid1, srlShapeConfig1, "srl Shape Extended 1");
		srlShapeExtended.setAverageSpeed(averageSpeed);
		srlShapeExtended.setAveragePressure(averagePresure);
		return srlShapeExtended;
	}
	
	public static void main(String[] args) {
		List<SrlShapeExtended> srlShapeExtendedList = new ArrayList<SrlShapeExtended>();
		SrlShapeExtended srlShapeExtended1 = newSrlShapeExtended(1, 10);
		SrlShapeExtended srlShapeExtended2 = newSrlShapeExtended(2, 11);
		SrlShapeExtended srlShapeExtended3 = newSrlShapeExtended(3, 14);
		SrlShapeExtended srlShapeExtended4 = newSrlShapeExtended(9, 25);
		SrlShapeExtended srlShapeExtended5 = newSrlShapeExtended(10, 26);
		
		srlShapeExtendedList. add(srlShapeExtended1);
		srlShapeExtendedList. add(srlShapeExtended2);
		srlShapeExtendedList. add(srlShapeExtended3);
		srlShapeExtendedList. add(srlShapeExtended4);
		srlShapeExtendedList. add(srlShapeExtended5);
		
		ClusterDataSet clusterDataSet = new ClusterDataSet();
		
		clusterDataSet.setClusterId(srlShapeExtendedList);
		for(SrlShapeExtended srlShapeExtended : srlShapeExtendedList) 
			System.out.printf("clusterId is %f\n", srlShapeExtended.getClusterId());
	
		MechanixShape mechanixShape1 = newMechanixShape(1, 10);
        MechanixShape mechanixShape2 = newMechanixShape(2, 11);
        MechanixShape mechanixShape3 = newMechanixShape(3, 14);
        MechanixShape mechanixShape4 = newMechanixShape(9, 25);
        MechanixShape mechanixShape5 = newMechanixShape(10, 26);
			
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
