package com.innowing.info.repository.primary;

import com.innowing.info.entity.primary.EligibleStudent;
import com.innowing.info.entity.primary.Staff;
import com.innowing.info.model.EligibleStudentPage;
import com.innowing.info.model.EligibleStudentSearchCriteria;
import com.innowing.info.model.StaffPage;
import com.innowing.info.model.StaffSearchCriteria;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Repository
public class StaffCriteriaRepository {
    private final EntityManager entityManager;
    private final CriteriaBuilder criteriaBuilder;

    public StaffCriteriaRepository(@Qualifier("primaryEntityManagerFactory") EntityManager entityManager) {
        this.entityManager = entityManager;
        this.criteriaBuilder = entityManager.getCriteriaBuilder();
    }

    public Page<Staff> findAllWithFilters(StaffPage staffPage,
                                                    StaffSearchCriteria staffSearchCriteria) {
        CriteriaQuery<Staff> criteriaQuery = criteriaBuilder.createQuery(Staff.class);
        Root<Staff> staffRoot = criteriaQuery.from(Staff.class);
        Predicate predicate = getPredicate(staffSearchCriteria, staffRoot);
        criteriaQuery.where(predicate);
        setOrder(staffPage, criteriaQuery, staffRoot);

        TypedQuery<Staff> typedQuery = entityManager.createQuery(criteriaQuery);
        typedQuery.setFirstResult(staffPage.getPageNumber() * staffPage.getPageSize());
        typedQuery.setMaxResults(staffPage.getPageSize());

        Pageable pageable = getPageable(staffPage);

        long staffsCount = getStaffsCount(predicate);

        return new PageImpl<>(typedQuery.getResultList(), pageable, staffsCount);
    }

    private Predicate getPredicate(StaffSearchCriteria staffSearchCriteria,
                                   Root<Staff> eligibleStudentRoot) {
        List<Predicate> predicates = new ArrayList<>();
        if (Objects.nonNull(staffSearchCriteria.getName())) {
            predicates.add(
                    criteriaBuilder.like(eligibleStudentRoot.get("name"),
                            "%" + staffSearchCriteria.getName() + "%")
            );
        }
        if (Objects.nonNull(staffSearchCriteria.getHkuId())) {
            predicates.add(
                    criteriaBuilder.equal(eligibleStudentRoot.get("hkuId"),
                            staffSearchCriteria.getHkuId())
            );
        }
        if (Objects.nonNull(staffSearchCriteria.getFaculty())) {
            predicates.add(
                    criteriaBuilder.like(eligibleStudentRoot.get("faculty"),
                            "%" + staffSearchCriteria.getFaculty() + "%")
            );
        }
        if (Objects.nonNull(staffSearchCriteria.getDepartment())) {
            predicates.add(
                    criteriaBuilder.like(eligibleStudentRoot.get("department"),
                            "%" + staffSearchCriteria.getDepartment() + "%")
            );
        }
        if (Objects.nonNull(staffSearchCriteria.getStaffCategory())) {
            predicates.add(
                    criteriaBuilder.like(eligibleStudentRoot.get("staffCategory"),
                            "%" + staffSearchCriteria.getDepartment() + "%")
            );
        }

        if (Objects.nonNull(staffSearchCriteria.getAccessGranted())) {
            predicates.add(
                    criteriaBuilder.equal(eligibleStudentRoot.get("accessGranted"),
                            staffSearchCriteria.getAccessGranted())
            );
        }
//        if (Objects.nonNull(eligibleStudentSearchCriteria.getMemberStatus())) {
//            predicates.add(
//                    criteriaBuilder.like(eligibleStudentRoot.get("memberStatus"),
//                            "%" + eligibleStudentSearchCriteria.getMemberStatus() + "%")
//            );
//        }
        return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
    }

    private void setOrder(StaffPage eligibleStudentPage,
                          CriteriaQuery<Staff> criteriaQuery,
                          Root<Staff> eligibleStudentRoot) {
        if (eligibleStudentPage.getSortDirection().equals(Sort.Direction.ASC)) {
            criteriaQuery.orderBy(criteriaBuilder.asc(eligibleStudentRoot.get(eligibleStudentPage.getSortBy())));
            return;
        }
        criteriaQuery.orderBy(criteriaBuilder.desc(eligibleStudentRoot.get(eligibleStudentPage.getSortBy())));
    }

    private Pageable getPageable(StaffPage staffPage) {
        Sort sort = Sort.by(staffPage.getSortDirection(), staffPage.getSortBy());
        return PageRequest.of(staffPage.getPageNumber(), staffPage.getPageSize(), sort);
    }

    private long getStaffsCount(Predicate predicate) {
        CriteriaQuery<Long> countQuery = criteriaBuilder.createQuery(Long.class);
        Root<EligibleStudent> countRoot = countQuery.from(EligibleStudent.class);
        countQuery.select(criteriaBuilder.count(countRoot)).where(predicate);
        return entityManager.createQuery(countQuery).getSingleResult();
    }
}
