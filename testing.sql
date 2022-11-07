SELECT Sensor_ID FROM Card, Sensor WHERE Sensor.ESP_ID = Card.ESP_ID AND Card.ESP_ID = 1; 

INSERT INTO Sensor VALUES (2,'Vind',1);
INSERT INTO Sensor VALUES (3,'Luftfuktighet',1);