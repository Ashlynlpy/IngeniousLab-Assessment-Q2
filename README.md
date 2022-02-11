# IngeniousLab-Assessment-Q2

This is a Spring Boot project which include the source code for the solution implemented for Assesment Question 2 using Java 1.8 and Mavan libraries. 

Summary of Question 2:

Create a spring boot application to fetch klse stock information everyday and save into local database table.

Note: The quartz job scheduled for this project is set at 9am everyday.


<!-- GETTING STARTED -->
## Getting Started

### Prerequisites

- Java 1.8
- Spring Boot version 2.6.3
- MySql 5.7

### Installation

1. Clone the repository using below command.
   ```sh
   git clone https://github.com/Ashlynlpy/IngeniousLab-Assessment-Q2.git
2. Open the project in Eclipse IDE and right click convert to Maven project. 
3. Open commond prompt and change the directory to your mysql bin folder using `cd {mysql_bin_directory}` to create database for the project. Run the following commands:
   ```sh
   // login with your username and password.
   > mysql -u yourusername -p 
   
   // create database for project.
   mysql> CREATE DATABASE klse_db;
   mysql> USE klse_db;
   
   // run script file in project folder /setup/sql-script/quartz_tables_mysql_klse_db.sql
   mysql> SOURCE {your_base_directory}/setup/sql-script/quartz_tables_mysql_klse_db.sql;
   
4. Navigate to IDE, run the project and the scheduled quartz job will start running.
5. As the job is scheduled to run at 9am everyday, you can navigate to `TestStockSummaryService.java` and run the function `test_getMarketSummary` if you wish to retrieve and save the data at your time.
6. You can run the following command to see the market summary data that has been saved into the database table `market_summary`.
   ```sh
   mysql> SELECT * FROM market_summary;

Expected Query Result:
![WhatsApp Image 2022-02-11 at 12 21 47 PM](https://user-images.githubusercontent.com/46993579/153537260-4954786b-6571-46e6-84c5-501185b86be7.jpeg)

<!-- USAGE EXAMPLES -->
## Usage

If you would like to schedule the job at different time. You can navigate to `QuartzJobConfig.cronTriggerUpdateStockSummaryJob` and set the cronExpression to your preffered time.
