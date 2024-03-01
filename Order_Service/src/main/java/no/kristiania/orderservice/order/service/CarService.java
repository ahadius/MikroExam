package no.kristiania.orderservice.order.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.EntityManager;
import no.kristiania.orderservice.order.entity.CarEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.PersistenceContext;

@Service
public class CarService {

    private static final Logger LOGGER = LoggerFactory.getLogger(CarService.class);

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public void saveCarDetails(String carDetails) {
        try {
            CarEntity carEntity = parseCarDetails(carDetails);
            entityManager.persist(carEntity);
        } catch (JsonProcessingException e) {
            LOGGER.error("Error parsing car details JSON: {}", e.getMessage(), e);
            throw new IllegalArgumentException("Error parsing car details JSON", e);
        } catch (Exception e) {
            LOGGER.error("Error saving car details: {}", e.getMessage(), e);
            throw new RuntimeException("Error saving car details", e);
        }
    }

    private CarEntity parseCarDetails(String carDetails) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(carDetails, CarEntity.class);
    }

}
