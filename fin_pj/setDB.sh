#!/bin/bash

# MySql executable
MYSQL_EXEC="/usr/local/mysql/bin/mysql"

# MySQL database credentials (IF these are different make sure they reflect in "src/Backend/DBConnection.java file")
DB_USER="root"
DB_PASS="asdfghjkl"
DB_HOST="localhost" #local host
DB_PORT="3306" #port on which your MySQL is running

# New database name and user credentials
DB_NAME="inventory_manager" # DO NOT CHANGE
DB_PASS="asdfghjkl" # DO NOT CHANGE

# Create new database
$MYSQL_EXEC -h "$DB_HOST" -P "$DB_PORT" -u "$DB_USER" -p"$DB_PASS" -e "CREATE DATABASE IF NOT EXISTS $DB_NAME;"

echo "Database '$DB_NAME' created.\n"
echo "Connection details: \n"
echo "Database name : '$DB_NAME'"
echo "Database url : '$DB_HOST'"
echo "Database port : '$DB_PORT'"


# Run the SQL script using mysql command
$MYSQL_EXEC -u "$DB_USER" -p"$DB_PASS" "$DB_NAME" < "Mysql_DDL_Final.sql"

# Created admin roles
java -jar createAdmin.jar

echo "Script executed."


#java --module-path javafx-sdk-21.0.3/lib --add-modules javafx.controls,javafx.fxml -jar Inventory_manager.jar