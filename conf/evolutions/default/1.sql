# --- !Ups
create table user(
  id int auto_increment,
  email varchar(255)
);

create table account(
  id int auto_increment,
  money_sum double,
  user_id int,
  version int
);

insert into user (email) values ('test@test.com');
insert into account (money_sum, user_id, version) values (1000,(select id from user where email = 'test@test.com'), 1);

insert into user (email) values ('test2@test.com');
insert into account (money_sum, user_id, version) values (1000,(select id from user where email = 'test2@test.com'), 1);
# --- !Downs
