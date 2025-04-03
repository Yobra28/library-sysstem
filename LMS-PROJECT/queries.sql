-- Insert a new book into the library
INSERT INTO lib_book (bookId, bookName, category, pages, rackNo, authorName, publisherName)
VALUES (?, ?, ?, ?, ?, ?, ?);

-- Insert a new student into the system
INSERT INTO student (userName, firstName, lastName, regNo, email, password)
VALUES (?, ?, ?, ?, ?, ?);

-- Record a new book borrowing transaction
INSERT INTO borrowing_history (userName, bookId, dueDate)
VALUES (?, ?, ?);

-- Book a study space for a specific user, seat, time period, and date
INSERT INTO study_space_booking (userName, seatNumber, timePeriod, year, month, day)
VALUES (?, ?, ?, ?, ?, ?);

-- Delete a book borrowing record when the book is returned
DELETE FROM borrowing_history
WHERE userName = ? AND bookId = ?;

-- Automatically vacate expired study space bookings
DELETE FROM study_space_booking
WHERE STR_TO_DATE(CONCAT(year, '-', month, '-', day, ' ', SUBSTRING_INDEX(timePeriod, '-', 1)), '%Y-%m-%d %l %p') < NOW() - INTERVAL 3 HOUR;

-- Calculate the number of overdue days for a borrowed book
SELECT DATEDIFF(CURDATE(), dueDate) AS daysOverdue
FROM borrowing_history
WHERE userName = ? AND bookId = ?;

-- Retrieve all booked seat numbers for a specific date and time period
SELECT seatNumber
FROM study_space_booking
WHERE year = ? AND month = ? AND day = ? AND timePeriod = ?;

-- Retrieve user details (username, registration number, and email)
SELECT userName, regNo, email
FROM student;

-- Retrieve study space booking details (username, seat number, and time period)
SELECT userName, seatNumber, timePeriod
FROM study_space_booking;

-- Retrieve fine details for users, including their registration number
SELECT f.userName, s.regNo, f.fine
FROM fines f
JOIN student s ON f.userName = s.userName;