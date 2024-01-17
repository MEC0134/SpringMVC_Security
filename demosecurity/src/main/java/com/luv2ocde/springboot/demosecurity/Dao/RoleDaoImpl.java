package com.luv2ocde.springboot.demosecurity.Dao;

import com.luv2ocde.springboot.demosecurity.Entity.Role;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Repository;

@Repository
public class RoleDaoImpl implements RoleDao {


    private EntityManager entityManager;

    public RoleDaoImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public Role findRoleByName(String theRoleName) {

        // retrieve/read from database using role name
        TypedQuery<Role> theQuery = entityManager.createQuery("from Role where role=:roleName", Role.class);
        theQuery.setParameter("roleName", theRoleName);

        Role theRole = null;

        try {
            theRole = theQuery.getSingleResult();
        } catch (Exception e) {
            theRole = null;
        }

        return theRole;
    }
}
