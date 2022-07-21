CREATE DATABASE HKCBooking;
drop database HKCBooking
GO
USE HKCBooking;
GO

CREATE TABLE hkcbooking_user (
    userId int IDENTITY(1,1) PRIMARY KEY NOT NULL,
    username varchar(30) NOT NULL,
    password varchar(30) NOT NULL,
    fullname varchar(50) NOT NULL,
    address varchar(100),
    phone varchar(20) NOT NULL,
    role varchar(20) NOT NULL,
    email varchar(50) NOT NULL
)
GO

CREATE TABLE hkcbooking_room_type (
    roomTypeId int PRIMARY KEY NOT NULL,
    roomName VARCHAR(50) NOT NULL,
    capacity int NOT NULL, 
	acreage int NOT NULL
)
GO

CREATE TABLE hkcbooking_room (
    roomId int IDENTITY(1,1) PRIMARY KEY NOT NULL,
    roomTypeId INT FOREIGN KEY REFERENCES hkcbooking_room_type(roomTypeId) NOT NULL,
    description NVARCHAR(1000) NOT NULL,
    price FLOAT NOT NULL,
    urlImage VARCHAR(255) NOT NULL,
    roomStatus VARCHAR(20) NOT NULL,
)
GO

CREATE TABLE hkcbooking_history (
    historyId int IDENTITY(1,1) PRIMARY KEY NOT NULL,
    userId INT FOREIGN KEY REFERENCES hkcbooking_user(userId) NOT NULL,
    message VARCHAR(100),
    historyStatus VARCHAR(20) NOT NULL,
    roomId INT FOREIGN KEY REFERENCES hkcbooking_room(roomId) NOT NULL,
    startDate DATE NOT NULL,
    endDate DATE NOT NULL,
    note VARCHAR(100),
    total FLOAT NOT NULL,
)
GO

-- ==================


SELECT * FROM hkcbooking_user


INSERT INTO hkcbooking_room_type VALUES (1, 'Single Room', 1, 40)
INSERT INTO hkcbooking_room_type VALUES (2, 'Double Room', 2, 45)
INSERT INTO hkcbooking_room_type VALUES (3, 'Triple Room', 3, 55)
INSERT INTO hkcbooking_room_type VALUES (4, 'Quad Room', 4, 80)
INSERT INTO hkcbooking_room_type VALUES (5, 'Queen Room', 1, 42)
INSERT INTO hkcbooking_room_type VALUES (6, 'King Room', 1, 42)
INSERT INTO hkcbooking_room_type VALUES (7, 'Twin Room', 2, 36)
INSERT INTO hkcbooking_room_type VALUES (8, 'Hollywood Twin Room', 2, 36)
INSERT INTO hkcbooking_room_type VALUES (9, 'Double-double Room', 4, 60)
INSERT INTO hkcbooking_room_type VALUES (10, 'Studio Room', 1, 35)
INSERT INTO hkcbooking_room_type VALUES (11, 'Suite / Executive Suite', 2, 90)
INSERT INTO hkcbooking_room_type VALUES (12, 'Mini Suite or Junior Suite', 1, 70)
INSERT INTO hkcbooking_room_type VALUES (13, 'President Suite | Presidential Suite', 10, 300)
INSERT INTO hkcbooking_room_type VALUES (14, 'Apartments / Room for Extended Stay', 8, 200)
INSERT INTO hkcbooking_room_type VALUES (15, 'Connecting Rooms', 4, 80)
INSERT INTO hkcbooking_room_type VALUES (16, 'Murphy Room', 2, 30)
INSERT INTO hkcbooking_room_type VALUES (17, 'Accessible Room / Disabled Room', 2, 40)
INSERT INTO hkcbooking_room_type VALUES (18, 'Cabana Room', 1, 38)
INSERT INTO hkcbooking_room_type VALUES (19, 'Adjoining rooms', 3, 40)
INSERT INTO hkcbooking_room_type VALUES (20, 'Adjacent rooms', 4, 42)
INSERT INTO hkcbooking_room_type VALUES (21, 'Villa Room', 5, 130)
INSERT INTO hkcbooking_room_type VALUES (22, 'Executive Floor / Floored Room', 2, 45)
INSERT INTO hkcbooking_room_type VALUES (23, 'Smoking / Non-Smoking Room', 4, 220)

