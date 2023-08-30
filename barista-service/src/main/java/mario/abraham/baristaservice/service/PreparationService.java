package mario.abraham.baristaservice.service;

import jakarta.transaction.Transactional;
import mario.abraham.baristaservice.dto.PreparationRequest;
import mario.abraham.baristaservice.dto.PreparationResponse;
import mario.abraham.baristaservice.model.CurrentStatus;
import mario.abraham.baristaservice.model.Preparation;
import mario.abraham.baristaservice.repository.PreparationRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@Service
public class PreparationService {

    private final PreparationRepository preparationRepository;

    private final WebClient webClient;

    public PreparationService(PreparationRepository preparationRepository, WebClient webClient) {
        this.preparationRepository = preparationRepository;
        this.webClient = webClient;
    }

    public List<PreparationResponse> getAllPreparations() {

        List<Preparation> preparations = preparationRepository.findAll();

        return preparations.stream().map(this::mapToPreparationResponse).toList();
    }

    public PreparationResponse getPreparationByOrderNumber(String orderNumber) {

        Preparation preparation = preparationRepository.findByOrderNumber(orderNumber);

        return mapToPreparationResponse(preparation);
    }

    public PreparationResponse updateCurrentStatus(String orderNumber, String statusCode) {
        Preparation preparation = preparationRepository.findByOrderNumber(orderNumber);

        CurrentStatus desiredStatus = CurrentStatus.getCurrentStatusFromCode(statusCode);

        validateLifeCycleOfCurrentStatus(preparation.getCurrentStatus(), desiredStatus);

        preparation.setCurrentStatus(desiredStatus);

        Preparation updatedPreparation = preparationRepository.save(preparation);

        if (updatedPreparation.getCurrentStatus().equals(CurrentStatus.PICKED_UP)) {
            deleteOrderByOrderNumber(orderNumber);
        }

        return mapToPreparationResponse(updatedPreparation);
    }

    private void validateLifeCycleOfCurrentStatus(CurrentStatus currentStatus, CurrentStatus desiredStatus) {
        if ((CurrentStatus.IN_PREPARATION.equals(desiredStatus) && !CurrentStatus.WAITING.equals(currentStatus))
                || (CurrentStatus.FINISHED.equals(desiredStatus) && !CurrentStatus.IN_PREPARATION.equals(currentStatus))
                || CurrentStatus.PICKED_UP.equals(desiredStatus) && !CurrentStatus.FINISHED.equals(currentStatus)) {

            throw new IllegalArgumentException("Status change not allowed: %s -> %s".formatted(currentStatus, desiredStatus));
        }
    }

    private void deleteOrderByOrderNumber(String orderNumber) {
        webClient.delete()
                .uri("http://localhost:8080/api/orders/" + orderNumber)
                .retrieve()
                .bodyToMono(Void.class)
                .block();
    }

    private PreparationResponse mapToPreparationResponse(Preparation preparation) {
        PreparationResponse preparationResponse = new PreparationResponse();

        if (preparation != null) {
            preparationResponse.setId(preparation.getId());
            preparationResponse.setOrderNumber(preparation.getOrderNumber());
            preparationResponse.setCurrentStatus(preparation.getCurrentStatus());
        }

        return preparationResponse;
    }


    public PreparationResponse createPreparation(PreparationRequest preparationRequest) {
        Preparation preparation = mapToPreparation(preparationRequest);

        preparation.setCurrentStatus(CurrentStatus.WAITING);

        Preparation createdPreparation = preparationRepository.save(preparation);

        return mapToPreparationResponse(createdPreparation);
    }

    private Preparation mapToPreparation(PreparationRequest preparationRequest) {
        Preparation preparation = new Preparation();

        if (preparationRequest != null) {
            preparation.setOrderNumber(preparationRequest.getOrderNumber());
            preparation.setCurrentStatus(preparationRequest.getCurrentStatus());
        }

        return preparation;
    }

    @Transactional
    public void deletePreparationByOrderNumber(String orderNumber) {

        preparationRepository.deleteByOrderNumber(orderNumber);
    }
}
