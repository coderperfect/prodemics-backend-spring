package com.mrityunjoy.prodemics.audit;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import com.mrityunjoy.prodemics.model.EndUser;
import com.mrityunjoy.prodemics.repository.EndUserRepository;

@Component("auditAwareImpl")
public class AuditAwareImpl implements AuditorAware<EndUser> {

	private EndUserRepository endUserRepository;
	
	@Autowired
	public AuditAwareImpl(EndUserRepository endUserRepository) {
		this.endUserRepository = endUserRepository;
	}
	
	@Override
	public Optional<EndUser> getCurrentAuditor() {
		return endUserRepository.getByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
	}

}
