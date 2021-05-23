package com.hotel.app.util;

import com.hotel.app.bean.SearchCriteria;
import com.hotel.app.model.Room;
import com.hotel.app.specification.SearchSpecification;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import javax.persistence.criteria.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Component
public class SpecificationUtil {

    private static final SimpleDateFormat DATE_FORMAT_YYYY_MM_DD = new SimpleDateFormat("yyyy-MM-dd");

    public Specification andAll(List<SearchCriteria> searchCriteria) {

        if(searchCriteria==null || searchCriteria.size()==0) return null;

        Specification spec = Specification.where(new SearchSpecification<Room>(searchCriteria.get(0)));

        return searchCriteria.stream().skip(1)
                .map(SearchSpecification<Room>::new)
                .map(Specification::where)
                .reduce(spec, Specification::and);
    }

    public static Predicate getSpecification(Root<?> root, CriteriaBuilder builder, SearchCriteria criteria) {
        Path<?> rootPath = root;
        String key = criteria.getKey();
        if(criteria.getKey().contains(".")) {
            List<String> list = Arrays.asList(criteria.getKey().split("\\."));
            key = list.get(list.size()-1);
            for(String path: list) {
                if(!path.equals(key))
                    rootPath = rootPath.get(path);
            }
        }

        if(criteria.getOperation().equalsIgnoreCase(">")) {
            if(rootPath.get(key).getJavaType() == Date.class) {
                try {
                    return builder.greaterThanOrEqualTo(rootPath.get(key),
                            DATE_FORMAT_YYYY_MM_DD.parse(criteria.getValue().toString()));
                } catch (ParseException e) {
                    return null;
                }
            }
            return builder.greaterThanOrEqualTo(
                    rootPath.get(key), criteria.getValue().toString()
            );
        } else if (criteria.getOperation().equalsIgnoreCase("<")) {
            if(rootPath.get(key).getJavaType() == Date.class) {
                try {
                    return builder.lessThanOrEqualTo(rootPath.get(key),
                            DATE_FORMAT_YYYY_MM_DD.parse(criteria.getValue().toString()));
                } catch (ParseException e) {
                    return null;
                }
            }
            return builder.lessThanOrEqualTo(
                    rootPath.get(key), criteria.getValue().toString());
        } else if (criteria.getOperation().equalsIgnoreCase(":")) {
            return rootPath.get(key).in(criteria.getValue().toString().split(","));
        } else if (criteria.getOperation().equalsIgnoreCase("=")) {
            if(rootPath.get(key).getJavaType() == Date.class) {
                try {
                    return builder.equal(rootPath.get(key),
                            DATE_FORMAT_YYYY_MM_DD.parse(criteria.getValue().toString()));
                } catch (ParseException e) {
                    return null;
                }
            }
            if (rootPath.get(key).getJavaType() == String.class) {
                return builder.like(
                        rootPath.get(key), "%" + criteria.getValue() + "%");
            } else {
                return builder.equal(rootPath.get(key), criteria.getValue());
            }
        }
        return null;
    }
}
