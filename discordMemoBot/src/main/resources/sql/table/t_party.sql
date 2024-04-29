create table t_party(
	id			char(4) primary key,
	discrim		char(1),
	name		varchar(255),
	reg_dt		timestamp default current_timestamp(),
	upt_dt		timestamp default current_timestamp() on update current_timestamp()
);

create table t_group(
	id			char(4) primary key,
	open		tinyint,
	intro		varchar(1000)
);