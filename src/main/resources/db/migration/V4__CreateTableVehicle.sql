create table vehicle (
 	id bigint not null auto_increment, 
 	ad_price double precision,
 	board varchar(255),
 	date_registrer datetime,
 	pricekbb double precision,
 	year integer,
 	brand_id varchar(255),
 	model_id varchar(255),
 	primary key (id)
) engine=InnoDB