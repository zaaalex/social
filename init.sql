CREATE TABLE userinfo
(
    name               VARCHAR(255) NOT NULL,
    last_authorization VARCHAR(255),
    CONSTRAINT pk_userinfo PRIMARY KEY (name)
);