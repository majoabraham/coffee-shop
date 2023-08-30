package mario.abraham.baristaservice.controller;

import mario.abraham.baristaservice.dto.PreparationRequest;
import mario.abraham.baristaservice.dto.PreparationResponse;
import mario.abraham.baristaservice.service.PreparationService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/preparations")
public class PreparationController {

    private final PreparationService preparationService;

    public PreparationController(PreparationService preparationService) {
        this.preparationService = preparationService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<PreparationResponse> getAllPreparations() {

        return preparationService.getAllPreparations();
    }

    @GetMapping("/{orderNumber}")
    @ResponseStatus(HttpStatus.OK)
    public PreparationResponse getPreparationByOrderNumber(@PathVariable String orderNumber) {

        return preparationService.getPreparationByOrderNumber(orderNumber);
    }

    @PutMapping(value = "/{orderNumber}")
    @ResponseStatus(HttpStatus.OK)
    public PreparationResponse updateCurrentStatus(@PathVariable String orderNumber, @RequestParam String statusCode) {

        return preparationService.updateCurrentStatus(orderNumber, statusCode);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PreparationResponse createPreparation(@RequestBody PreparationRequest preparationRequest) {

        return preparationService.createPreparation(preparationRequest);
    }

    @DeleteMapping("/{orderNumber}")
    @ResponseStatus(HttpStatus.OK)
    public String deletePreparation(@PathVariable String orderNumber) {

        preparationService.deletePreparationByOrderNumber(orderNumber);

        return "Preparation of '%s' was deleted successfully".formatted(orderNumber);
    }
}
