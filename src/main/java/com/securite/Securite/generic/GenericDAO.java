package com.securite.Securite.generic;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import lombok.Setter;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class GenericDAO<Entity> {
    @Setter
    private Class<Entity> type;

    public GenericDAO(Class<Entity> type){
        this.type = type;
    }

    @PersistenceContext
    private EntityManager entityManager;

    public Entity save(Entity entity){
        entityManager.persist(entity);

        return entity;
    }

    public Entity update(Entity entity){
        return entityManager.merge(entity);
    }

    public void delete(Entity entity){
        entityManager.remove(entity);
    }

    public Entity findById(long id){
        return entityManager.find(type, id);
    }

    public Long countByAgeAndNationality(String nationalityValue, int minAge) {
        // Calculer la date de naissance limite
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.YEAR, -minAge);
        Date birthDateThreshold = cal.getTime();

        // Simplifier la requête JPQL
        String request = "SELECT COUNT(e) FROM " + type.getSimpleName() + " e " +
                "WHERE e.dateOfBirth <= :birthDateThreshold " +
                "AND e.nationality = :nationalityValue";

        // Création de la requête typée
        TypedQuery<Long> query = entityManager.createQuery(request, Long.class)
                .setParameter("birthDateThreshold", birthDateThreshold)
                .setParameter("nationalityValue", nationalityValue);

        return query.getSingleResult();
    }

    public List<Entity> findAllByAgeAndNationalityAndPlaceOfBirth(
            int minAge,
            String nationality,
            String placeOfBirth,
            int page,
            int size
    ) {
        // Instantier le calendrier pour la date actuelle
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.YEAR, -minAge);
        Date birthDateThreshold = cal.getTime();

        String request = "SELECT e FROM " + type.getSimpleName() + " e " +
                "WHERE e.dateOfBirth <= :birthDateThreshold " +
                "AND e.nationality = :nationality " +
                "AND e.placeOfBirth = :placeOfBirth";

        TypedQuery<Entity> query = entityManager.createQuery(request, type)
                .setParameter("birthDateThreshold", birthDateThreshold)
                .setParameter("nationality", nationality)
                .setParameter("placeOfBirth", placeOfBirth);

        query.setFirstResult(page * size);
        query.setMaxResults(size);

        return query.getResultList();
    }


    public List<Entity> findAllByAttributeNameANDAttributeValue(
            String attributeName,
            Object attributeValue,
            int page,
            int size
    ){
        String request = "SELECT e FROM "+ type.getSimpleName() +" e WHERE e." + attributeName + "=:value";

        TypedQuery<Entity> query = entityManager.createQuery(request, type)
                .setParameter("value", attributeValue);

        query.setFirstResult(page * size);
        query.setMaxResults(size);

        return query.getResultList();
    }


    public List<Entity> findByOrTwoAttributeValue(String attributeName1, String attributeName2, Object value1, Object value2){
        String request = "SELECT e FROM "+ type.getSimpleName() +" e WHERE e." + attributeName1 + "=:value1 OR e." + attributeName2 +"=:value2";

        return entityManager.createQuery(request, type)
                .setParameter("value1", value1)
                .setParameter("value2", value2)
                .getResultList();
    }

    public List<Entity> findByAndThreeAttributeValue(
            String attributeName1,
            String attributeName2,
            String attributeName3,
            Object value1,
            Object value2,
            Object value3
    ){
        String request = "SELECT e FROM "+ type.getSimpleName() +" e WHERE e." + attributeName1 +
                "=:value1 AND e." + attributeName2 +"=:value2 AND e."+ attributeName3 +"=:value3";

        return entityManager.createQuery(request, type)
                .setParameter("value1", value1)
                .setParameter("value2", value2)
                .setParameter("value3", value3)
                .getResultList();
    }

    public List<Entity> findByAndTwoAttributeValue(
            String attributeName1,
            String attributeName2,
            Object value1,
            Object value2
    ){
        String request = "SELECT e FROM "+ type.getSimpleName() +" e WHERE e." + attributeName1 +
                "=:value1 AND e." + attributeName2 +"=:value2";

        return entityManager.createQuery(request, type)
                .setParameter("value1", value1)
                .setParameter("value2", value2)
                .getResultList();
    }

    public List<Entity> findByDynamicTableOnTable(String joinTable, String table, String attributName, Object attributValue){
        String request = "SELECT e FROM " + type.getSimpleName() + " e JOIN e." + joinTable + " j WHERE j."+ table+"." + attributName + "=:attributValue";

        TypedQuery<Entity> query = entityManager.createQuery(request, type);
        query.setParameter("attributValue", attributValue);

        return query.getResultList();
    }

    public List<Entity> findByDynamicOneTable(String joinTable, String attributName, Object attributValue){
        String request = "SELECT e FROM " + type.getSimpleName() + " e JOIN e." + joinTable +
                " j WHERE j." + attributName + "=:attributValue";

        TypedQuery<Entity> query = entityManager.createQuery(request, type);
        query.setParameter("attributValue", attributValue);

        return query.getResultList();
    }

    public Entity findUniqueByAttributeName(String attributeName, Object value) {
        String jpql = "SELECT e FROM " + type.getSimpleName() + " e WHERE e." + attributeName + " = :value";

        Entity entity = entityManager.createQuery(jpql, type)
                .setParameter("value", value)
                .getSingleResult();

        if(entity == null){
            return null;
        }
        return entity;
    }
}
