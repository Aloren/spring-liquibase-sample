create table service_group (
    group_id int not null comment 'Unique identifier of group',
    group_name varchar(255) not null comment 'group name',
    group_limit int not null default 0 comment 'group limit',
    primary key (group_id),
    unique key idx_group_name(group_name)
) engine = innodb default charset = utf8;
