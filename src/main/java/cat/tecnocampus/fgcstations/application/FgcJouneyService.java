package cat.tecnocampus.fgcstations.application;

import cat.tecnocampus.fgcstations.application.DTOs.JourneyDTO;
import cat.tecnocampus.fgcstations.application.exception.JourneyDoesNotExistsException;
import cat.tecnocampus.fgcstations.domain.Journey;
import cat.tecnocampus.fgcstations.domain.JourneyId;
import cat.tecnocampus.fgcstations.persistence.JourneyRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FgcJouneyService {
    private final JourneyRepository journeyRepository;

    public FgcJouneyService(JourneyRepository journeyRepository) {
        this.journeyRepository = journeyRepository;
    }

    public List<Journey> getAllJourneysDomain() {
        return journeyRepository.findAll();
    }

    public List<JourneyDTO> getAllJourneysDTO() {
        List<Journey> journeys = journeyRepository.findAll();
        MapperHelper.toJourneyDTOList(journeys);
    }

    public Journey getJourneyDomain(String origin, String destination) {
        return journeyRepository.findByOriginAndDestination(origin, destination)
                .orElseThrow(() -> new JourneyDoesNotExistsException("Journey from " + origin + " to " + destination + " does not exist"));
    }

    public JourneyId getJourneyId(String origin, String destination) {
        Journey journey = journeyRepository.findByOriginAndDestination(origin, destination)
                .orElseThrow(() -> new JourneyDoesNotExistsException("Journey from " + origin + " to " + destination + " does not exist"));
        return journey.getJourneyId();
    }
}
