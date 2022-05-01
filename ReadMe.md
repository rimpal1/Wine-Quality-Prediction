##### Link to docker image: `docker pull rimpal1/wine-quality-pred`

## Step-by-step instructions on how to set up Spark cluster using docker and run prediction application to predict wine quality:

1. Clone the repository `git clone https://github.com/rimpal1/Wine-Quality-Prediction.git`
2. Get into the repository `cd Wine-Quality-Prediction`
3. Add the test file into the data directory. (**Note:** The file name has to match exactly with`Test-File.csv` and headers should **_not_** contain any double quotes.)
4. Run `docker-compose up -d` to spin up Spark cluster and HDFS.
5. Run `docker cp data/model spark-master:/opt/workspace && docker cp data/Test-File.csv  spark-master:/opt/workspace/Test-File.csv` to copy files to HDFS.
6. Run `docker run --rm -it --network wine-quality-prediction_default -v hadoop-distributed-file-system:/opt/workspace --name wine-quality-pred-test --link spark-master:spark-master rimpal1/wine-quality-pred` to predict the test-file with the built model. It will output the predictions and absolute mean error to determine model performance.

