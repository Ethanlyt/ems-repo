CREATE SEQUENCE IF NOT EXISTS department_seq START
WITH
    1 INCREMENT BY 1;

CREATE SEQUENCE IF NOT EXISTS employee_seq START
WITH
    1 INCREMENT BY 1;

CREATE SEQUENCE IF NOT EXISTS project_seq START
WITH
    1 INCREMENT BY 1;

CREATE TABLE department (
    id BIGINT PRIMARY KEY auto_increment,
    name VARCHAR(255) UNIQUE
);

CREATE TABLE project (
    id BIGINT PRIMARY KEY auto_increment,
    name VARCHAR(255) UNIQUE
);

CREATE TABLE employee (
    id BIGINT PRIMARY KEY auto_increment,
    name VARCHAR(255),
    position VARCHAR(255),
    department_id BIGINT,
    FOREIGN KEY (department_id) REFERENCES department (id)
);

CREATE TABLE employee_project (
    employee_id BIGINT,
    project_id BIGINT,
    PRIMARY KEY (employee_id, project_id),
    FOREIGN KEY (employee_id) REFERENCES employee (id),
    FOREIGN KEY (project_id) REFERENCES project (id)
);