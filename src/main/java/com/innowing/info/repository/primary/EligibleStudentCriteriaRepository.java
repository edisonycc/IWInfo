package com.innowing.info.repository.primary;

import com.innowing.info.entity.primary.EligibleStudent;
import com.innowing.info.model.EligibleStudentPage;
import com.innowing.info.model.EligibleStudentSearchCriteria;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Repository
public class EligibleStudentCriteriaRepository {
    private final EntityManager entityManager;
    private final CriteriaBuilder criteriaBuilder;

    public EligibleStudentCriteriaRepository(@Qualifier("primaryEntityManagerFactory") EntityManager entityManager) {
        this.entityManager = entityManager;
        this.criteriaBuilder = entityManager.getCriteriaBuilder();
    }

    public Page<EligibleStudent> findAllWithFilters(EligibleStudentPage eligibleStudentPage,
                                            EligibleStudentSearchCriteria eligibleStudentSearchCriteria) {
        CriteriaQuery<EligibleStudent> criteriaQuery = criteriaBuilder.createQuery(EligibleStudent.class);
        Root<EligibleStudent> eligibleStudentRoot = criteriaQuery.from(EligibleStudent.class);
        Predicate predicate = getPredicate(eligibleStudentSearchCriteria, eligibleStudentRoot);
        criteriaQuery.where(predicate);
        setOrder(eligibleStudentPage, criteriaQuery, eligibleStudentRoot);

        TypedQuery<EligibleStudent> typedQuery = entityManager.createQuery(criteriaQuery);
        typedQuery.setFirstResult(eligibleStudentPage.getPageNumber() * eligibleStudentPage.getPageSize());
        typedQuery.setMaxResults(eligibleStudentPage.getPageSize());

        Pageable pageable = getPageable(eligibleStudentPage);

        long eligibleStudentsCount = getEligibleStudentsCount(predicate);

        return new PageImpl<>(typedQuery.getResultList(), pageable, eligibleStudentsCount);
    }

