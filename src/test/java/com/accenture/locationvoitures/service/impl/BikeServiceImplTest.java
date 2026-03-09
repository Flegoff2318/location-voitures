package com.accenture.locationvoitures.service.impl;

import com.accenture.locationvoitures.exception.VehicleException;
import com.accenture.locationvoitures.fake.FakeBikeMapper;
import com.accenture.locationvoitures.fake.FakeBikeRepository;
import com.accenture.locationvoitures.model.enumeration.EBikeType;
import com.accenture.locationvoitures.model.enumeration.EDrivingLicence;
import com.accenture.locationvoitures.service.dto.request.vehicle.BikeRequestDto;
import com.accenture.locationvoitures.service.dto.request.vehicle.VehicleMetaDataRequestDto;
import com.accenture.locationvoitures.service.dto.request.vehicle.patch.BikePatchRequestDto;
import com.accenture.locationvoitures.service.dto.response.admin.vehicle.BikeAdminResponseDto;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class BikeServiceImplTest {

    private FakeBikeRepository bikeRepository;
    private BikeServiceImpl service;

    @BeforeEach
    void init() {
        this.bikeRepository = new FakeBikeRepository();
        FakeBikeMapper bikeMapper = new FakeBikeMapper();
        this.service = new BikeServiceImpl(bikeRepository, bikeMapper);
    }

    @Nested
    @DisplayName("addBike")
    class AddBikeTests {
        @Test
        @DisplayName("OK")
        void addBikeOk() {
            VehicleMetaDataRequestDto metaDataReq = new VehicleMetaDataRequestDto(5.1, 100, true, false);
            BikeRequestDto req = new BikeRequestDto(
                    "BikeBrand",
                    "BikeModel",
                    "BikeColor",
                    metaDataReq,
                    15,
                    40,
                    true,
                    false,
                    null,
                    null,
                    EBikeType.MTB
            );
            BikeAdminResponseDto res = service.add(req);
            // Test Vehicle values
            assertEquals("BikeBrand", res.brand());
            assertEquals("BikeModel", res.model());
            assertEquals("BikeColor", res.color());
            assertNotNull(res.vehicleMetaData());
            assertEquals(EDrivingLicence.NONE, res.requiredDrivingLicence());

            // Test Metadata values
            assertEquals(5.1, res.vehicleMetaData().dailyRentalPrice());
            assertEquals(100, res.vehicleMetaData().mileage());
            assertTrue(res.vehicleMetaData().active());
            assertFalse(res.vehicleMetaData().outOfFleet());

            // Test Bike values
            assertEquals(15, res.weight());
            assertEquals(40, res.frameSize());
            assertTrue(res.discBrakes());
            assertFalse(res.isElectric());
            assertNull(res.batteryCapacity());
            assertNull(res.autonomy());
            assertEquals(EBikeType.MTB, res.bikeType());


            assertEquals(1, bikeRepository.store.size());
        }

        @Test
        @DisplayName("Invalid (null)")
        void addBikeNull() {
            assertThrows(VehicleException.class, () -> service.add(null), "dto.null");
        }

        @Test
        @DisplayName("Invalid (discBrakes null)")
        void addBikeDiscBrakesNull() {
            VehicleMetaDataRequestDto metaDataReq = new VehicleMetaDataRequestDto(5.1, 100, true, false);
            BikeRequestDto req = new BikeRequestDto(
                    "BikeBrand",
                    "BikeModel",
                    "BikeColor",
                    metaDataReq,
                    15,
                    40,
                    null,
                    false,
                    null,
                    null,
                    EBikeType.MTB
            );
            // Supposed to be a constraint exception, but we don't have the @Valid in here
            // We get a VehicleException instead from the validate() method inside of Bike class.
            assertThrows(VehicleException.class, () -> service.add(req), "bike.discbrakes.null");
        }

        @Test
        @DisplayName("Invalid (model null)")
        void addBikeModelNull() {
            VehicleMetaDataRequestDto metaDataReq = new VehicleMetaDataRequestDto(5.1, 100, true, false);
            BikeRequestDto req = new BikeRequestDto(
                    "BikeBrand",
                    null,
                    "BikeColor",
                    metaDataReq,
                    15,
                    40,
                    true,
                    false,
                    null,
                    null,
                    EBikeType.MTB
            );
            // Supposed to be a constraint exception, but we don't have the @Valid in here
            // We get a VehicleException instead from the validate() method inside of Bike class.
            assertThrows(VehicleException.class, () -> service.add(req));
        }

        @Test
        @DisplayName("Invalid (model blank)")
        void addBikeModelBlank() {
            VehicleMetaDataRequestDto metaDataReq = new VehicleMetaDataRequestDto(5.1, 100, true, false);
            BikeRequestDto req = new BikeRequestDto(
                    "BikeBrand",
                    "     ",
                    "BikeColor",
                    metaDataReq,
                    15,
                    40,
                    true,
                    false,
                    null,
                    null,
                    EBikeType.MTB
            );
            // Supposed to be a constraint exception, but we don't have the @Valid in here
            // We get a VehicleException instead from the validate() method inside of Bike class.
            assertThrows(VehicleException.class, () -> service.add(req), "vehicle.model.blank");
        }

        @Test
        @DisplayName("Invalid (electric true + autonomy null)")
        void addBikeElectricTrueAutonomyNull() {
            VehicleMetaDataRequestDto metaDataReq = new VehicleMetaDataRequestDto(5.1, 100, true, false);
            BikeRequestDto req = new BikeRequestDto(
                    "BikeBrand",
                    "BikeModel",
                    "BikeColor",
                    metaDataReq,
                    15,
                    40,
                    true,
                    true,
                    3,
                    null,
                    EBikeType.MTB
            );
            // Supposed to be a constraint exception, but we don't have the @Valid in here
            // We get a VehicleException instead from the validate() method inside of Bike class.
            assertThrows(VehicleException.class, () -> service.add(req), "bike.autonomy.null");
        }

        @Test
        @DisplayName("Invalid (active true & out of fleet true)")
        void addBikeActiveTrueOutOfFleetTrue() {
            VehicleMetaDataRequestDto metaDataReq = new VehicleMetaDataRequestDto(5.1, 100, true, true);
            BikeRequestDto req = new BikeRequestDto(
                    "BikeBrand",
                    "BikeModel",
                    "BikeColor",
                    metaDataReq,
                    15,
                    40,
                    true,
                    true,
                    3,
                    3.0,
                    EBikeType.MTB
            );
            // Supposed to be a constraint exception, but we don't have the @Valid in here
            // We get a VehicleException instead from the validate() method inside of Bike class.
            assertThrows(VehicleException.class, () -> service.add(req), "metadata.activeoutoffleet");
        }
    }

    @Nested
    @DisplayName("getBike")
    class GetBike {
        @Test
        @DisplayName("OK")
        void getByIdOK() {
            VehicleMetaDataRequestDto metaDataReq = new VehicleMetaDataRequestDto(5.1, 100, true, false);
            BikeRequestDto req = new BikeRequestDto(
                    "BikeBrand",
                    "BikeModel",
                    "BikeColor",
                    metaDataReq,
                    15,
                    40,
                    true,
                    false,
                    null,
                    null,
                    EBikeType.MTB
            );
            BikeAdminResponseDto res = service.add(req);
            assertEquals(1, bikeRepository.store.size());
            assertDoesNotThrow(() -> service.getById(res.uuid()));
        }

        @Test
        @DisplayName("NOK (Entity Not Found)")
        void getByIdNOK() {
            assertEquals(0, bikeRepository.store.size());
            UUID uuid = UUID.randomUUID();
            assertThrows(EntityNotFoundException.class, () -> service.getById(uuid), "Bike not found");
        }
    }

    @Nested
    @DisplayName("deleteBike")
    class DeleteBike {
        @Test
        @DisplayName("OK")
        void getByIdOK() {
            VehicleMetaDataRequestDto metaDataReq = new VehicleMetaDataRequestDto(5.1, 100, true, false);
            BikeRequestDto req = new BikeRequestDto(
                    "BikeBrand",
                    "BikeModel",
                    "BikeColor",
                    metaDataReq,
                    15,
                    40,
                    true,
                    false,
                    null,
                    null,
                    EBikeType.MTB
            );
            BikeAdminResponseDto res = service.add(req);
            assertEquals(1, bikeRepository.store.size());
            assertDoesNotThrow(() -> service.delete(res.uuid()));
            assertEquals(0, bikeRepository.store.size());
        }

        @Test
        @DisplayName("NOK (Entity Not Found)")
        void deleteByIdNOK() {
            assertEquals(0, bikeRepository.store.size());
            UUID uuid = UUID.randomUUID();
            assertThrows(EntityNotFoundException.class, () -> service.delete(uuid), "Bike not found");
        }
    }

    @Nested
    @DisplayName("getBikes")
    class getBikes {
        @Test
        @DisplayName("OK, 3 units")
        void getBikesOK() {
            VehicleMetaDataRequestDto metaDataReq = new VehicleMetaDataRequestDto(5.1, 100, true, false);
            BikeRequestDto req = new BikeRequestDto(
                    "BikeBrand",
                    "BikeModel",
                    "BikeColor",
                    metaDataReq,
                    15,
                    40,
                    true,
                    false,
                    null,
                    null,
                    EBikeType.MTB
            );
            service.add(req);
            service.add(req);
            service.add(req);

            assertEquals(3, bikeRepository.store.size());
            List<BikeAdminResponseDto> bikes = service.findAll();
            assertEquals(3, bikes.size());
        }

        @Test
        @DisplayName("OK, Empty")
        void getBikesEmptyOK() {
            assertEquals(0, bikeRepository.store.size());
            List<BikeAdminResponseDto> bikes = service.findAll();
            assertEquals(0, bikes.size());
        }
    }

    @Nested
    @DisplayName("patchBike")
    class patchBikes {
        @Test
        @DisplayName("OK")
        void patchBikeOK() {
            VehicleMetaDataRequestDto metaDataReq = new VehicleMetaDataRequestDto(5.1, 100, true, false);
            BikeRequestDto req = new BikeRequestDto(
                    "BikeBrand",
                    "BikeModel",
                    "BikeColor",
                    metaDataReq,
                    15,
                    40,
                    true,
                    false,
                    null,
                    null,
                    EBikeType.MTB
            );
            BikeAdminResponseDto resp = service.add(req);

            assertEquals(1, bikeRepository.store.size());

            BikePatchRequestDto patch = new BikePatchRequestDto(null, 100, null, null, null, null, null);

            BikeAdminResponseDto patched = assertDoesNotThrow(() -> service.patch(resp.uuid(), patch));

            assertEquals(100, patched.frameSize());
            assertEquals(15, patched.weight());
        }

        @Test
        @DisplayName("Invalid (Framesize negative)")
        void patchBikeNOK() {
            VehicleMetaDataRequestDto metaDataReq = new VehicleMetaDataRequestDto(5.1, 100, true, false);
            BikeRequestDto req = new BikeRequestDto(
                    "BikeBrand",
                    "BikeModel",
                    "BikeColor",
                    metaDataReq,
                    15,
                    40,
                    true,
                    false,
                    null,
                    null,
                    EBikeType.MTB
            );
            BikeAdminResponseDto resp = service.add(req);

            assertEquals(1, bikeRepository.store.size());

            BikePatchRequestDto patch = new BikePatchRequestDto(null, -1, null, null, null, null, null);

            assertThrows(VehicleException.class,()->service.patch(resp.uuid(),patch),"bike.framesize.invalid");

        }
    }
}