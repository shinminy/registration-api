package com.kstd.shinmy.registrationapi.dao;

import com.kstd.shinmy.registrationapi.entity.Lecture;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.util.List;

@Repository
public class LectureDAO extends GenericDAO<Lecture, Long> {
    public List<Lecture> findAllList() {
        CriteriaBuilder criteriaBuilder = getCriteriaBuilder();
        CriteriaQuery<Lecture> criteriaQuery = criteriaBuilder.createQuery(Lecture.class);
        criteriaQuery.select(criteriaQuery.from(Lecture.class));
        return getResultList(criteriaQuery);
    }
    
    public List<Lecture> findRegistrableList() {
        String sql = "select * from lecture where start_date <= adddate(now(), 7) and start_date > adddate(now(), -1)";
        return getResultList(sql, Lecture.class);
    }

    public List<Lecture> findListByEmployeeIdNumber(String employeeIdNumber) {
        String sql = "select l.* from lecture l, registration r where r.employee_id_number = '" + employeeIdNumber + "' and l.lecture_id = r.lecture_id";
        return getResultList(sql, Lecture.class);
    }
}
