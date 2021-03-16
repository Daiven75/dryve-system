create table model_year (
	id varchar(255) not null,
	kbb_id integer,
	year integer,
	model_id varchar(255),
	primary key (id)
) engine=InnoDB