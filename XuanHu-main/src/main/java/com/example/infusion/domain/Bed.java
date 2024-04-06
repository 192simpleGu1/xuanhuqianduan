package com.example.infusion.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 
 * @TableName bed
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Bed implements Serializable {
    /**
     * 床位id
     */
    private String bedId;

    /**
     * 用户
     */
    private String phone;

    private static final long serialVersionUID = 1L;



}