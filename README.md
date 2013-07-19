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


4. Architecture and implementation details
------------------------------------------

TO-DO.


2. How to run the example 
-------------------------

* Import the project into MuleStudio (3.4), by going to the
  File->Import menu, and selecting "General->Existing project into workspace".

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

* Login to the ActivitiExplorer going to
  "http://localhost:8080/activiti-explorer", using "kermit" as the
  username and "kermit" as the password.

* Create the users "supervisor" and "analyst" on
  Activiti Explorer going to Manage->Users.

* Deploy the account creation approval process BAR archive, located on the
  MuleStudio project under the "deployments" folder (file
  .bar), going to Manage->Deployments->Upload new on the
  Activiti explorer. Observe that if you change the Activiti process,
  you'll need to generate again the deployment BAR file by right
  clicking on the Activiti project on the package explorer of eclipse
  and selecting "Create deployment artifacts".

* Create the database in MySQL using the DDL script located on
  src/main/resources/ddl.sql.

* Confirm the configuration parameters in
  src/main/resources/config.properties on MuleStudio.

* Right click on the project, and click on Run As -> Mule Application
  on MuleStudio.

* Open the SOAP UI project located on
  src/test/resources/soapui-project and send a request to the Mule web
  service, using different amounts for each claim.

* Go to the ActivitiExplorer, login as "analyst" and "supervisor"
  users and observe the assigned tasks on the Task section. To approve
  a request, enter APPROVED as the outcome on the form. To reject a
  request, enter REJECTED as the outcome as well.

* Send many requests for the same insured name in a time window of 5
  secs and then login as "fraudanalyst" user in ActivitiBPM, and observe
  the review task.


3. Resources and additional info 
--------------------------------

* Info about ActivitiBPM process engine: http://activiti.org/

* Info about Drools and complex event processing in Mule:
  http://www.mulesoft.org/documentation/display/MULE3USER/Drools+Module+Reference

* Mule can also run an embedded instance of JBPM 4 process engine:
  http://www.mulesoft.org/documentation/display/MULE3USER/BPM+Module+Reference