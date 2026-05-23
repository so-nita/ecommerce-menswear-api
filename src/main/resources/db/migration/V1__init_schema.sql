CREATE TABLE IF NOT EXISTS roles (
    id          UUID PRIMARY KEY,
    name        VARCHAR(50)  NOT NULL UNIQUE,
    description VARCHAR(200)
);

CREATE TABLE IF NOT EXISTS users (
    id                       UUID PRIMARY KEY,
    username                 VARCHAR(50)  NOT NULL UNIQUE,
    first_name               VARCHAR(100),
    last_name                VARCHAR(100),
    email                    VARCHAR(254) NOT NULL UNIQUE,
    email_confirmed          BOOLEAN      NOT NULL DEFAULT FALSE,
    password_hash            VARCHAR(255) NOT NULL,
    phone_number             VARCHAR(30),
    phone_number_confirmed   BOOLEAN      NOT NULL DEFAULT FALSE,
    two_factor_enabled       BOOLEAN      NOT NULL DEFAULT FALSE,
    lockout_end              TIMESTAMP WITH TIME ZONE,
    lockout_enabled          BOOLEAN      NOT NULL DEFAULT TRUE,
    access_failed_count      INT          NOT NULL DEFAULT 0,
    security_stamp           VARCHAR(255),
    is_deleted               BOOLEAN      NOT NULL DEFAULT TRUE
);

CREATE TABLE IF NOT EXISTS user_roles (
    user_id UUID NOT NULL REFERENCES users(id),
    role_id UUID NOT NULL REFERENCES roles(id),
    PRIMARY KEY (user_id, role_id)
);

CREATE TABLE IF NOT EXISTS user_profiles (
    id      UUID PRIMARY KEY,
    user_id UUID NOT NULL UNIQUE REFERENCES users(id)
);

CREATE TABLE IF NOT EXISTS address (
    id      UUID PRIMARY KEY,
    name    VARCHAR(255),
    user_id UUID NOT NULL REFERENCES users(id)
);

CREATE TABLE IF NOT EXISTS categories (
    id          UUID PRIMARY KEY,
    name        VARCHAR(255),
    description VARCHAR(255)
);

CREATE TABLE IF NOT EXISTS items (
    id          UUID PRIMARY KEY,
    name        VARCHAR(255),
    price       NUMERIC,
    image       VARCHAR(255),
    description VARCHAR(255),
    category_id UUID REFERENCES categories(id)
);
