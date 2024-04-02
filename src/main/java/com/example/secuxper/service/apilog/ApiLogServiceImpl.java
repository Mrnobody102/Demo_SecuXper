package com.example.secuxper.service.apilog;

import com.example.secuxper.apilog.ApiLog;
import com.example.secuxper.apilog.ApiLogRepository;
import com.example.secuxper.data.User;
import com.example.secuxper.data.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ApiLogServiceImpl implements ApiLogService {
    private final ApiLogRepository apiLogRepository;

    public ApiLogServiceImpl(ApiLogRepository apiLogRepository) {
        this.apiLogRepository = apiLogRepository;
    }


    @Override
    public ApiLog addLog(ApiLog apiLog) {
        return apiLogRepository.save(apiLog);
    }
}
