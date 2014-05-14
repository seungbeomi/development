create table dept (
  deptno decimal(2) primary key,
  dname varchar(14) ,
  loc varchar(13)
);
 
create table emp (
  empno decimal(4) primary key,
  ename varchar(10),
  job varchar(9),
  mgr decimal(4),
  hiredate date,
  sal decimal(7,2),
  comm decimal(7,2),
  deptno decimal(2),
  foreign key (deptno) references dept (deptno)
);
 
create table bonus (
  ename varchar(10),
  job varchar(9),
  sal decimal,
  comm decimal
);
 
create table salgrade (
  grade decimal,
  losal decimal,
  hisal decimal
);
 
begin;
insert into dept values (10,'accounting','new york');
insert into dept values (20,'research','dallas');
insert into dept values (30,'sales','chicago');
insert into dept values (40,'operations','boston');
insert into emp values (7369,'smith','clerk',7902,str_to_date('17-12-1980','%d-%m-%Y'),800,null,20);
insert into emp values (7499,'allen','salesman',7698,str_to_date('20-2-1981','%d-%m-%Y'),1600,300,30);
insert into emp values (7521,'ward','salesman',7698,str_to_date('22-2-1981','%d-%m-%Y'),1250,500,30);
insert into emp values (7566,'jones','manager',7839,str_to_date('2-4-1981','%d-%m-%Y'),2975,null,20);
insert into emp values (7654,'martin','salesman',7698,str_to_date('28-9-1981','%d-%m-%Y'),1250,1400,30);
insert into emp values (7698,'blake','manager',7839,str_to_date('1-5-1981','%d-%m-%Y'),2850,null,30);
insert into emp values (7782,'clark','manager',7839,str_to_date('9-6-1981','%d-%m-%Y'),2450,null,10);
insert into emp values (7788,'scott','analyst',7566,date_add(str_to_date('13-jul-87','%d-%b-%y'), interval -85 day),3000,null,20);
insert into emp values (7839,'king','president',null,str_to_date('17-11-1981','%d-%m-%Y'),5000,null,10);
insert into emp values (7844,'turner','salesman',7698,str_to_date('8-9-1981','%d-%m-%Y'),1500,0,30);
insert into emp values (7876,'adams','clerk',7788,date_add(str_to_date('13-jul-87', '%d-%b-%y'), interval -51 day),1100,null,20);
insert into emp values (7900,'james','clerk',7698,str_to_date('3-12-1981','%d-%m-%Y'),950,null,30);
insert into emp values (7902,'ford','analyst',7566,str_to_date('3-12-1981','%d-%m-%Y'),3000,null,20);
insert into emp values (7934,'miller','clerk',7782,str_to_date('23-1-1982','%d-%m-%Y'),1300,null,10);
insert into salgrade values (1,700,1200);
insert into salgrade values (2,1201,1400);
insert into salgrade values (3,1401,2000);
insert into salgrade values (4,2001,3000);
insert into salgrade values (5,3001,9999);
commit;