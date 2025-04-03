-- Create the database
CREATE DATABASE IF NOT EXISTS DBMS_Project;

-- Use the database
USE DBMS_Project;

-- Create the `student` table
CREATE TABLE IF NOT EXISTS student (
    userName VARCHAR(50) PRIMARY KEY,
    firstName VARCHAR(50) NOT NULL,
    lastName VARCHAR(50) NOT NULL,
    regNo VARCHAR(20) UNIQUE NOT NULL,
    email VARCHAR(100) NOT NULL,
    password VARCHAR(100) NOT NULL
);

-- Create the `lib_book` table
CREATE TABLE IF NOT EXISTS lib_book (
    bookId VARCHAR(50) PRIMARY KEY,
    bookName VARCHAR(100) NOT NULL,
    category VARCHAR(50) NOT NULL,
    pages INT NOT NULL,
    rackNo VARCHAR(20) NOT NULL,
    authorName VARCHAR(100) NOT NULL,
    publisherName VARCHAR(100) NOT NULL
);

-- Create the `borrowing_history` table
CREATE TABLE IF NOT EXISTS borrowing_history (
    userName VARCHAR(50),
    bookId VARCHAR(50),
    dueDate DATE NOT NULL,
    FOREIGN KEY (userName) REFERENCES student(userName),
    FOREIGN KEY (bookId) REFERENCES lib_book(bookId)
);

-- Create the `study_space_booking` table
CREATE TABLE IF NOT EXISTS study_space_booking (
    userName VARCHAR(50),
    seatNumber VARCHAR(20),
    timePeriod VARCHAR(20),
    year INT,
    month INT,
    day INT,
    PRIMARY KEY (seatNumber, timePeriod, year, month, day),
    FOREIGN KEY (userName) REFERENCES student(userName)
);

-- Create the `fines` table
CREATE TABLE IF NOT EXISTS fines (
    userName VARCHAR(50),
    fine DECIMAL(10, 2) NOT NULL,
    FOREIGN KEY (userName) REFERENCES student(userName)
);