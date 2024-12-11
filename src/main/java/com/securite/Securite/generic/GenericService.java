package com.securite.Securite.generic;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GenericService<Entity> {
    private final GenericDAO<Entity> genericDAO;

    public GenericService(GenericDAO<Entity> genericDAO) {
        this.genericDAO = genericDAO;
    }

    @Transactional
    public Entity create(Entity entity){
        return this.genericDAO.save(entity);
    }

    @Transactional
    public Entity update(Entity entity, Object slug){
        Entity entity1 = genericDAO.findUniqueByAttributeName("slug", slug);

        if (entity1 == null){
            throw new IllegalArgumentException("L'entité n'existe pas");
        }

        return genericDAO.update(entity);
    }

    public Entity findUniqueWithValueAndAttribut(Object value, String attributName){
        return genericDAO.findUniqueByAttributeName(attributName, value);
    }

    @Transactional
    public String delete(String slug){
        Entity entity = genericDAO.findUniqueByAttributeName("slug", slug);

        if (entity == null){
            throw new IllegalArgumentException("L'entité n'existe pas");
        }

        genericDAO.delete(entity);

        return "Suppression réussie";
    }

    public Entity findUniqueById(long id){
        return genericDAO.findById(id);
    }

    public List<Entity> findAllByAttributeNameAndValue(Object value, String attributName, int page, int size){
        return genericDAO.findAllByAttributeNameANDAttributeValue(attributName, value, page, size);
    }

    public List<Entity> findListOrTwoValue(Object value1, String attributName1, Object value2, String attributName2){
        return genericDAO.findByOrTwoAttributeValue(attributName1, attributName2, value1, value2);
    }

    public List<Entity> findListAndTwoValue(Object value1, String attributName1, Object value2, String attributName2){
        return genericDAO.findByAndTwoAttributeValue(attributName1, attributName2, value1, value2);
    }

    public List<Entity> findListAndThreeValue(
            String attributName1,
            String attributName2,
            String attributName3,
            Object value1,
            Object value2,
            Object value3
    ){
        return genericDAO.findByAndThreeAttributeValue(
                attributName1,
                attributName2,
                attributName3,
                value1,
                value2,
                value3
        );
    }

    public List<Entity> findAllByAgeAndNationality(int minAge, String nationality, String ville, int page, int size){
        return genericDAO.findAllByAgeAndNationalityAndPlaceOfBirth(minAge, nationality, ville, page, size);
    }

    public Long countByAgeAndNationality(int minAge, String nationality){
        return genericDAO.countByAgeAndNationality(nationality, minAge);
    }

    public List<Entity> findDynamicWithJoinTableOnTable(String joinTable, String table, String attributName, Object attributValue){
        return genericDAO.findByDynamicTableOnTable(joinTable, table, attributName, attributValue);
    }

    public List<Entity> findDynamicWithJoinOneTable(String joinTable, String attributName, Object attributValue){
        return genericDAO.findByDynamicOneTable(joinTable, attributName, attributValue);
    }
}
