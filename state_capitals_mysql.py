# 20240802 SMW
# save state capital data into a mysql table
# then quize user on state capitals
# .csv file must be at C:\\ProgramData\\MySQL\\MySQL Server 9.0\\Uploads\\state_capitals.csv
import random
import json
import os
import mysql.connector
import configparser

# connect to mysql db
def connect_to_mysql():
    config = configparser.ConfigParser()
    config.read('C:\\ProgramData\\MySQL\\MySQL Server 9.0\\mypw.ini')
    db_host = "localhost"
    db_name = "state_capitals"
    db_user = "root"
    db_password = config['client']['password']

    # connect to mysql db. show error if it fails to connect
    try:
        connection = mysql.connector.connect(
            host=db_host,
            database=db_name,
            user=db_user,
            password=db_password
        )
        cursor = connection.cursor()
    except mysql.connector.Error as err:
        print("Error connecting to database:", err)
        exit()
    return cursor, connection

# define a function to initialize data in mysql
def initialize_data(cursor, connection):
    # check if table exists
    cursor.execute(f"SHOW TABLES LIKE 'states'")
    table_exists = cursor.fetchone() is not None
    # table exists, no action needed
    if table_exists:
        table_exists = 1

    # create table if it does not exist. import data from .csv file
    else:
        table_exists = 0
        cursor.execute(f"CREATE TABLE IF NOT EXISTS states (name VARCHAR(50), capital VARCHAR(50))")
        cursor.execute(f"LOAD DATA INFILE 'C:\\ProgramData\\MySQL\\MySQL Server 9.0\\Uploads\\state_capitals.csv' INTO TABLE states FIELDS TERMINATED BY ',' LINES TERMINATED BY '\n' IGNORE 1 ROWS;")
        connection.commit()

# ask for capital of random states
def quiz_user(cursor):
    #get data from mysql db and save to dictionary "state_capitals"
    cursor.execute("SELECT name, capital FROM states")   #get from mysqldb
    results = cursor.fetchall()                          #save data to results variable
    state_capitals = {}                                  #initialize dictionary state_capitals
    for column in results:
        state_capitals[column[0]] = column[1]
    states = list(state_capitals.keys())

    # intro message
    os.system('cls')
    print("\nWelcome to the State Capitals Quiz!\n")

    # Shuffle the order of states to make the quiz more interesting
    random.shuffle(states)
    for state in states:
        user_answer = input(f"What is the capital of {state}? ").strip()
        correct_answer = state_capitals[state]

        if user_answer.lower() == correct_answer.lower():
            print("Correct!\n")
        else:
            print(f"Wrong! The correct capital of {state} is {correct_answer}.\n")

### main ###
if __name__ == "__main__":
    cursor, connection = connect_to_mysql()
    initialize_data(cursor, connection)
    quiz_user(cursor)
