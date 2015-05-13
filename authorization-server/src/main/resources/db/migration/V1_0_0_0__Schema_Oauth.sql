create table if not exists c_security_permission (
    id varchar(255) not null,
    permission_label varchar(255) not null,
    permission_value varchar(255) not null,
    primary key (id)
) ENGINE=InnoDB;

create table if not exists c_security_role (
    id varchar(255) not null,
    description varchar(255),
    name varchar(255) not null,
    primary key (id)
) ENGINE=InnoDB;

create table if not exists c_security_role_permission (
    id_role varchar(255) not null,
    id_permission varchar(255) not null,
    primary key (id_role, id_permission)
) ENGINE=InnoDB;

create table if not exists c_security_user (
    id varchar(255) not null,
    active bit,
    fullname varchar(255) not null,
    username varchar(255) not null,
    id_role varchar(255) not null,
    primary key (id)
) ENGINE=InnoDB;

create table if not exists c_security_user_password (
    id_user varchar(255) not null,
    user_password varchar(255) not null,
    primary key (id_user)
) ENGINE=InnoDB;

alter table c_security_permission 
    add constraint UK_k4suda9cvcsoikdgquscypmt6  unique (permission_value);

alter table c_security_user 
    add constraint UK_at8if7a9lnl90wxllb9divpdf  unique (username);

alter table c_security_role_permission 
    add constraint FK_d89p0a0x87scb5s3830jx7xq0 
    foreign key (id_permission) 
    references c_security_permission (id);

alter table c_security_role_permission 
    add constraint FK_fvynt2q4rxk27e0bxuon50tp4 
    foreign key (id_role) 
    references c_security_role (id);

alter table c_security_user 
    add constraint FK_my18sie96bgbncypva3fxboxy 
    foreign key (id_role) 
    references c_security_role (id);

alter table c_security_user_password 
    add constraint FK_9a26m4sjx4ddi35n3w0s6b5os 
    foreign key (id_user) 
    references c_security_user (id);

CREATE TABLE IF NOT EXISTS `oauth_access_token` (
  `token_id` varchar(256) DEFAULT NULL,
  `token` blob,
  `authentication_id` varchar(256) DEFAULT NULL,
  `user_name` varchar(256) DEFAULT NULL,
  `client_id` varchar(256) DEFAULT NULL,
  `authentication` blob,
  `refresh_token` varchar(256) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE IF NOT EXISTS `oauth_client_details` (
  `client_id` varchar(256) NOT NULL,
  `resource_ids` varchar(256) DEFAULT NULL,
  `client_secret` varchar(256) DEFAULT NULL,
  `scope` varchar(256) DEFAULT NULL,
  `authorized_grant_types` varchar(256) DEFAULT NULL,
  `web_server_redirect_uri` varchar(256) DEFAULT NULL,
  `authorities` varchar(256) DEFAULT NULL,
  `access_token_validity` int(11) DEFAULT NULL,
  `refresh_token_validity` int(11) DEFAULT NULL,
  `additional_information` varchar(4096) DEFAULT NULL,
  `autoapprove` varchar(256) DEFAULT NULL,
  PRIMARY KEY (`client_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE IF NOT EXISTS `oauth_refresh_token` (
  `token_id` varchar(256) DEFAULT NULL,
  `token` blob,
  `authentication` blob
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE IF NOT EXISTS `oauth_client_token` (
  `token_id` varchar(256) DEFAULT NULL,
  `token` blob,
  `authentication_id` varchar(256) DEFAULT NULL,
  `user_name` varchar(256) DEFAULT NULL,
  `client_id` varchar(256) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

INSERT INTO `c_security_permission` (`id`, `permission_label`, `permission_value`) VALUES
('userweb', 'User Web', 'ROLE_USER_WEB');

INSERT INTO `c_security_role` (`id`, `description`, `name`) VALUES
('administrator', 'Application Administrator', 'Administrator');

INSERT INTO `c_security_role_permission` (`id_role`, `id_permission`) VALUES
('administrator', 'userweb');

INSERT INTO `c_security_user` (`id`, `active`, `fullname`, `username`, `id_role`) VALUES
('su123', b'1', 'Supsr User', 'superuser', 'administrator');

INSERT INTO `c_security_user_password` (`id_user`, `user_password`) VALUES
('su123', '$2a$08$LS3sz9Ft014MNaIGCEyt4u6VflkslOW/xosyRbinIF9.uaVLpEhB6');