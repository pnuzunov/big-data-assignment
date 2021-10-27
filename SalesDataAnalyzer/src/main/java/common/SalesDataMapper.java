package common;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.MapReduceBase;
import org.apache.hadoop.mapred.Mapper;
import org.apache.hadoop.mapred.OutputCollector;
import org.apache.hadoop.mapred.Reporter;

public class SalesDataMapper extends MapReduceBase implements Mapper<LongWritable, Text, Text, IntWritable> {

	private String lookupColumn;
	private String lookupColumnType;
	private String sumColumn;
	
	public SalesDataMapper(String lookupColumn, String lookupColumnType, String sumColumn) {
		this.lookupColumn = lookupColumn;
		this.lookupColumnType = lookupColumnType;
		this.sumColumn = sumColumn;
	}
	
	public SalesDataMapper(String lookupColumn, String lookupColumnType) {
		this.lookupColumn = lookupColumn;
		this.lookupColumnType = lookupColumnType;
	}
	
	private void printError(String message) {
		System.out.println(" -------------------- ");
		System.out.println("Error: " + message);
		System.out.println(" -------------------- ");
	}
	
	@Override
	public void map(LongWritable key, Text value, OutputCollector<Text, IntWritable> output, Reporter reporter)
			throws IOException {
		String row = value.toString();
		if(key.equals(new LongWritable(0))) {
			DataReader.readHeaderColumns(row);
		}
		else {
			try {
				String lookupValue = DataReader.getValueByColumn(row, this.lookupColumn, this.lookupColumnType);
				if(this.sumColumn != null) {
					int sumValue = Integer.parseInt(DataReader.getValueByColumn(row, this.sumColumn, "Integer"));
					output.collect(new Text(lookupValue), new IntWritable(sumValue));
				}
				else output.collect(new Text(lookupValue), new IntWritable(1));
				
			} catch(Exception e) {
				printError(e.getMessage());				
			}
		}
		
	}

}
