--DROP TABLE employees PURGE;
--DROP TABLE departments PURGE;

CREATE TABLE departments (
  department_id   NUMBER(2) CONSTRAINT departments_pk PRIMARY KEY,
  department_name VARCHAR2(14),
  location        VARCHAR2(13)
);

INSERT INTO departments VALUES (10,'ACCOUNTING','NEW YORK');
INSERT INTO departments VALUES (20,'RESEARCH','DALLAS');
INSERT INTO departments VALUES (30,'SALES','CHICAGO');
INSERT INTO departments VALUES (40,'OPERATIONS','BOSTON');
COMMIT;


CREATE TABLE employees (
  employee_id   NUMBER(4) CONSTRAINT employees_pk PRIMARY KEY,
  employee_name VARCHAR2(10),
  job           VARCHAR2(9),
  manager_id    NUMBER(4),
  hiredate      DATE,
  salary        NUMBER(7,2),
  commission    NUMBER(7,2),
  department_id NUMBER(2) CONSTRAINT emp_department_id_fk REFERENCES departments(department_id)
);

INSERT INTO employees VALUES (7369,'SMITH','CLERK',7902,to_date('17-12-1980','dd-mm-yyyy'),800,NULL,20);
INSERT INTO employees VALUES (7499,'ALLEN','SALESMAN',7698,to_date('20-2-1981','dd-mm-yyyy'),1600,300,30);
INSERT INTO employees VALUES (7521,'WARD','SALESMAN',7698,to_date('22-2-1981','dd-mm-yyyy'),1250,500,30);
INSERT INTO employees VALUES (7566,'JONES','MANAGER',7839,to_date('2-4-1981','dd-mm-yyyy'),2975,NULL,20);
INSERT INTO employees VALUES (7654,'MARTIN','SALESMAN',7698,to_date('28-9-1981','dd-mm-yyyy'),1250,1400,30);
INSERT INTO employees VALUES (7698,'BLAKE','MANAGER',7839,to_date('1-5-1981','dd-mm-yyyy'),2850,NULL,30);
INSERT INTO employees VALUES (7782,'CLARK','MANAGER',7839,to_date('9-6-1981','dd-mm-yyyy'),2450,NULL,10);
INSERT INTO employees VALUES (7788,'SCOTT','ANALYST',7566,to_date('13-JUL-87','dd-mm-rr')-85,3000,NULL,20);
INSERT INTO employees VALUES (7839,'KING','PRESIDENT',NULL,to_date('17-11-1981','dd-mm-yyyy'),5000,NULL,10);
INSERT INTO employees VALUES (7844,'TURNER','SALESMAN',7698,to_date('8-9-1981','dd-mm-yyyy'),1500,0,30);
INSERT INTO employees VALUES (7876,'ADAMS','CLERK',7788,to_date('13-JUL-87', 'dd-mm-rr')-51,1100,NULL,20);
INSERT INTO employees VALUES (7900,'JAMES','CLERK',7698,to_date('3-12-1981','dd-mm-yyyy'),950,NULL,30);
INSERT INTO employees VALUES (7902,'FORD','ANALYST',7566,to_date('3-12-1981','dd-mm-yyyy'),3000,NULL,20);
INSERT INTO employees VALUES (7934,'MILLER','CLERK',7782,to_date('23-1-1982','dd-mm-yyyy'),1300,NULL,10);
COMMIT;