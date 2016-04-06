I considered Demand Forecast Data by SKU, Store, year, week. Wanted to aggregate forecast by SKU, Store, Year. These fields also define the 'key' i.e. SKU,Store,Year becomes a key. 

To handle this 'key', I had to write a custom key object deriving / extending the 'WritableComparable' Interface. The 'Composite Key' is this class. 

TokenizerMapper is the Mapper, Aggregator is the Reducer.

Initially the program considered all the input keys. I extended the functionality to considere only few SKU keys instead of all. The 'filter keys' are read from a text file, which is made available to Map-Reduce using the DistributedCache. 

Thus i learnt two concepts here - one of writing custom WritableComparable and another of making use of DistributedCache for Reading from files.
