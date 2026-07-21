package com.mrityunjoy.prodemics.audit;

import java.util.Optional;

import jakarta.persistence.EntityManager;
import org.jspecify.annotations.NonNull;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import com.mrityunjoy.prodemics.model.EndUser;

@Component("auditAwareImpl")
public class AuditAwareImpl implements AuditorAware<EndUser> {

	private final EntityManager entityManager;

	public AuditAwareImpl(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
	
	@Override
	@NonNull
	public Optional<EndUser> getCurrentAuditor() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

		if (authentication == null || !authentication.isAuthenticated() ||
				authentication instanceof AnonymousAuthenticationToken) {
			return Optional.empty();
		}

		return Optional.of(entityManager.getReference(EndUser.class, authentication.getName()));

	}

}
