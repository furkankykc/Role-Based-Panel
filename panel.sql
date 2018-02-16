
drop database if exists GaliPanel ;
create database if not exists GaliPanel; 
use GaliPanel;

create table user(
    	id int primary key auto_increment,
    	username varchar(30) unique,
	name varchar(30),
    	password varchar(30),
    	type INT
);
create table groups (
	id int primary key auto_increment,
	name varchar(30) unique
);
create table permission (
	id int primary key auto_increment,
	name varchar(30) unique
);
create table group_perms(
	group_id int,
	permission_id int,
	PRIMARY KEY (permission_id, group_id),
   	FOREIGN KEY (permission_id) REFERENCES permission (id),
    	FOREIGN KEY (group_id) REFERENCES groups (id)
);
