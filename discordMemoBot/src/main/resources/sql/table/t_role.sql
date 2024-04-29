create table t_role(
	id			char(4) primary key,
	provider	char(4),
	name		varchar(16),
	info		varchar(200),
	dflt		tinyint,
	allow		char(2)
);
create index idx_name_in_group on t_role(provider, name);