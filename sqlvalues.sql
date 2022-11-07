SET FOREIGN_KEY_CHECKS = 0;

TRUNCATE table Card;
TRUNCATE table Location;
TRUNCATE table Measure;
TRUNCATE table Sensor;

INSERT INTO Card VALUES (1,1);
INSERT INTO Card VALUES (2,2);
INSERT INTO Card VALUES (3,3);

INSERT INTO Location VALUES (1,'Helsingborg');
INSERT INTO Location VALUES (2,'Malmö');
INSERT INTO Location VALUES (3,'Kiruna');

INSERT INTO Sensor VALUES (1,'Temperatur',1);

INSERT INTO Measure VALUES(now(),5,1);

SET FOREIGN_KEY_CHECKS = 1;



