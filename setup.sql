create table Location (
Location_ID int (20) not null,
Display_Name varchar (40),
primary key (Location_ID)
);

create table Card (
ESP_ID int (20) not null,
Location_ID int(20) not null,
primary key (ESP_id),
foreign key (Location_ID) references Location(Location_ID)
);

create table Sensor (
Sensor_ID int (20) not null,
Type varchar (20) not null,
ESP_ID int(20) not null,
primary key (Sensor_id),
foreign key (ESP_ID) references Card(ESP_ID)
);

create table Measure (
MeasureTime datetime,
MeasureValue float (20),
Sensor_ID int (20) not null,
primary key (MeasureTime),
foreign key (Sensor_ID) references Sensor(Sensor_ID)
);