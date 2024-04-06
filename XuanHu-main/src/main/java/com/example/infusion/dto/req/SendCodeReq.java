package com.example.infusion.dto.req;

import com.example.infusion.common.utils.PhoneDesensitizationSerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SendCodeReq {
    @JsonSerialize(using = PhoneDesensitizationSerializer.class)
    String phone;


}
