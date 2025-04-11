-- Ensure the pluggable database is open before creating users
ALTER PLUGGABLE DATABASE XEPDB1 OPEN;

-- Switch session to XEPDB1
ALTER SESSION SET CONTAINER = XEPDB1;

-- Create the user inside XEPDB1
CREATE USER poc_event IDENTIFIED BY mypwd;

-- Ensure user creation is saved
COMMIT;

-- Grant privileges
GRANT CONNECT, RESOURCE TO poc_event;

-- Ensure grants are applied
COMMIT;
