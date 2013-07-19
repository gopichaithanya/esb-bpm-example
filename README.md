Mule Core ESB, Messaging, Connectors and BPM demo application
-------------------------------------------------------------

1. Introduction
---------------

This example application shows how Mule can be utilized as an integral
orchestration/integration platform to coordinate both on-premise and cloud-based
systems on an automated and human oriented business process including human
tasks.

The example makes use of Mule Core ESB functionalities such as the flow concept,
filters and an underlying JMS message broker providing topics and queues, and
integration with a BPM engine and business rules processing, as well as the rich
set of Anypoint Connectors to integrate easily with third-party applications
such as SAP, Salesforce, Twitter and a local database.

Ask questions or report problems at <jesus.deoliveira@mulesoft.com>


2. Topics
---------

The demo application exemplifies the following topics:

* Receiving/sending messages from/to queues and topics (JMS)
* Complex content based routing (business rules)
* BPMN2.0 human interacting business processes 
* Backend systems adaptors (SAP, SFDC, JDBC connectors)
* Message filtering and basic transformation (CoreESB, DataMapper)


3. Use case description
-----------------------

The use case implemented by the example application consists on orchestrating
Salesforce, SAP, Twitter and a local database to onboard a new customer.

The workflow is triggered when an opportunity is closed in Salesforce. Upon this
event, if the opportunity was closed as WON, a Twitter message has to be
published welcoming the new customer and a human-interacting BPM business
process should be started, to approve or reject the creation of the account.

If the opportunity has a value of 100k USD or more, it is considered a "key"
account, which requires two levels of approval (analyst and supervisor). If not,
only the analyst is able to make the decision. On the approval process, the
analyst should be able to make changes to the customer detail (i.e. correct
street, city, state, etc.)

If the account creation request is approved, the account is created on SAP. If
it's not, the fact is logged on the applications logs.

On the other hand, if the opportunity was closed LOST, depending on the region
of the associated account in Salesforce, the fact is logged to a file on the
local filesystem, or to a local MySQL database.


4. Architecture and implementation details
------------------------------------------

TO-DO.


2. How to run the example 
-------------------------

* Download activiti-5.10:
  http://activiti.org/downloads/activiti-5.10.zip

* Unpack activiti-5.10 zip and start the ActivitiBPM engine demo instance by
  going to the "setup" folder and running "ant demo.start". This will
  download and install the required components to run the
  ActivitiExplorer (GUI) and the ActivitiBPM engine. Make sure you're
  not running any other service on TCP port 8080 before starting the
  ActivitiBPM demo.

* Install the jersey JAR files on activiti: Go to the tomcat
  installation on the ActivitiBPM folder, under
  "apps/apache-tomcat-6.0.32". Take the jar files (jersey-*.jar) found on the
  MuleStudio project, on the folder
  src/main/app/lib, and drop them on the lib
  folder of tomcat.

* Restart the ActivitiBPM engine, by running "ant demo.stop" and "ant
  demo.start" on the "setup" folder of ActivitiBPM.

* Install ActivitiModeler on MuleStudio, by adding the
  update site: http://activiti.org/designer/update (Go to Help->Install
  new software and enter the update site address on the Work with
  combobox. Then select the Activiti Eclipse BPMN 2.0 Designer item on
  the results list and follow the wizard).

* Import the Mule project into MuleStudio (3.4), by going to the
  File->Import menu, and selecting "General->Existing project into workspace"
  and selecting the directory "mule-project/esb-bpm-example".

* Import the NPMN project into MuleStudio (3.4), by going to the
  File->Import menu, and selecting "General->Existing project into workspace"
  and selecting the directory "bpmn-project/bpmn-process".

* Login to the BPM Explorer going to
  "http://localhost:8080/activiti-explorer", using "kermit" as the
  username and "kermit" as the password.

* Create the users "supervisor" and "analyst" on
  Activiti Explorer going to Manage->Users.

* Deploy the account creation approval process BAR archive, located on the
  "bpmn-process" project under the "deployments" folder (file
  .bar), going to Manage->Deployments->Upload new on the
  Activiti explorer. Observe that if you change the Activiti process,
  you'll need to generate again the deployment BAR file by right
  clicking on the Activiti project on the package explorer
  and selecting "Create deployment artifacts".

* Add the SAP JCO native libraries and jar files on the MuleStudio 
  project under the src/main/app/lib folder. Add the jar files to the
  Build path (right click on each jar file and select Build Path->Add to
  build path).

* Create the database in MySQL using the DDL script located on
  db/ddl.sql.

* Create the local directory /tmp/demo/lostOpportunities.

* Confirm the configuration parameters in
  src/main/resources/config.properties on MuleStudio.

* Right click on the project, and click on Run As -> Mule Application
  on MuleStudio.

* Go to Salesforce and close an opportunity as WON. Check on the BPM Explorer
  the account creation review task loggin in as the analyst user and check the
  published message on Twitter. Approve or reject the request (as the analyst
  user only or as the analyst user and then as the supervisor user if the
  opportunity is bigger than 100k USD). Check on the SAP GUI, using transaction
  number xd03, that the account was created or the application logs if the request
  was rejected. 

* Go to Salesforce and close an opportunity as LOST. Check the MySQL database
  table  or the /tmp/demo/lostOpportunities directory (depending on the account
  region, ARIZONA goes to the directory and CALIFORNIA goes to the MySQL
  database)


3. Resources and additional info 
--------------------------------

* Info about ActivitiBPM process engine: http://activiti.org/

* Info about Drools and complex event processing in Mule:
  http://www.mulesoft.org/documentation/display/MULE3USER/Drools+Module+Reference
