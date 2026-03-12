package com.mc.icmc.dto.mapper;

import com.mc.icmc.domain.Modules;
import com.mc.icmc.dto.request.ModulesRequest;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-03-11T12:33:42+0100",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 18.0.2.1 (Oracle Corporation)"
)
@Component
public class IModulesMapperImpl implements IModulesMapper {

    @Override
    public Modules toEntity(ModulesRequest request) {
        if ( request == null ) {
            return null;
        }

        Modules modules = new Modules();

        modules.setModuleName( request.getModuleName() );

        return modules;
    }
}
