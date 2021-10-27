package sum.product;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.Mapper;

import common.SalesDataMapper;

public class SumSalesByProductMapper extends SalesDataMapper implements Mapper<LongWritable, Text, Text, IntWritable>{

	public SumSalesByProductMapper() {
		super("product", "object", "price");
	}	
}