INSERT INTO hkcbooking_room VAlUES(1,'A room assigned to one person. May have one or more beds.',50,1,'on')
INSERT INTO hkcbooking_room VAlUES(2,'A room assigned to two people. May have one or more beds.',60,1,'on')
INSERT INTO hkcbooking_room VAlUES(3,'A room that can accommodate three persons and has been fitted with three twin beds, one double bed and one twin bed or two double beds.',70,1,'on')
INSERT INTO hkcbooking_room VAlUES(4,'A room assigned to four people. May have two or more beds.',80,1,'on')
INSERT INTO hkcbooking_room VAlUES(5,'A room with a queen-sized bed. May be occupied by one or more people.',90,1,'on')
INSERT INTO hkcbooking_room VAlUES(6,'A room with a king-sized bed. May be occupied by one or more people.',100,1,'on')
INSERT INTO hkcbooking_room VAlUES(7,'A room with two twin beds. May be occupied by one or more people.',110,1,'on')
INSERT INTO hkcbooking_room VAlUES(8,'A room that can accommodate two persons with two twin beds joined together by a common headboard. Most of the budget hotels tend to provide many of these room settings which cater both couples and parties in two.',120,1,'on')
INSERT INTO hkcbooking_room VAlUES(9,'A Room with two double ( or perhaps queen) beds. And can accommodate two to four persons with two twin, double or queen-size beds.',130,1,'on')
INSERT INTO hkcbooking_room VAlUES(10,'A room with a studio bed- a couch which can be converted into a bed. May also have an additional bed.',140,1,'on')
INSERT INTO hkcbooking_room VAlUES(11,'A parlour or living room connected with to one or more bedrooms. (A room with one or more bedrooms and a separate living space.)',150,1,'on')
INSERT INTO hkcbooking_room VAlUES(12,'A single room with a bed and sitting area. Sometimes the sleeping area is in a bedroom separate from the parlour or living room.',160,1,'on')
INSERT INTO hkcbooking_room VAlUES(13,'The most expensive room provided by a hotel. Usually, only one president suite is available in one single hotel property. ',170,1,'on')
INSERT INTO hkcbooking_room VAlUES(14,'This room type can be found in service apartments and hotels which target for long stay guests.',180,1,'on')
INSERT INTO hkcbooking_room VAlUES(15,'Rooms with individual entrance doors from the outside and a connecting door between. Guests can move between rooms without going through the hallway.',190,1,'on')
INSERT INTO hkcbooking_room VAlUES(16,'A room that is fitted with a sofa bed or a Murphy bed (i.e. a bed that folds out of a wall or closet) which can be transformed from a bedroom in the night time to a living room in daytime.',200,1,'on')
INSERT INTO hkcbooking_room VAlUES(17,'This room type is mainly designed for disabled guests and it is required by law that hotels must provide a certain number of accessible rooms to avoid discrimination.',210,1,'on')
INSERT INTO hkcbooking_room VAlUES(18,'This type of room is always adjoining to the swimming pool or have a private pool attached to the room.',220,1,'on')
INSERT INTO hkcbooking_room VAlUES(19,'Rooms with a common wall but no connecting door.',230,1,'on')
INSERT INTO hkcbooking_room VAlUES(20,'Rooms close to each other, perhaps across the hall.',240,1,'on')
INSERT INTO hkcbooking_room VAlUES(21,'A special form of accommodation which can be found in some resort hotels. It is a kind of stand-alone house which gives extra privacy and space to hotel guests. A fully equipped villa contains not only bedrooms and a living room but a private swimming pool, Jacuzzi and balcony.',175,1,'on')
INSERT INTO hkcbooking_room VAlUES(22,' A room located on the ‘executive floor’ which enables convenient access to the executive lounge.',260,1,'on')
INSERT INTO hkcbooking_room VAlUES(23,'Many hotels provide both smoking and non-smoking rooms for their guests. In order to minimize the effects of secondhand smoke exposure on non-smoking guests.',100,1,'on')

SELECT * FROM hkcbooking_room_type
SELECT RoomName FROM hkcbooking_room_type WHERE roomTypeId=10 Or RoomName=' Room'
