package kr.co.bnksys.querydsl;

import static kr.co.bnksys.querydsl.model.QDepartment.department;
import static kr.co.bnksys.querydsl.model.QEmployee.employee;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.querydsl.core.Tuple;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQueryFactory;

import kr.co.bnksys.querydsl.model.first.Employee;

@SpringBootTest
class JoinTest {

    @Autowired
    JPAQueryFactory query;

    @Test
    void testJoin() {
        // https://github.com/cheese10yun/blog-sample/blob/master/query-dsl/docs/query-dsl-study.md
        assertNotNull(query);

        List<?> list = null;

        // PAGING
        count();
        paging();
        aggregation();
        subquery();

        // JOIN
        innerJoin();
        leftOuterJoin();
        rightOuterJoin();
        fetchJoin();

        // GROUP BY

        System.out.println(list.size() + " rows selected");
    }

    private void count() {
        long cnt = query.selectFrom(department)
                .fetchCount();

        System.out.println("COUNT: " + cnt);
    }

    /*
    fetchResults : 페이징  
    */
    private void paging() {
        query.selectFrom(department)
                .fetchResults();

        /*
        SELECT COUNT( d.department_id )
          FROM bnk_db.departments d
        
         
        */
        query.selectFrom(department)
                .offset(1)
                .limit(2)
                .fetchResults();
    }

    /*
    통계 
    */
    private void aggregation() {
        /*
        SELECT
            COUNT( e.employee_id ),
            SUM( e.salary ),
            AVG( e.salary ),
            MAX( e.salary ),
            MIN( e.salary )
        FROM
            bnk_db.employees e
        */
        Tuple tuple = query.select(employee.count(),
                employee.salary.sum(),
                employee.salary.avg(),
                employee.salary.max(),
                employee.salary.min())
                .from(employee)
                .fetchOne();

        System.out.println(tuple.get(employee.count()));
        System.out.println(tuple.get(employee.salary.sum()));

        /*
        SELECT d.department_name,
               e.employee_name,
               AVG( e.salary )
          FROM bnk_db.departments d
         INNER JOIN bnk_db.employees e 
            ON d.department_id = e.department_id
         WHERE d.department_id >= 30
         GROUP BY d.department_name,
                  e.employee_name
         ORDER BY d.department_name ASC,
                  e.employee_name ASC 
        */
        List<Tuple> list = query.select(department.name,
                employee.name,
                employee.salary.avg())
                .from(department)
                .innerJoin(employee)
                .on(department.id.eq(employee.department.id))
                .groupBy(department.name,
                        employee.name)
                .where(department.id.goe(30))
                .orderBy(department.name.asc(), employee.name.asc())
                .fetch();

        System.out.println(list);
    }

    private void subquery() {
        List<Employee> list = query.selectFrom(employee)
                .where(employee.department.id.eq(
                        JPAExpressions.select(department.id)
                                .from(department)
                                .where(department.id.eq(20L))))
                .fetch();

        System.out.println(list + " rows selected");
    }

    private void innerJoin() {
        /*
        SELECT d.department_name AS col_0_0_,
               e.employee_name AS col_1_0_
          FROM bnk_db.departments d
         INNER JOIN bnk_db.employees e 
            ON d.department_id = e.department_id
         WHERE d.department_id >= 30
         ORDER BY d.department_name ASC,
                  e.employee_name ASC
        */
        List<Tuple> list = query.select(department.name,
                employee.name)
                .from(department)
                .innerJoin(employee)
                .on(department.id.eq(employee.department.id))
                .where(department.id.goe(30))
                .orderBy(department.name.asc(), employee.name.asc())
                .fetch();

        System.out.println(list.size() + " rows selected");
    }

    private void leftOuterJoin() {
        /*
        SELECT d.department_name,
               e.employee_name
          FROM bnk_db.departments d
          LEFT OUTER JOIN bnk_db.employees e 
            ON d.department_id = e.department_id
           AND e.salary >= 2000.0
         WHERE d.department_id >= 30
         ORDER BY d.department_name ASC,
                  e.employee_name ASC
        */
        List<Tuple> list = query.select(department.name,
                employee.name)
                .from(department)
                .leftJoin(employee)
                .on(department.id.eq(employee.department.id)
                        .and(employee.salary.goe(2000)))
                .where(department.id.goe(30))
                .orderBy(department.name.asc(), employee.name.asc().nullsLast())
                .fetch();

        System.out.println(list.size() + " rows selected");
    }

    private void rightOuterJoin() {
        /*
        SELECT d.department_name,
               e.employee_name
          FROM bnk_db.employees e
         RIGHT OUTER JOIN bnk_db.departments d 
            ON e.department_id = d.department_id
         WHERE d.department_id >= 30
         ORDER BY d.department_name ASC,
                  e.employee_name ASC 
        */
        List<Tuple> list = query.select(department.name,
                employee.name)
                .from(employee)
                .rightJoin(department)
                .on(employee.department.id.eq(department.id))
                .where(department.id.goe(30))
                .orderBy(department.name.asc(), employee.name.asc())
                .fetch();

        System.out.println(list.size() + " rows selected");
    }

    private void fetchJoin() {
        /*
        SELECT
            e.employee_id,
            d.department_id,
            e.commission,
            e.department_id,
            e.hiredate,
            e.job,
            e.manager_id,
            e.employee_name,
            e.salary,
            d.location,
            d.department_name
         FROM bnk_db.employees e
        INNER JOIN bnk_db.departments d 
           ON e.department_id = d.department_id
        */
        List<Employee> list = query.selectFrom(employee)
                .join(employee.department, department)
                .fetchJoin()
                .fetch();

        System.out.println(list.size() + " rows selected");
    }

    @Test
    void test() {
        assertNotNull(query);
        /*
        // LEFT OUTER JOIN I
        List<Tuple> list = query.select(employee.id,
                employee.lastName,
                department.id,
                department.location.id)
                .from(employee)
                .leftJoin(employee.department, department)
                .fetch();
        
        System.out.println(list.size());
        
        // LEFT OUTER JOIN II
        list = query.select(employee.id,
                employee.lastName,
                department.id,
                department.location.id)
                .from(employee)
                .leftJoin(employee.department, department)
                .where(department.isNotNull())
                .fetch();
        
        System.out.println(list.size());
        
        // RIGHT OUTER JOIN I
        list = query.select(employee.id,
                employee.lastName,
                department.id,
                department.location.id)
                .from(employee)
                .rightJoin(employee.department, department)
                .fetch();
        
        System.out.println(list.size());
        
        // RIGHT OUTER JOIN II
        list = query.select(employee.id,
                employee.lastName,
                department.id,
                department.location.id)
                .from(employee)
                .rightJoin(employee.department, department)
                .where(employee.department.isNotNull())
                .fetch();
        
        System.out.println(list.size());
        
        // INNER JOIN
        list = query.select(employee.id,
                employee.lastName,
                department.id,
                department.location.id)
                .from(employee)
                .innerJoin(employee.department, department)
                .fetch();
        
        System.out.println(list.size());
        */
    }

}
