package com.mc.icmc.service;

import com.mc.icmc.domain.Nationalite;
import java.util.List;

public interface INationaliteService {
    List<Nationalite> getAll();
    Nationalite create(Nationalite nationalite);
}