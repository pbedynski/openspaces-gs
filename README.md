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
