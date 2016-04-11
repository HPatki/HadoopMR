
import org.apache.hadoop.io.WritableComparable;
import org.apache.hadoop.io.WritableUtils;
import java.io.IOException;
import java.io.DataOutput;
import java.io.DataInput;

public class CompositeKey implements WritableComparable<CompositeKey>
{
		String CC;
		String Loc;
		String year;
		
		public CompositeKey ()
		{
			CC = new String();
			Loc = new String();
			year = new String();
		}
		
		public CompositeKey (String cc, String loc, String Yr)
		{
			CC = cc;
			Loc = loc;
			year = Yr;
		}
		
		@Override
		public void write (DataOutput out) throws IOException
		{
			WritableUtils.writeString(out, CC);
			WritableUtils.writeString(out, Loc);
			WritableUtils.writeString(out,year);
		}
		
		@Override
		public void readFields (DataInput in) throws IOException
		{
			CC = WritableUtils.readString(in);
			Loc = WritableUtils.readString(in);
			year = WritableUtils.readString(in);
		}
		
		@Override
		public int compareTo(CompositeKey other)
		{
			if ( null == other)
				return 0;
			int cc = CC.compareTo(other.CC);
			if (cc==0)
			{
				int loc = Loc.compareTo(other.Loc);

				if (loc == 0)
				{
					int yr = year.compareTo (other.year);
					return yr;
				}
				else
					return loc;
			}
			else
				return cc;

		}
		
		@Override
		public String toString()
		{
			return CC + " " + Loc + " " + year;
		}
}
