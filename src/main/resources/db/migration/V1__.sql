CREATE TABLE address
(
    id         UUID         NOT NULL,
    created_at TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    is_deleted BOOLEAN      NOT NULL,
    name       VARCHAR(255),
    user_id    VARCHAR(255) NOT NULL,
    CONSTRAINT pk_address PRIMARY KEY (id)
);

CREATE TABLE categories
(
    id          VARCHAR(255) NOT NULL,
    created_at  TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    is_deleted  BOOLEAN      NOT NULL,
    name        VARCHAR(255),
    description VARCHAR(255),
    CONSTRAINT pk_categories PRIMARY KEY (id)
);

CREATE TABLE items
(
    id          VARCHAR(255) NOT NULL,
    created_at  TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    is_deleted  BOOLEAN      NOT NULL,
    name        VARCHAR(255),
    price       BYTEA,
    image_id    VARCHAR(255),
    description VARCHAR(255),
    category_id VARCHAR(255),
    CONSTRAINT pk_items PRIMARY KEY (id)
);

CREATE TABLE roles
(
    id          VARCHAR(255) NOT NULL,
    created_at  TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    is_deleted  BOOLEAN      NOT NULL,
    name        VARCHAR(255) NOT NULL,
    description VARCHAR(200),
    user_id     VARCHAR(255),
    CONSTRAINT pk_roles PRIMARY KEY (id)
);

CREATE TABLE user_profiles
(
    id      UUID         NOT NULL,
    user_id VARCHAR(255) NOT NULL,
    CONSTRAINT pk_user_profiles PRIMARY KEY (id)
);

CREATE TABLE users
(
    id                     VARCHAR(255) NOT NULL,
    created_at             TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    is_deleted             BOOLEAN      NOT NULL,
    username               VARCHAR(50)  NOT NULL,
    first_name             VARCHAR(100),
    last_name              VARCHAR(100),
    password_hash          VARCHAR(255) NOT NULL,
    phone_number           VARCHAR(255) NOT NULL,
    phone_number_confirmed BOOLEAN,
    user_profile_id        UUID,
    role_id                VARCHAR(255),
    CONSTRAINT pk_users PRIMARY KEY (id)
);

ALTER TABLE roles
    ADD CONSTRAINT uc_roles_name UNIQUE (name);

ALTER TABLE user_profiles
    ADD CONSTRAINT uc_user_profiles_user UNIQUE (user_id);

ALTER TABLE users
    ADD CONSTRAINT uc_users_username UNIQUE (username);

ALTER TABLE address
    ADD CONSTRAINT FK_ADDRESS_ON_USER FOREIGN KEY (user_id) REFERENCES users (id);

ALTER TABLE items
    ADD CONSTRAINT FK_ITEMS_ON_CATEGORY FOREIGN KEY (category_id) REFERENCES categories (id);

ALTER TABLE roles
    ADD CONSTRAINT FK_ROLES_ON_USER FOREIGN KEY (user_id) REFERENCES users (id);

ALTER TABLE users
    ADD CONSTRAINT FK_USERS_ON_ROLE FOREIGN KEY (role_id) REFERENCES roles (id);

ALTER TABLE users
    ADD CONSTRAINT FK_USERS_ON_USERPROFILE FOREIGN KEY (user_profile_id) REFERENCES user_profiles (id);

ALTER TABLE user_profiles
    ADD CONSTRAINT FK_USER_PROFILES_ON_USER FOREIGN KEY (user_id) REFERENCES users (id);