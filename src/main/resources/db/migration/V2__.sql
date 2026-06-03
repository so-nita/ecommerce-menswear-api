CREATE TABLE files
(
    id             VARCHAR(255) NOT NULL,
    created_at     TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    is_deleted     BOOLEAN      NOT NULL,
    created_by_id  VARCHAR(255) NOT NULL,
    origin_name    VARCHAR(255) NOT NULL,
    generated_name VARCHAR(255) NOT NULL,
    category       VARCHAR(255),
    extension      VARCHAR(255),
    content_type   VARCHAR(255),
    CONSTRAINT pk_files PRIMARY KEY (id)
);

ALTER TABLE categories
    ADD image_id VARCHAR(255);

ALTER TABLE files
    ADD CONSTRAINT FK_FILES_ON_CREATEDBY FOREIGN KEY (created_by_id) REFERENCES users (id);