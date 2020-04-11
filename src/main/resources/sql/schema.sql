drop table if exists user_conversation;
drop table if exists user_message;
drop table if exists message;
drop table if exists conversation;
drop table if exists friend_ask;
drop table if exists user;


create table user(
    `id` bigint primary key auto_increment,
    `name` varchar(255) not null ,
    `password` varchar(255)not null ,
    `phone` varchar(255) not null ,
    created_at timestamp,
    updated_at timestamp,
    avatar_url varchar(255),
    index idx_name (name)
);


create table conversation(
    `id` bigint primary key auto_increment,
    created_at timestamp,
    last_chat_at timestamp,
    `type` tinyint
);

create table user_conversation(
    `u_id` bigint,
    `c_id` bigint,
    primary key pk_user_conversation (u_id,c_id),
    constraint fk_user_conversation_user foreign key (u_id) references user(id),
    constraint fk_user_conversation_conversation foreign key (c_id) references conversation(id)
);

create table message(
    `id` bigint primary key auto_increment,
    `text` varchar(1023),
    `sent_time` timestamp,
    `u_id` bigint,
    `c_id` bigint,
    constraint fk_message_user foreign key (u_id) references user(id),
    constraint fk_message_conversation foreign key (c_id) references conversation(id)
);

create table friend_ask(
    `user_ask_from` bigint,
    `user_asK_to` bigint,
    constraint pk_from_to primary key (user_ask_from,user_asK_to),
    constraint fk_ask_from foreign key (user_ask_from) references user(id),
    constraint fk_ask_to foreign key (user_asK_to) references user(id)
);


create table user_need_active(
    `id` bigint primary key auto_increment,
    `phone` varchar(255),
    `code` varchar(255),
    `is_active` boolean default false,
    `created_at` timestamp,
    index idx_active_code_phone (phone)
);

