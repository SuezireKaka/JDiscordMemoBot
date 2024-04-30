create table t_role(
	id			char(4) primary key,
	provider	char(4),
	name		varchar(16),
	info		varchar(200),
	dflt		tinyint,
	allow		char(2)
);
create index idx_by_provider_name on t_role(provider, name);

insert into t_role(id, provider, name, info, dflt, allow)
select NEXT_PK('s_role') /*'0000'*/ id,
            '0000' provider,
            'member' name,
            '' info,
            1 dflt,
            'PM' allow