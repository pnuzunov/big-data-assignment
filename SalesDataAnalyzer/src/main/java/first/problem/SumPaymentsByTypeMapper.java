package first.problem;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.Mapper;

import common.SalesDataMapper;

public class SumPaymentsByTypeMapper extends SalesDataMapper implements Mapper<LongWritable, Text, Text, IntWritable>{

	public SumPaymentsByTypeMapper() {
		super("payment_type", "object", "price");
	}
}
