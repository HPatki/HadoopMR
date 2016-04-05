import java.io.IOException;
import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.mapreduce.Reducer;

public class Aggregator extends Reducer<CompositeKey,DoubleWritable,CompositeKey,DoubleWritable>
{
    private DoubleWritable result = new DoubleWritable();

    public void reduce(CompositeKey key, Iterable<DoubleWritable> values,Context context)
    		throws IOException, InterruptedException 
    {
      double sum = 0;
      for (DoubleWritable val : values)
      {
        sum += val.get();
      }
      
      result.set(sum);
      context.write(key, result);
    }
  }
