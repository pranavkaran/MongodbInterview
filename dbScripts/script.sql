CREATE TABLE hr (fname VARCHAR(50), lname VARCHAR(50), email VARCHAR(50));
ALTER TABLE hr
ADD UNIQUE KEY `my_unique_key` (`fname`, `lname`);
CREATE TABLE hr_log (feild VARCHAR(50), oldemail VARCHAR(50), newemail VARCHAR(50), changedate DATETIME);


DELIMITER $$
CREATE TRIGGER before_hr_update 
    BEFORE UPDATE ON hr
    FOR EACH ROW 
BEGIN
    INSERT INTO hr_log
    SET feild = 'email',
     oldemail = OLD.email,
     newemail = NEW.email,
     changedate = NOW(); 
END$$
DELIMITER ;

Update hr SET email = 'testShawn@anycompany.com' where fname = 'Shawn' and lname = 'Keith';
Update hr SET email = 'testShawn1@anycompany.com' where fname = 'Shawn' and lname = 'Keith';
Update hr SET email = 'testShawn2@anycompany.com' where fname = 'Shawn' and lname = 'Keith';
Update hr SET email = 'testShawn3@anycompany.com' where fname = 'Shawn' and lname = 'Keith';
Update hr SET email = 'testVictor@anycompany.com' where fname = 'Victor' and lname = 'Gallegos';
Update hr SET email = 'testKathleen@anycompany.com' where fname = 'Kathleen' and lname = 'Allison';
Update hr SET email = 'testDouglas@anycompany.com' where fname = 'Douglas' and lname = 'Sharp';
Update hr SET email = 'testWilliam@anycompany.com' where fname = 'William' and lname = 'Skinner';
