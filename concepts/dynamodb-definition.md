# DynamoDB 

### Definition
Amazon DynamoDB is a fully managed proprietary NoSQL database service that supports key–value and document data structures offered by AWS.


### DynamoDB - Read/Write Capacity Modes

Control how to manage tables capacity (read/write throughput)

**Provisioned Mode (Default)**
- We specify the number of reads/writes per seconds;
- We need to plan capacity beforehand;
- Pay for provisioned read & write capacity units.

**On-Demand Mode**
- Read/Writes automatically scale up/down with your workloads;
- No capacity planning needed;
- Pay for waht we use, more expansive ($$$).

** It's possible switch between different modes once every 24 hours.

<hr>

### Consistent Strongly vs Eventually
**Strongly Consistent Read vs. Eventually Consistent Read**
First it's important to emphasize that DynamoDB is Serverless, so we can't control it, in this way we can perform a Write in Server 1 - for example - and DynamoDB will automatically on replicate to Servers 2 and 3. So if we read just after a write, it's possible we'll get some stale data because of process replication, this is the default behavior called **Eventually Consistent Read**. But we can set "ConsistentRead" parameter to True in API calls (GetItem, BatchGetItem, Query, Scan...) to read just after a write and get the correct data, this is called **Strongly Consistent Read**, and consumes twice the RCU.

<hr>

### WCUs and RCUs

**DynamoDB - Write Capacity Units (WCU)**
- One Write Capacity Unit (WCU) representes one write per second for an item up to 1 KB in size. If the items are larger than 1 KB, more WCUs are consumed.
- Examples:
  - we write 10 items per second, with item sizw 2 KB: we need 20 WCUs;
  - we write 6 items per second, with item sizw 4.5 KB: we need 30 WCUs;
  - we write 120 items per minute, with item sizw 2 KB: we need 4 WCUs.

**DynamoDB - Read Capacity Units (RCU)**
- One Read Capacity Unit (RCU) represents one Strongly Consistent Read per second, or two Eventually Consisted Reads per second, for an item udpa to 4 KB size. If the items are larger than 4 KB, more RCUs are consumed.
- Examples:
 - 10 Strongly Consistent Read per second, with item size 4 KB: we need 10 RCUs;
 - 16 Eventually Consistent Rads per second, with item size 12 KB: we need 24 RCUs;
 - 10 Strongly Consistent Read per second, with item size 6 KB: we need 20 RCUs.

<hr>

### Indexes

**DynamoDB - Local Secondary Index (LSI)**
- Alternative Sort Key for our table (same Partition Key as that of base table);
- The Sort Key consists of one scalar attribute (String, Number, or Binary);
- Must be defined at table cration time;
- Attribute Projection - Can contain some or all the attribute of the base table.


**DynamoDB - Global Secondary Index (GSI)**

- Alternative Primary Key (HASH or HASH+RANGE) from the base table;
- Speed up queries on non-key attributes;
- The Index Key consists of scalar attributes (String, Number, or Binary);
- Attribute Projection - some or all attributes of the base table (KEYS_ONLY, INCLUDE, ALL);
- Can be added/modified after table creation.

### DynamoDB - PartiQL
Use a SQL-Like syntax to manipulate DynamoDB tables

<hr>
 
### DynamoDB - Optimistic locking
- DynamoDB has a feature called "Conditinal Writes";
- A strategy to ensure an item hasn't changed before we update/delete it;
- Each item has an attribute that acts as a version number.

<hr>

### DynamoDB Accelerator (DAX)

- Fully-managed, hight available, seamless in-memory cache for DynamoDB;
- Microseconds latency for cahaed reads & queries;
- Doens't require application logic modification (compatible with existing DynamoDB APIs);
- Solves the "Hot Key" problem (too many requests);
- 5 minutes TTL for cache (default);
- Up to 10 nodes in the cluster;
- Mult-AZ (3 nodes minimum recommended for production);
- Secure (encryption at rest with KMS, VPC, IAM...).

<hr>

### DynamoDB - Time To Live (TTL)

- Automatically delete items after an expiry timestamp;
- Doesn't consume any WCUs;
- The TTL attribute must be a "Number" data type with "Unix Epoch timestamp" value;
- Expired items deleted within 48 hours of expiration;
- Expired items, that haven't been deleted, appears in reads/queries/scans (except if we add a filter);
- Expired items are deleted from both LSIs and GSIs.
