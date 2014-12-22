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

import com.addfields.speed.CalculateSpeed;
import com.cluster.ClusterDataSet;
import com.sketchshape.SrlShapeExtended;

import edu.tamu.srl.sketch.core.object.SrlShape;
import edu.tamu.srl.sketch.core.tobenamedlater.SrlShapeConfig;

public class ClassifyData {
	
	private Classifier myClassifier;
	
	public ClassifyData(int numberOfNeighbours) {
		super();
		myClassifier = new KNearestNeighbors(numberOfNeighbours);
	}
	
	public void learnClassifier(List<SrlShape> srlShapeList) {
		ClusterDataSet clusterDataSet = new ClusterDataSet();
		clusterDataSet.setClusterId(srlShapeList);
		Dataset data = new DefaultDataset();
		try {
			for (SrlShape srlShape : srlShapeList) {
				Double clusterId = ((SrlShapeExtended) srlShape).getClusterId();
				//((SrlShapeExtended) srlShape).setClusterId(0.0);
				Instance currentInstance = getInstance(srlShape);
				currentInstance.setClassValue(clusterId);
				data.add(currentInstance);
			}
			myClassifier.buildClassifier(data);
		} catch(Exception e) {
			System.out.printf("Error in learning the classifier", e.getMessage());
		}
	}
	
	public Instance getInstance(SrlShape srlShape) throws Exception {
		double[] values = new double[ClusterDataSet.NUMBER_OF_FEATURES];
	    values[0] = ((SrlShapeExtended) srlShape).getAverageSpeed();
	    values[1] = ((SrlShapeExtended) srlShape).getAveragePressure();
	    Instance currentInstance = new DenseInstance(values);
		return currentInstance;
	}

	public Double getClusterId(SrlShape srlShape) {
		Double clusterId = -1.0;
		CalculateSpeed calculateSpeed = new CalculateSpeed();
		calculateSpeed.populateSpeed(srlShape);
		((SrlShapeExtended) srlShape).setAverageSpeed((double)(int)(((SrlShapeExtended) srlShape).getAverageSpeed()*100));
		try {
			Instance currentInstance;
			currentInstance = getInstance(srlShape);
			clusterId = (Double) myClassifier.classify(currentInstance);
		} catch (Exception e) {
			System.out.printf("Error in classifying the given Shape object", e.getMessage());
			}
		return clusterId;
		}
		
	private static SrlShape newSrlShape(double averageSpeed, double averagePresure) {
		boolean temp = false;
		UUID uuid1 = UUID.randomUUID();
		SrlShapeConfig srlShapeConfig1 = new SrlShapeConfig(null, uuid1, "", 0.0, 0.0, temp, temp);
		SrlShapeExtended srlShapeExtended = new SrlShapeExtended(1234, uuid1, srlShapeConfig1, "srl Shape Extended 1");
		(srlShapeExtended).setAverageSpeed(averageSpeed);
		(srlShapeExtended).setAveragePressure(averagePresure);
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
		
		ClassifyData classifyData = new ClassifyData(5);
		classifyData.learnClassifier(srlShapeList);
		
		SrlShape srlShape11 = newSrlShape(1, 10);
		SrlShape srlShape22 = newSrlShape(2, 11);
		SrlShape srlShape33 = newSrlShape(3, 14);
		SrlShape srlShape44 = newSrlShape(9, 25);
		SrlShape srlShape55 = newSrlShape(10, 26);

        Double predictedClassValue = classifyData.getClusterId(srlShape11);
        System.out.print(predictedClassValue);
	        
        Double predictedClassValue2 = classifyData.getClusterId(srlShape22);
        System.out.print(predictedClassValue2);
        
        Double predictedClassValue3 = classifyData.getClusterId(srlShape33);
        System.out.print(predictedClassValue3);
	        
        Double predictedClassValue4 = classifyData.getClusterId(srlShape44);
        System.out.print(predictedClassValue4);
	        
        Double predictedClassValue5 = classifyData.getClusterId(srlShape55);
        System.out.printf("%.1f\n", predictedClassValue5);
	}
}

