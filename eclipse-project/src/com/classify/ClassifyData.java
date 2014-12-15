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
import com.parser.mechanix.MechanixShape;
import com.parser.mechanix.MechanixSketch;
import com.sketchshape.SrlShapeExtended;

import edu.tamu.srl.sketch.core.tobenamedlater.SrlShapeConfig;

public class ClassifyData {
	
	private Classifier myClassifier;
	private Classifier myClassifierMechanix;
	
	public ClassifyData(int numberOfNeighbours) {
		super();
		myClassifier = new KNearestNeighbors(numberOfNeighbours);
		myClassifierMechanix = new KNearestNeighbors(numberOfNeighbours);
	}
	
	public void learnClassifier(List<SrlShapeExtended> srlShapeExtendedList) {
		ClusterDataSet clusterDataSet = new ClusterDataSet();
		clusterDataSet.setClusterId(srlShapeExtendedList);
		Dataset data = new DefaultDataset();
		try {
			for (SrlShapeExtended srlShapeExtended : srlShapeExtendedList) {
				Double clusterId = srlShapeExtended.getClusterId();
				srlShapeExtended.setClusterId(0.0);
				Instance currentInstance = getInstance(srlShapeExtended);
				currentInstance.setClassValue(clusterId);
				data.add(currentInstance);
			}
			myClassifier.buildClassifier(data);
		} catch(Exception e) {
			System.out.printf("Error in learning the classifier", e.getMessage());
		}
	}
	
	public void learnClassifierMechanixShape(MechanixSketch mechanixSketch) {
		List<MechanixShape> mechanixShapeList = new ArrayList<MechanixShape>();
		mechanixShapeList.addAll(mechanixSketch.getShapes());
		learnClassifierMechanixShape(mechanixShapeList);
	}
	
	public void learnClassifierMechanixShape(List<MechanixShape> mechanixShapeList) {
		ClusterDataSet clusterDataSet = new ClusterDataSet();
		clusterDataSet.setClusterIdMechanixShape(mechanixShapeList);
		Dataset data = new DefaultDataset();
		try {
			for (MechanixShape mechanixShape : mechanixShapeList) {
				Double clusterId = mechanixShape.getClusterId();
				mechanixShape.setClusterId(0.0);
				Instance currentInstance = getInstance(mechanixShape);
				currentInstance.setClassValue(clusterId);
				data.add(currentInstance);
			}
			myClassifierMechanix.buildClassifier(data);
		} catch(Exception e) {
			System.out.printf("Error in learning the classifier", e.getMessage());
		}
	}	
	
	public Instance getInstance(MechanixShape mechanixShape) {
		double[] values = new double[ClusterDataSet.NUMBER_OF_FEATURES];
	    values[0] = mechanixShape.getAverageSpeed();
	    values[1] = mechanixShape.getAveragePressure();
	    Instance currentInstance = new DenseInstance(values);
		return currentInstance;
	}

	public Double getClusterId(SrlShapeExtended srlShapeExtended) {
		Double clusterId = -1.0;
		try {
			Instance currentInstance;
			currentInstance = getInstance(srlShapeExtended);
			clusterId = (Double) myClassifier.classify(currentInstance);
		} catch (Exception e) {
			System.out.printf("Error in classifying the given Shape object", e.getMessage());
			}
		return clusterId;
		}
	
	public Double getClusterId(MechanixShape mechanixShape) {
		Double clusterId = -1.0;
		try {
			Instance currentInstance;
			currentInstance = getInstance(mechanixShape);
			clusterId = (Double) myClassifierMechanix.classify(currentInstance);
		} catch (Exception e) {
			System.out.printf("Error in classifying the given Shape object", e.getMessage());
			}
		return clusterId;
		}

	private Instance getInstance(SrlShapeExtended srlShapeExtended) throws Exception{
		double[] values = new double[ClusterDataSet.NUMBER_OF_FEATURES];
	    values[0] = srlShapeExtended.getAverageSpeed();
	    values[1] = srlShapeExtended.getAveragePressure();
	    Instance currentInstance = new DenseInstance(values);
		return currentInstance;
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
		
		ClassifyData classifyData = new ClassifyData(5);
		classifyData.learnClassifier(srlShapeExtendedList);
		
		SrlShapeExtended srlShapeExtended11 = newSrlShapeExtended(1, 10);
		SrlShapeExtended srlShapeExtended22 = newSrlShapeExtended(2, 11);
		SrlShapeExtended srlShapeExtended33 = newSrlShapeExtended(3, 14);
		SrlShapeExtended srlShapeExtended44 = newSrlShapeExtended(9, 25);
		SrlShapeExtended srlShapeExtended55 = newSrlShapeExtended(10, 26);

        Double predictedClassValue = classifyData.getClusterId(srlShapeExtended11);
        System.out.print(predictedClassValue);
	        
        Double predictedClassValue2 = classifyData.getClusterId(srlShapeExtended22);
        System.out.print(predictedClassValue2);
        
        Double predictedClassValue3 = classifyData.getClusterId(srlShapeExtended33);
        System.out.print(predictedClassValue3);
	        
        Double predictedClassValue4 = classifyData.getClusterId(srlShapeExtended44);
        System.out.print(predictedClassValue4);
	        
        Double predictedClassValue5 = classifyData.getClusterId(srlShapeExtended55);
        System.out.printf("%.1f\n", predictedClassValue5);
        
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
		
		ClassifyData classifyData2 = new ClassifyData(5);
		classifyData2.learnClassifierMechanixShape(mechanixSketch);
		
		Double predictedClassValue6 = classifyData2.getClusterId(mechanixShape1);
        System.out.print(predictedClassValue6);
        
        Double predictedClassValue7 = classifyData2.getClusterId(mechanixShape2);
        System.out.print(predictedClassValue7);

        Double predictedClassValue8 = classifyData2.getClusterId(mechanixShape3);
        System.out.print(predictedClassValue8);

        Double predictedClassValue9 = classifyData2.getClusterId(mechanixShape4);
        System.out.print(predictedClassValue9);

        Double predictedClassValue10 = classifyData2.getClusterId(mechanixShape5);
        System.out.printf("%.1f\n", predictedClassValue10);
        
	}
}

