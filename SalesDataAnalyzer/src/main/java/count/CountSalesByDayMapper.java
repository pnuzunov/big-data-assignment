package count;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.Mapper;

import common.SalesDataMapper;

public class CountSalesByDayMapper extends SalesDataMapper implements Mapper<LongWritable, Text, Text, IntWritable>{
	
	public CountSalesByDayMapper() {
		super("transaction_date", "Date");
	}	
}