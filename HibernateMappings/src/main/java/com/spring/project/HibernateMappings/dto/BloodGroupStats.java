package com.spring.project.HibernateMappings.dto;

import com.spring.project.HibernateMappings.entity.type.BloodGroupType;
import lombok.Data;

@Data
public class BloodGroupStats {
    private final BloodGroupType bloodGroupType;
    private final Long count;
}
