
    create table address (
        id uuid not null,
        user_id uuid not null,
        name varchar(255),
        primary key (id)
    );

    create table categories (
        id uuid not null,
        description varchar(255),
        name varchar(255),
        primary key (id)
    );

    create table items (
        category_id uuid,
        id uuid not null,
        description varchar(255),
        image varchar(255),
        name varchar(255),
        price bytea,
        primary key (id)
    );

    create table roles (
        id uuid not null,
        name varchar(50) not null unique,
        description varchar(200),
        primary key (id)
    );

    create table user_profiles (
        id uuid not null,
        user_id uuid not null unique,
        primary key (id)
    );

    create table user_roles (
        role_id uuid not null,
        user_id uuid not null,
        primary key (role_id, user_id)
    );

    create table users (
        access_failed_count integer not null,
        email_confirmed boolean not null,
        is_deleted boolean not null,
        lockout_enabled boolean not null,
        phone_number_confirmed boolean not null,
        two_factor_enabled boolean not null,
        lockout_end timestamp(6) with time zone,
        id uuid not null,
        phone_number varchar(30),
        username varchar(50) not null unique,
        first_name varchar(100),
        last_name varchar(100),
        email varchar(254) not null unique,
        password_hash varchar(255) not null,
        security_stamp varchar(255),
        primary key (id)
    );

    alter table if exists address 
       add constraint FK6i66ijb8twgcqtetl8eeeed6v 
       foreign key (user_id) 
       references users;

    alter table if exists items 
       add constraint FKjcdcde7htb3tyjgouo4g9xbmr 
       foreign key (category_id) 
       references categories;

    alter table if exists user_profiles 
       add constraint FKjcad5nfve11khsnpwj1mv8frj 
       foreign key (user_id) 
       references users;

    alter table if exists user_roles 
       add constraint FKh8ciramu9cc9q3qcqiv4ue8a6 
       foreign key (role_id) 
       references roles;

    alter table if exists user_roles 
       add constraint FKhfh9dx7w3ubf1co1vdev94g3f 
       foreign key (user_id) 
       references users;

    create table address (
        id uuid not null,
        user_id uuid not null,
        name varchar(255),
        primary key (id)
    );

    create table categories (
        id uuid not null,
        description varchar(255),
        name varchar(255),
        primary key (id)
    );

    create table items (
        category_id uuid,
        id uuid not null,
        description varchar(255),
        image varchar(255),
        name varchar(255),
        price bytea,
        primary key (id)
    );

    create table roles (
        id uuid not null,
        name varchar(50) not null unique,
        description varchar(200),
        primary key (id)
    );

    create table user_profiles (
        id uuid not null,
        user_id uuid not null unique,
        primary key (id)
    );

    create table user_roles (
        role_id uuid not null,
        user_id uuid not null,
        primary key (role_id, user_id)
    );

    create table users (
        access_failed_count integer not null,
        email_confirmed boolean not null,
        is_deleted boolean not null,
        lockout_enabled boolean not null,
        phone_number_confirmed boolean not null,
        two_factor_enabled boolean not null,
        lockout_end timestamp(6) with time zone,
        id uuid not null,
        phone_number varchar(30),
        username varchar(50) not null unique,
        first_name varchar(100),
        last_name varchar(100),
        email varchar(254) not null unique,
        password_hash varchar(255) not null,
        security_stamp varchar(255),
        primary key (id)
    );

    alter table if exists address 
       add constraint FK6i66ijb8twgcqtetl8eeeed6v 
       foreign key (user_id) 
       references users;

    alter table if exists items 
       add constraint FKjcdcde7htb3tyjgouo4g9xbmr 
       foreign key (category_id) 
       references categories;

    alter table if exists user_profiles 
       add constraint FKjcad5nfve11khsnpwj1mv8frj 
       foreign key (user_id) 
       references users;

    alter table if exists user_roles 
       add constraint FKh8ciramu9cc9q3qcqiv4ue8a6 
       foreign key (role_id) 
       references roles;

    alter table if exists user_roles 
       add constraint FKhfh9dx7w3ubf1co1vdev94g3f 
       foreign key (user_id) 
       references users;
