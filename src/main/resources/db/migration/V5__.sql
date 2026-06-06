ALTER TABLE users
DROP
CONSTRAINT fk_users_on_userprofile;

ALTER TABLE users
    ADD created_by VARCHAR(255);

ALTER TABLE users
    ADD deleted_at TIMESTAMP WITHOUT TIME ZONE;

ALTER TABLE users
    ADD deleted_by VARCHAR(255);

ALTER TABLE users
    ADD updated_at TIMESTAMP WITHOUT TIME ZONE;

ALTER TABLE users
    ADD updated_by VARCHAR(255);

ALTER TABLE users
    ALTER COLUMN updated_at SET NOT NULL;

ALTER TABLE users
    ADD CONSTRAINT uc_users_phonenumber UNIQUE (phone_number);

ALTER TABLE users
DROP
COLUMN user_profile_id;

ALTER TABLE user_profiles
DROP
COLUMN id;

ALTER TABLE user_profiles
    ADD id VARCHAR(255) NOT NULL PRIMARY KEY;

ALTER TABLE users
ALTER
COLUMN phone_number TYPE VARCHAR(50) USING (phone_number::VARCHAR(50));

ALTER TABLE users
ALTER
COLUMN username TYPE VARCHAR(255) USING (username::VARCHAR(255));

ALTER TABLE users
    ALTER COLUMN username DROP NOT NULL;