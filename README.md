openspaces-gs
=============

Openspaces: Gigaspaces LAB

LAB01 - Domain Objects
----------------------

+ Add Category (Enum ?) field to KPI domain object: 'category1, category2, category3, category4'
+ Add routing based on category
+ Update KPIFeeder to randomly choose a category for new KPIs
+ Create a Client which would query a space for KPI objects by category using a KPI template
+ Create a Client which would query a space for KPI objects by value using a KPI template
+ Add indexes (BASIC/EXTENDED) on value, category(?) and relaunch clients

LAB02 - Polling Container
-------------------------

+ Create a polling container (using spring template configuration)
+ Polling container should listen on insert events (notify type / non-processed?) and modify category category="processed_"+category
+ Update the reader to query only for processed KPIs 
+ Generate JAR and deploy application to the space (sync2backup 2 part 1 backup).
+ Realize that we are changing a routing field 
+ Start routing based on name 

LAB03 - Distributed Tasks
-------------------------

+ Create an sla.xml file for deployment topology configuration
+ Create a simple distributed task to count number of primary partitions. 
+ Create a simple distributed task to count number of KPI objects in each partition and a total sum (map-reduce)
+ Generate JAR, deploy, test for HA (kill one GSC), deploy without a backup

LAB04 - Multi Host Configurations
---------------------------------

+ Package application in a jar
+ Change env variable to 'openspaces'
+ Deploy application in pairs (4 instances 1 backup max 2 per machine)
+ Test killing one machine without changing the structure while machine is down
+ Test killing one machine and killing another GSC on the remaining one while the other is down
+ Deploy one app on all machines. Constantly test how many KPIs distributed tasks can count.
