![Screenshot](https://github.com/timeblade0/state_capital_quiz_python/blob/main/screenshot.PNG)

# State Capital Quiz
- This script will import all state capitals from a mysql db into python.
- Quiz the user on random state capitals."
- This file can only be uploaded into mysql from this location:
  - C:\ProgramData\MySQL\MySQL Server 9.0\Uploads\state_capitals.csv
  - copy state_capitals.csv to that location before running the python script
- In mysql, this command can be used to import the csv file into mysql
  - LOAD DATA INFILE 'C:\\ProgramData\\MySQL\\MySQL Server 9.0\\Uploads\\state_capitals.csv' INTO TABLE states FIELDS TERMINATED BY ',' LINES TERMINATED BY '\n' IGNORE 1 ROWS;