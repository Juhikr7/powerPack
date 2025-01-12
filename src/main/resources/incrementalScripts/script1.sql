use powerpack;
DROP TABLE IF EXISTS pp_user;
CREATE TABLE `usr_user` (
   `USER_ID` int(10) UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY COMMENT 'Primary key for the user table',
   `USER_NAME` varchar(25) NOT NULL COMMENT '',
   `NAME` varchar(50) NOT NULL COMMENT '',
   `PASSWORD` tinyblob NULL COMMENT '',
   `ROLE_ID` varchar(25) NOT NULL COMMENT '',
   `ACTIVE` tinyint(3) UNSIGNED NOT NULL COMMENT '',
   `NUMBER_OF_ORDER` int(10) NULL COMMENT ''
);

DROP TABLE IF EXISTS usr_role;
CREATE TABLE `usr_role` (
    `ROLE_ID` varchar(25) NOT NULL PRIMARY KEY COMMENT '',
    `ROLE_DESC` varchar(50) NOT NULL COMMENT ''
);

DROP TABLE IF EXISTS usr_business_function;
CREATE TABLE `usr_business_function` (
    `BUSINESS_FUNCTION_ID` varchar(45) NOT NULL PRIMARY KEY,
    `BUSINESS_FUNCTION_DESC` varchar(100) NOT NULL
);

DROP TABLE IF EXISTS usr_role_business_function;
CREATE TABLE `usr_role_business_function` (
    `ROLE_ID` varchar(25) NOT NULL COMMENT 'Role ID',
    `BUSINESS_FUNCTION_ID` varchar(45) NOT NULL COMMENT 'Business Function ID',
    PRIMARY KEY (`ROLE_ID`, `BUSINESS_FUNCTION_ID`)
);

DROP TABLE IF EXISTS pp_customer;
CREATE TABLE `pp_customer` (
    `CUSTOMER_ID` int(10) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT 'Unique customer ID',
    `CUSTOMER_NAME` varchar(100) NOT NULL COMMENT 'Customer name',
    `MOBILE` varchar(15) NOT NULL COMMENT 'Mobile number',
    `EMAIL_ID` varchar(50) NOT NULL COMMENT 'Email address',
    `AGE` int(2) NOT NULL COMMENT 'Age of customer',
    `GENDER` varchar(1) NOT NULL COMMENT 'Gender (M/F/O)',
    `ORDER_ID` varchar(100) NOT NULL COMMENT 'Order ID associated with customer',
    `CREATED_BY_ID` INT NOT NULL COMMENT 'ID of the user who created the customer record',
    PRIMARY KEY (`CUSTOMER_ID`)
);

INSERT INTO usr_user
(USER_NAME, NAME,PASSWORD,ROLE_ID,ACTIVE) VALUES
('admin','Yash','','ROLE_ADMIN',1),
('user','Yash K','','ROLE_USER',1);

INSERT INTO usr_role
(ROLE_ID, ROLE_DESC) VALUES
('ROLE_ADMIN','admin'),
('ROLE_USER','user');

INSERT INTO usr_business_function
(BUSINESS_FUNCTION_ID, BUSINESS_FUNCTION_DESC) VALUES
('ROLE_BF_SHOW_HOME','Show home page'),
('ROLE_BF_MANAGE_USER','Create / Edit User'),
('ROLE_BF_MANAGE_CUSTOMER','Create / Edit / View / Search customer master');

INSERT INTO usr_role_business_function
(ROLE_ID, BUSINESS_FUNCTION_ID) VALUES
('ROLE_ADMIN','ROLE_BF_SHOW_HOME'),
('ROLE_ADMIN','ROLE_BF_MANAGE_USER'),
('ROLE_ADMIN','ROLE_BF_MANAGE_CUSTOMER'),
('ROLE_ADMIN','ROLE_BF_CREATE_FLOW'),
('ROLE_USER','ROLE_BF_SHOW_HOME'),
('ROLE_USER','ROLE_BF_MANAGE_CUSTOMER');

DROP TABLE IF EXISTS ms_ui_labels;

CREATE TABLE `ms_ui_labels` (
    `UI_LABEL_ID` int(10) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT 'Unique identifier for UI label',
    `UI_LABEL_DESCRIPTION` varchar(50) NOT NULL COMMENT 'Description of the UI label',
    PRIMARY KEY (`UI_LABEL_ID`)
) COMMENT 'Table for storing UI labels and their descriptions';

INSERT INTO ms_ui_label
(UI_LABEL_ID, UI_LABEL_DESCRIPTION) VALUES
('0','Current Type'),
('1','AC Power Supply'),
('2','DC Power Supply'),
('3','1 Phase AC Mortor 4 pole / 2 pole'),
('4','4 Phase AC Mortor 4 pole / 2 pole'),
('5','Watt For 12v DC Motor'),
('6','Watt For 24v DC Motor'),
('7','Starting Relay For DC Mortor'),
('8','Central Moified Circuit'),
('9','Relief Valve Spring'),
('10','Relief Valve Spring'),
('11','Relief Valve Spring'),
('12','Solenoid Cartaidge Valve'),
('13','Solenoid Cartaidge Valve Setting'),
('14','Solenoid Cartaidge Valve Setting (2)'),
('15','Solenoid Cartaidge Valve Setting (2)'),
('16','Pressure Compensated Flow Control Valve'),
('17','Standard Gear Pump'),
('18','Tank Material'),
('19','Tank Shape And Capacity'),
('20','Tank Shape And Capacity');

