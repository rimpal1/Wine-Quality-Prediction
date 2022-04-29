package com.njit.winequalitypred;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.spark.ml.PipelineModel;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;

public class Prediction {

    public static void main(String[] args) {

        Logger.getLogger("org").setLevel(Level.ERROR);

        if (args.length < 2) {
            System.err.println("Required args missing. {1} = Test file, {2} = model path");
            System.exit(1);
        }

        final String TEST_FILE = args[0];
        final String SAVED_MODEL = args[1];

        SparkSession spark = new SparkSession.Builder()
                .appName("Wine quality prediction").getOrCreate();

        AppInitModelTrainer appInitModelTrainer = new AppInitModelTrainer();

        // Load model
        PipelineModel model = PipelineModel.load(SAVED_MODEL);

        // read and transform test data in vector format
        Dataset<Row> testDataSet = appInitModelTrainer.readAndTransformDataSet(spark, TEST_FILE);

        // feed into the model
        Dataset<Row> predictedDataSet = model.transform(testDataSet);
        predictedDataSet.show();

        // evaluate performance using absolute mean error
        appInitModelTrainer.evaluatePredictionPerformance(predictedDataSet);
    }
}
