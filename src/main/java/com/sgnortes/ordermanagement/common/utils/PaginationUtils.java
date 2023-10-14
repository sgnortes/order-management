package com.sgnortes.ordermanagement.common.utils;

import com.sgnortes.ordermanagement.common.constants.ErrorMessages;
import org.springframework.batch.item.database.Order;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Utils class that contains common methods for creating paginated endpoints.
 * @author Sergio
 */
public class PaginationUtils {

    public static final String  EQUAL = "eq";
    public static final String NOT_EQUAL = "neq";
    public static final String GREATER_THAN = "gt";
    public static final String LESS_THAN = "lt";
    public static final String LIKE = "like";
    public static final String IN = "in";
    public static final String SEPARATOR = ":";
    public static final String ORDER_ASCENDING = "asc";
    public static final String ORDER_DESCENDING = "desc";

    private static final Map<Class<?>, Function<String, ?>> CASTING_MAP = new HashMap<>();

    public PaginationUtils(){

    }

    static {
        // Initialization of castings map
        CASTING_MAP.put(Integer.class, Integer::valueOf);
        CASTING_MAP.put(Long.class, Long::valueOf);
        CASTING_MAP.put(Double.class, Double::valueOf);
        CASTING_MAP.put(String.class, String::valueOf);
    }

    /**
     * Method created to create specifications for the following cases: equal, not equal, greater than, less than, like and in.
     * It is used in methods where you want to apply some type of filtering.
     *
     * @param filter the filter applied (For example -> eq:2)
     * @param fieldName the name of the field
     * @param type the class of the value we are filtering (Integer, Double, etc)
     * @return Specification with the filter applied
     * @param <T>
     */
    public static <T> Specification<T> createSpecification(String filter, String fieldName, Class type){

        final int filterPosition = 0;
        final int valuePosition = 1;

        // We need a filter to apply the specification
        if(Objects.isNull(filter) || filter.isBlank()){
            return null;
        }

        // We create specification depending on its type.
        String[] splitFilter = filter.split(SEPARATOR);


        switch(splitFilter[filterPosition]){

            case EQUAL:
                return (root, query, criteriaBuilder) ->
                        criteriaBuilder.equal(root.get(fieldName), CASTING_MAP.get(type).apply(splitFilter[valuePosition]));
            case NOT_EQUAL:
                return (root, query, criteriaBuilder) ->
                        criteriaBuilder.notEqual(root.get(fieldName), CASTING_MAP.get(type).apply(splitFilter[valuePosition]));
            case GREATER_THAN:
                return (root, query, criteriaBuilder) ->
                        criteriaBuilder.gt(root.get(fieldName), (Number) CASTING_MAP.get(type).apply(splitFilter[valuePosition]));
            case LESS_THAN:
                return (root, query, criteriaBuilder) ->
                        criteriaBuilder.lt(root.get(fieldName), (Number) CASTING_MAP.get(type).apply(splitFilter[valuePosition]));
            case LIKE:
                return (root, query, criteriaBuilder) ->
                        criteriaBuilder.like(root.get(fieldName), "%" + CASTING_MAP.get(type).apply(splitFilter[valuePosition]) + "%");
            case IN:
                Stream<String> values = Arrays.stream(splitFilter[valuePosition].split(","));
                return (root, query, criteriaBuilder) ->
                        criteriaBuilder.in(root.get(fieldName)).value(values.map(value -> CASTING_MAP.get(type).apply(value)).collect(Collectors.toList()));
            default:
                throw new IllegalArgumentException(ErrorMessages.SPECIFICATION_NOT_SUPPORTED.getMessage());
        }

    }

    /**
     * Method used to transform an array of strings with the following format 'property,direction' to a Sort object
     * @param sortFields array of string with this format 'property,direction'
     * @return Sort object.
     */
    public static Sort createSort(String[] sortFields){

        List<Sort.Order> ordering = Arrays.stream(sortFields).map(field -> {

            String[] orderSplit = field.split(SEPARATOR);

            if(orderSplit.length != 2){
                throw new IllegalArgumentException(ErrorMessages.ORDER_NOT_VALID.getMessage());
            }

            switch(orderSplit[1]){
                case ORDER_ASCENDING:
                    return Sort.Order.asc(orderSplit[0]);
                case ORDER_DESCENDING:
                    return Sort.Order.desc(orderSplit[0]);
                default:
                    throw new IllegalArgumentException(ErrorMessages.ORDER_NOT_SUPPORTED.getMessage());
            }

        }).collect(Collectors.toList());

        return Sort.by(ordering);

    }

}
