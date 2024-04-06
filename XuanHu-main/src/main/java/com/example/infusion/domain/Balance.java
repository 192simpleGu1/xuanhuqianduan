package com.example.infusion.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 
 * @TableName balance
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Balance implements Serializable {
    /**
     * 
     */
    private String id;

    /**
     * 
     */
    private Double balance;

    /**
     * 
     */
    private String phone;

    private static final long serialVersionUID = 1L;


}