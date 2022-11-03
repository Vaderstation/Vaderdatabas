create table Card (
ESP_ID int (20) not null,
primary key (ESP_id)
);

create table Sensor (
Sensor_id int (20) not null,
Value double (20),
Date date,
ESP_ID int(20) not null,
primary key (Sensor_id),
foreign key (ESP_ID) references Card(ESP_ID)
);

create table Location (
Location_ID int (20) not null,
Display_Name varchar (30),
ESP_ID int(20) not null,
primary key (Sensor_id),
foreign key (ESP_ID) references Card(ESP_ID)
);