    private Predicate getPredicate(EligibleStudentSearchCriteria eligibleStudentSearchCriteria,
                                   Root<EligibleStudent> eligibleStudentRoot) {
        List<Predicate> predicates = new ArrayList<>();
        if (Objects.nonNull(eligibleStudentSearchCriteria.getName())) {
            predicates.add(
                    criteriaBuilder.like(eligibleStudentRoot.get("name"),
                            "%" + eligibleStudentSearchCriteria.getName() + "%")
            );
        }
        if (Objects.nonNull(eligibleStudentSearchCriteria.getHkuId())) {
            predicates.add(
                    criteriaBuilder.equal(eligibleStudentRoot.get("hkuId"),
                            eligibleStudentSearchCriteria.getHkuId())
            );
        }
        if (Objects.nonNull(eligibleStudentSearchCriteria.getMissingHkuId())) {
            predicates.add(
                    criteriaBuilder.isNull(eligibleStudentRoot.get("hkuId"))
            );
        }
        if (Objects.nonNull(eligibleStudentSearchCriteria.getEmail())) {
            predicates.add(
                    criteriaBuilder.like(eligibleStudentRoot.get("email"),
                            "%" + eligibleStudentSearchCriteria.getEmail() + "%")
            );
        }
        if (Objects.nonNull(eligibleStudentSearchCriteria.getStudyYear())) {
            predicates.add(
                    criteriaBuilder.equal(eligibleStudentRoot.get("studyYear"),
                            eligibleStudentSearchCriteria.getStudyYear())
            );
        }
        if (Objects.nonNull(eligibleStudentSearchCriteria.getGender())) {
            predicates.add(
                    criteriaBuilder.equal(eligibleStudentRoot.get("gender"),
                            eligibleStudentSearchCriteria.getGender())
            );
        }
        if (Objects.nonNull(eligibleStudentSearchCriteria.getFaculty())) {
            predicates.add(
                    criteriaBuilder.like(eligibleStudentRoot.get("faculty"),
                            "%" + eligibleStudentSearchCriteria.getFaculty() + "%")
            );
        }
        if (Objects.nonNull(eligibleStudentSearchCriteria.getDepartment())) {
            predicates.add(
                    criteriaBuilder.like(eligibleStudentRoot.get("department"),
                            "%" + eligibleStudentSearchCriteria.getDepartment() + "%")
            );
        }
        if (Objects.nonNull(eligibleStudentSearchCriteria.getCurriculum())) {
            predicates.add(
                    criteriaBuilder.like(eligibleStudentRoot.get("curriculum"),
                            "%" + eligibleStudentSearchCriteria.getCurriculum() + "%")
            );
        }
        if (
                Objects.nonNull(eligibleStudentSearchCriteria.getAppliedMembership()) &&
                        eligibleStudentSearchCriteria.getAppliedMembership()
        ) {
            predicates.add(
//                    criteriaBuilder.equal(eligibleStudentRoot.get("appliedMembership"),
//                            eligibleStudentSearchCriteria.getAppliedMembership())
                    criteriaBuilder.isNotNull(eligibleStudentRoot.get("appliedMembership"))
            );
            predicates.add(
                    criteriaBuilder.isNotNull(eligibleStudentRoot.get("appliedMembership"))
            );
        }
        if (
                Objects.nonNull(eligibleStudentSearchCriteria.getAgreedToPay()) &&
                        eligibleStudentSearchCriteria.getAgreedToPay()
        ) {
            predicates.add(
                    criteriaBuilder.isNotNull(eligibleStudentRoot.get("agreedToPay"))
            );
        }
        if (
                Objects.nonNull(eligibleStudentSearchCriteria.getSiteVisited()) &&
                        eligibleStudentSearchCriteria.getSiteVisited()
        ) {
            predicates.add(
                    criteriaBuilder.isNotNull(eligibleStudentRoot.get("siteVisited"))
            );
        }
        if (
                Objects.nonNull(eligibleStudentSearchCriteria.getPassedQuiz()) &&
                        eligibleStudentSearchCriteria.getPassedQuiz()
        ) {
            predicates.add(
                    criteriaBuilder.isNotNull(eligibleStudentRoot.get("passedQuiz"))
            );
        }
        if (
                Objects.nonNull(eligibleStudentSearchCriteria.getDepositPaid()) &&
                        eligibleStudentSearchCriteria.getDepositPaid()
        ) {
            predicates.add(
                    criteriaBuilder.isNotNull(eligibleStudentRoot.get("depositPaid"))
            );
        }
        if (
                Objects.nonNull(eligibleStudentSearchCriteria.getSentToFeo()) &&
                        eligibleStudentSearchCriteria.getSentToFeo()
        ) {
            predicates.add(
                    criteriaBuilder.isNotNull(eligibleStudentRoot.get("sentToFeo"))
            );
        }
        if (
                Objects.nonNull(eligibleStudentSearchCriteria.getConfirmedMember()) &&
                        eligibleStudentSearchCriteria.getConfirmedMember()
        ) {
            predicates.add(
                    criteriaBuilder.isNotNull(eligibleStudentRoot.get("confirmedMember"))
            );
        }
        if (
                Objects.nonNull(eligibleStudentSearchCriteria.getAccessGranted()) &&
                        eligibleStudentSearchCriteria.getAccessGranted()
        ) {
            predicates.add(
                    criteriaBuilder.equal(eligibleStudentRoot.get("accessGranted"),
                            eligibleStudentSearchCriteria.getAccessGranted())
            );
        }
        if (
                Objects.nonNull(eligibleStudentSearchCriteria.getIsActive()) &&
                        eligibleStudentSearchCriteria.getIsActive()
        ) {
            predicates.add(
                    criteriaBuilder.equal(eligibleStudentRoot.get("isActive"),
                            eligibleStudentSearchCriteria.getIsActive())
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

    private void setOrder(EligibleStudentPage eligibleStudentPage,
                          CriteriaQuery<EligibleStudent> criteriaQuery,
                          Root<EligibleStudent> eligibleStudentRoot) {
        if (eligibleStudentPage.getSortDirection().equals(Sort.Direction.ASC)) {
            criteriaQuery.orderBy(criteriaBuilder.asc(eligibleStudentRoot.get(eligibleStudentPage.getSortBy())));
            return;
        }
        criteriaQuery.orderBy(criteriaBuilder.desc(eligibleStudentRoot.get(eligibleStudentPage.getSortBy())));
    }

    private Pageable getPageable(EligibleStudentPage eligibleStudentPage) {
        Sort sort = Sort.by(eligibleStudentPage.getSortDirection(), eligibleStudentPage.getSortBy());
        return PageRequest.of(eligibleStudentPage.getPageNumber(), eligibleStudentPage.getPageSize(), sort);
    }

    private long getEligibleStudentsCount(Predicate predicate) {
        CriteriaQuery<Long> countQuery = criteriaBuilder.createQuery(Long.class);
        Root<EligibleStudent> countRoot = countQuery.from(EligibleStudent.class);
        countQuery.select(criteriaBuilder.count(countRoot)).where(predicate);
        return entityManager.createQuery(countQuery).getSingleResult();
    }

}
