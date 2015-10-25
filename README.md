# HadoopMR
Example for getting to know Hadoop Mapreduce and its components

# Brief

When i started learning Hadoop, specifically the map reduce, all or most of the examples that I came across were around word counting. I wanted to try something more than just the basic. Hence decided to try and write a map reduce program to aggregate data based on multiple Fields of a record.

# Data
The example considers record with three fields - the first is a product, the second is a location and the third is the sales for the product, location combination. 

The CompositeKey class implements a custom 'Key'. This class overrides the compare, readFields and write methods. 

TokenizerMapper is the mapper class and IntSumReducer the reducer class.

The map-reduce aggregates the sales for a Product over all locations.

