package com.installertrackingws.installertrackingws.utility.department;

import com.installertrackingws.installertrackingws.bean.department.DepartmentBn;
import com.installertrackingws.installertrackingws.bean.network.Response;
import com.installertrackingws.installertrackingws.bean.user.UserBn;
import com.installertrackingws.installertrackingws.model.department.Department;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.web.bind.annotation.RequestBody;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.servlet.http.HttpServletRequest;
import java.math.BigInteger;
import java.util.List;

public class DepartmentUtl {

    public List<DepartmentBn> getDepartmentList(EntityManagerFactory entityManagerFactory){

        SessionFactory sessionFactory = entityManagerFactory.unwrap(SessionFactory.class);
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        Query query = session.createNativeQuery("select  * from Department",Department.class);

        List<DepartmentBn> departmentBnList = query.getResultList();

        session.getTransaction().commit();
        session.close();

        return departmentBnList;

    }

    public Response save(EntityManagerFactory entityManagerFactory, HttpServletRequest httpServletRequest, @RequestBody DepartmentBn departmentBean){

        Response response = new Response();

        SessionFactory sessionFactory = entityManagerFactory.unwrap(SessionFactory.class);
        Transaction transaction = null;

        try (Session session = sessionFactory.openSession()) {

            transaction = session.beginTransaction();

            Query query = session.createNativeQuery("SELECT count(id) FROM department WHERE rank = :rk");
            query.setParameter("rk",departmentBean.getRank());

            if (query.getResultList().get(0).toString().equals("0")){

                BigInteger oId = (BigInteger) session.createNativeQuery("SELECT IFNULL(MAX(o_id),100)+1 AS o_id FROM department").getResultList().get(0);

                Department department = new Department();
                department.setIp(httpServletRequest.getRemoteAddr());
                department.setName(departmentBean.getName());
                department.setRank(departmentBean.getRank());
                department.setoId(oId.intValue());
                department.setModifiedBy(departmentBean.getUserBn().getId());
                session.save(department);

                response.setMsg("Department save successfully !");
                response.setCode(200);

            }else {

                response.setMsg("This rank already exist !");
                response.setCode(400);

            }

            transaction.commit();

        } catch (Exception e) {
            response.setCode(400);
            response.setMsg("Exception occurred");
            if (transaction != null) {
                transaction.rollback();
                throw e;
            }
        }

        return response;

    }

    public List<Department> getDepartmentByUser(EntityManagerFactory entityManagerFactory, UserBn userBn){

        SessionFactory sessionFactory = entityManagerFactory.unwrap(SessionFactory.class);
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        Query rankQuery = session.createNativeQuery("SELECT * FROM department WHERE o_id = :deptId",Department.class);
        rankQuery.setParameter("deptId",userBn.getDeptId());

        Department department = (Department) rankQuery.getSingleResult();
        int rank = department.getRank();

        Query departmentListQeury = session.createNativeQuery("SELECT * FROM department WHERE rank >= :rank",Department.class);
        departmentListQeury.setParameter("rank",rank);

        List<Department> departmentList = departmentListQeury.getResultList();

        session.getTransaction().commit();
        session.close();

        return departmentList;

    }

}
