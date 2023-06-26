package com.ss.oa.authorization;

import org.springframework.data.repository.CrudRepository;

import com.ss.oashared.model.*;



public interface AuthUserProfileAccessRepository extends CrudRepository<AuthUserProfileAccess, String> {
}
