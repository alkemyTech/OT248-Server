CREATE TABLE activities (
    id LONG PRIMARY KEY NOT NULL AUTO_INCREMENT,
    name VARCHAR NOT NULL,
    content VARCHAR NOT NULL,
    image VARCHAR NOT NULL,
    createAt DATE,
    updateAt DATE,
    deleted BOOLEAN
);