package com.accenture.locationvoitures.model;

import com.accenture.locationvoitures.exception.VehicleException;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class CampingCarEquipment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer numberOfBerths;
    private Boolean cookingEquipment;
    private Boolean fridge;
    private Boolean shower;
    private Boolean bedLinen;

    public void applyPatch(CampingCarEquipment campingCarEquipment) {
        if(campingCarEquipment.getNumberOfBerths()!=null)
            this.setNumberOfBerths(campingCarEquipment.getNumberOfBerths());
        if(campingCarEquipment.getCookingEquipment()!=null)
            this.setCookingEquipment(campingCarEquipment.getCookingEquipment());
        if(campingCarEquipment.getFridge()!=null)
            this.setFridge(campingCarEquipment.getFridge());
        if(campingCarEquipment.getShower()!=null)
            this.setShower(campingCarEquipment.getShower());
        if(campingCarEquipment.getBedLinen()!=null)
            this.setBedLinen(campingCarEquipment.getBedLinen());
    }

    public void validate() {
        if(this.getNumberOfBerths()==null)
            throw new VehicleException("equipment.numberofberths.null", HttpStatus.BAD_REQUEST);
        if(this.getNumberOfBerths()<0)
            throw new VehicleException("equipment.numberofberths.invalid", HttpStatus.BAD_REQUEST);
        if(this.getCookingEquipment()==null)
            throw new VehicleException("equipment.cookingequipment.null", HttpStatus.BAD_REQUEST);
        if(this.getFridge()==null)
            throw new VehicleException("equipment.fridge.null", HttpStatus.BAD_REQUEST);
        if(this.getShower()==null)
            throw new VehicleException("equipment.shower.null", HttpStatus.BAD_REQUEST);
        if(this.getBedLinen()==null)
            throw new VehicleException("equipment.bedlinen.null", HttpStatus.BAD_REQUEST);
    }

    public void checkUpdateData() {
        if(this.getNumberOfBerths()!=null && getNumberOfBerths()<0)
            throw new VehicleException("equipment.numberofberths.invalid", HttpStatus.BAD_REQUEST);
    }
}
