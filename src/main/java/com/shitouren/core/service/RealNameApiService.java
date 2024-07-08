package com.shitouren.core.service;

import com.shitouren.core.bean.param.RealNameParam;
import com.shitouren.core.model.dto.RealNameResponseDTO.RealNameDataDto;

import java.util.Optional;

public interface RealNameApiService {
    boolean isRealNameSuccess(RealNameParam RealNameParam);
}
