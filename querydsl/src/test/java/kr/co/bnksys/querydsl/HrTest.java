package kr.co.bnksys.querydsl;

import static kr.co.bnksys.querydsl.model.hr.QDepartment.department;
import static kr.co.bnksys.querydsl.model.hr.QEmployee.employee;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.querydsl.core.Tuple;
import com.querydsl.jpa.impl.JPAQueryFactory;

@SpringBootTest
class HrTest {

    @Autowired
    JPAQueryFactory query;

    @Test
    void test() {
        assertNotNull(query);

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

    }

}
