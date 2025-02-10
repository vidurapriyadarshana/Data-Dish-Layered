//package edu.ijse.datadish.bo;
//
//import org.modelmapper.ModelMapper;
//
//public class DTOConverter {
//    private static final ModelMapper modelMapper = new ModelMapper();
//
//    public static <D, E> E toEntity(D dto, Class<E> entityClass) {
//        return modelMapper.map(dto, entityClass);
//    }
//
//    public static <E, D> D toDTO(E entity, Class<D> dtoClass) {
//        return modelMapper.map(entity, dtoClass);
//    }
//}


package edu.ijse.datadish.bo;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class DTOConverter {
    public static <D, E> E toEntity(D dto, Class<E> entityClass) {
        try {
            E entity = entityClass.getDeclaredConstructor().newInstance();
            copyFields(dto, entity);
            return entity;
        } catch (Exception e) {
            throw new RuntimeException("Error converting DTO to Entity", e);
        }
    }

    public static <E, D> D toDTO(E entity, Class<D> dtoClass) {
        try {
            D dto = dtoClass.getDeclaredConstructor().newInstance();
            copyFields(entity, dto);
            return dto;
        } catch (Exception e) {
            throw new RuntimeException("Error converting Entity to DTO", e);
        }
    }

    public static <S, T> ArrayList<T> toDTOList(List<S> sourceList, Class<T> targetClass) {
        ArrayList<T> dtoList = new ArrayList<>();
        for (S source : sourceList) {
            dtoList.add(toDTO(source, targetClass));
        }
        return dtoList;
    }



    private static void copyFields(Object source, Object target) throws IllegalAccessException {
        Field[] sourceFields = source.getClass().getDeclaredFields();
        Field[] targetFields = target.getClass().getDeclaredFields();

        for (Field sourceField : sourceFields) {
            sourceField.setAccessible(true);
            for (Field targetField : targetFields) {
                if (sourceField.getName().equals(targetField.getName()) &&
                        sourceField.getType().equals(targetField.getType())) {
                    targetField.setAccessible(true);
                    targetField.set(target, sourceField.get(source));
                    break;
                }
            }
        }
    }
}
