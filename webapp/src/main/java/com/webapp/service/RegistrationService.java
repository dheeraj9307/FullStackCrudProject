package com.webapp.service;

import java.util.List;

import com.webapp.entity.Registration;

public interface RegistrationService {
public void addRegistration(Registration registration);

public List<Registration> findAllRegistrations();

public void deleteRegistration(long id);

public Registration getRegistrationById(long id);

public void updateRegistration(Registration registration);
}
