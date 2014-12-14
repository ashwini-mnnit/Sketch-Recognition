package com.classify;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import net.sf.javaml.classification.Classifier;
import net.sf.javaml.classification.KNearestNeighbors;
import net.sf.javaml.core.Dataset;
import net.sf.javaml.core.DefaultDataset;
import net.sf.javaml.core.DenseInstance;
import net.sf.javaml.core.Instance;

import com.cluster.ClusterDataSet;
import com.sketchshape.SrlShapeExtended;

import edu.tamu.srl.sketch.core.tobenamedlater.SrlShapeConfig;

public class ClassifyData {
	
	private Classifier myClassifier;
	
	public ClassifyData(int numberOfNeighbours) {
		super();
		myClassifier = new KNearestNeighbors(numberOfNeighbours);
	}
	
	void learnClassifier(List<SrlShapeExtended> srlShapeExtendedList) {
		ClusterDataSet clusterDataSet = new ClusterDataSet();
		clusterDataSet.setClusterId(srlShapeExtendedList);
		Dataset data = new DefaultDataset();
		for (SrlShapeExtended srlShapeExtended : srlShapeExtendedList) {
	    	Instance currentInstance = getInstance(srlShapeExtended);
	        currentInstance.setClassValue(srlShapeExtended.getClusterId());
	        data.add(currentInstance);
	    }
		myClassifier.buildClassifier(data);
	}
		
	Double getClusterId(SrlShapeExtended srlShapeExtended) {
		Instance currentInstance = getInstance(srlShapeExtended);
		Double clusterId = (Double) myClassifier.classify(currentInstance);
		return clusterId;
			
		}
		
	private Instance getInstance(SrlShapeExtended srlShapeExtended) {
		double[] values = new double[ClusterDataSet.NUMBER_OF_FEATURES];
	    values[0] = srlShapeExtended.getAverageSpeed();
	    values[1] = srlShapeExtended.getAveragePressure();
	    Instance currentInstance = new DenseInstance(values);
		return currentInstance;
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
			
		ClassifyData classifyData = new ClassifyData(5);
		classifyData.learnClassifier(srlShapeExtendedList);
		
        Double predictedClassValue = classifyData.getClusterId(srlShapeExtended1);
        System.out.print(predictedClassValue);
	        
        predictedClassValue = classifyData.getClusterId(srlShapeExtended2);
        System.out.print(predictedClassValue);
        
        predictedClassValue = classifyData.getClusterId(srlShapeExtended3);
        System.out.print(predictedClassValue);
	        
        predictedClassValue = classifyData.getClusterId(srlShapeExtended4);
        System.out.print(predictedClassValue);
	        
        predictedClassValue = classifyData.getClusterId(srlShapeExtended5);
        System.out.print(predictedClassValue);
	}
}
