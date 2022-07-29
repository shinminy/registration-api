package com.kstd.shinmy.registrationapi.dao;

import com.kstd.shinmy.registrationapi.entity.Registration;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@Repository
public class RegistrationDAO extends GenericDAO<Registration, Long> {
    public List<Registration> findByLectureId(long lectureId) {
        CriteriaBuilder criteriaBuilder = getCriteriaBuilder();
        CriteriaQuery<Registration> criteriaQuery = criteriaBuilder.createQuery(Registration.class);
        Root<Registration> r = criteriaQuery.from(Registration.class);
        criteriaQuery.select(r).where(criteriaBuilder.equal(r.get("lectureId"), lectureId));
        return getResultList(criteriaQuery);
    }
}
