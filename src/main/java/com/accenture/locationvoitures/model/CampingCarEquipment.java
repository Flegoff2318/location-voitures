package com.accenture.locationvoitures.model;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class CampingCarEquipment {
    private Long id;
    private Integer numberOfBerths;
    private Boolean cookingEquipment;
    private Boolean fridge;
    private Boolean shower;
    private Boolean bedLinen;
}
