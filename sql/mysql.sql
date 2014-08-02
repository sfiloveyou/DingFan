
    drop table if exists sys_user_role;

    drop table if exists sys_role_resource;

    drop table if exists sys_user;

    drop table if exists sys_resource;

    drop table if exists sys_role;



    create table sys_resource (
        id bigint not null auto_increment,
        resType varchar(255) not null,
        resString varchar(255) not null,
        name varchar(255),
        descn varchar(255),
        primary key (id)
    ) ENGINE=InnoDB;

    create table sys_role (
        id bigint not null auto_increment,
        name varchar(255) not null unique,
        primary key (id)
    ) ENGINE=InnoDB;

    create table sys_role_resource (
        role_id bigint not null,
        resource_id bigint not null
    ) ENGINE=InnoDB;

 	create table sys_user
    (
        id bigint(20) NOT NULL AUTO_INCREMENT,
        email VARCHAR(255),
        login VARCHAR(255) NOT NULL,
        name VARCHAR(255),
        password VARCHAR(255),
        enabled TINYINT(1) NOT NULL,
        PRIMARY KEY USING BTREE (id),
        CONSTRAINT login UNIQUE USING BTREE (login)
    ) ENGINE=InnoDB;

    create table sys_user_role (
        user_id bigint not null,
        role_id bigint not null
    ) ENGINE=InnoDB;

    alter table sys_role_resource 
        add constraint FK_sys_role_resource_role
        foreign key (role_id) 
        references sys_role (id);

    alter table sys_role_resource 
        add constraint FK__sys_role_resource_resource 
        foreign key (resource_id) 
        references sys_resource (id);

    alter table sys_user_role 
        add constraint FK_user_role_role 
        foreign key (role_id) 
        references sys_role (id);

    alter table sys_user_role 
        add constraint FK_user_role_user 
        foreign key (user_id) 
        references sys_user (id);
        
insert into sys_resource (id, resType, resString, name, descn) values (1, 'URL', '/**', '', '');
insert into sys_resource (id, resType, resString, name, descn) values (2, 'URL', '/js/**', '', '');
insert into sys_resource (id, resType, resString, name, descn) values (3, 'URL', '/images/**', '', '');
insert into sys_resource (id, resType, resString, name, descn) values (4, 'URL', '/index.jsp', '', '');
insert into sys_resource (id, resType, resString, name, descn) values (5, 'URL', '/common/log*.jsp', '', '');


insert into sys_role (id, name) values (1, 'IS_AUTHENTICATED_ANONYMOUSLY');
insert into sys_role (id, name) values (2, 'ROLE_ADMIN');
insert into sys_role (id, name) values (3, 'ROLE_USER');

insert into sys_role_resource (role_id, resource_id) values (2, 1);
insert into sys_role_resource (role_id, resource_id) values (3, 1);
insert into sys_role_resource (role_id, resource_id) values (1, 2);
insert into sys_role_resource (role_id, resource_id) values (1, 3);
insert into sys_role_resource (role_id, resource_id) values (1, 4);
insert into sys_role_resource (role_id, resource_id) values (1, 5);

insert into sys_user (id, email, login, name, password, enabled) values (1, '', 'admin', 'admin', 'admin', '1');
insert into sys_user (id, email, login, name, password, enabled) values (2, '', 'sf', 'sf', 'sf', '1');

insert into sys_user_role (user_id, role_id) values (1, 2);
insert into sys_user_role (user_id, role_id) values (2, 3);

    
DROP TABLE IF EXISTS orders;
CREATE TABLE orders (
  id bigint(20) NOT NULL AUTO_INCREMENT,
  personName varchar(50)  NOT NULL,
  amount varchar(10)  NOT NULL,
  paid int(1) DEFAULT NULL,
  entertime timestamp NULL DEFAULT NULL,
  last_ip varchar(20) DEFAULT NULL,
  comments varchar(50) DEFAULT NULL,
  status int(1) DEFAULT NULL,
  PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
        
