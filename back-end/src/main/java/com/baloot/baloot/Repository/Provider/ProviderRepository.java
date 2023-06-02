package com.baloot.baloot.Repository.Provider;

import com.baloot.baloot.models.Provider.Provider;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public interface ProviderRepository extends JpaRepository<Provider, Integer> {

    List<Provider> getAllProviders(); // ??

    List<Provider> findProvidersByNameContaining(String providerName);

    Provider getProviderById(int providerId);

}
