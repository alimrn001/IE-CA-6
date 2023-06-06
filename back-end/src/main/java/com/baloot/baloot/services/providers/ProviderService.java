package com.baloot.baloot.services.providers;

import com.baloot.baloot.services.BalootService;
import com.baloot.baloot.DTO.ProviderDTO;
import com.baloot.baloot.Exceptions.ProviderNotExistsException;
import com.baloot.baloot.models.Provider.Provider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProviderService {
    @Autowired
    private BalootService balootService;

    public ProviderDTO getBalootProvider(int providerId) throws Exception {
        Provider provider = balootService.getProviderById(providerId);
        if(provider == null)
            throw new ProviderNotExistsException();
        ProviderDTO providerDTO = new ProviderDTO
                (provider.getId(), provider.getName(),
                 provider.getRegistryDate(), provider.getCommoditiesNum(),
                 provider.getAvgCommoditiesRate(), provider.getImage());
        // any thing more ??
        return providerDTO;
    }

}
