ALTER TABLE roles
DROP
CONSTRAINT fk_roles_on_user;

ALTER TABLE roles
DROP
COLUMN user_id;