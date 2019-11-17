create table task
(
    id              SERIAL       NOT NULL,
    name            VARCHAR(50)  NOT NULL,
    description     VARCHAR(300) NOT NULL,
    creation_date   DATE         NOT NULL,
    sequence_number INT          NOT NULL,
    column_id       INT          NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (column_id) REFERENCES columns (id)
);













