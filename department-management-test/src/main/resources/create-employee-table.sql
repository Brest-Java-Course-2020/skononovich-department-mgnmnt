DROP TABLE IF EXISTS employee;
CREATE TABLE employee (
  employeeId INT NOT NULL AUTO_INCREMENT,
  departmentId INT NOT NULL,
  firstName VARCHAR(30) NOT NULL,
  lastName VARCHAR(30) NOT NULL,
  salary INT,
  PRIMARY KEY (employeeId)
);