package com.hotel.app.specification;

import com.hotel.app.bean.SearchCriteria;
import com.hotel.app.util.SpecificationUtil;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.*;

@AllArgsConstructor
public class SearchSpecification<T> implements Specification<T> {

    private SearchCriteria criteria;

    @Override
    public Predicate toPredicate(Root<T> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder builder) {
        return SpecificationUtil.getSpecification(root, builder, criteria);
    }
}
