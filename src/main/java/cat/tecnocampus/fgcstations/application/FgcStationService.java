package cat.tecnocampus.fgcstations.application;

import cat.tecnocampus.fgcstations.application.DTOs.StationDTO;
import cat.tecnocampus.fgcstations.application.DTOs.StationTopFavoriteJourney;
import cat.tecnocampus.fgcstations.application.exception.StationDoesNotExistsException;
import cat.tecnocampus.fgcstations.application.mapper.MapperHelper;
import cat.tecnocampus.fgcstations.domain.Station;
import cat.tecnocampus.fgcstations.persistence.StationRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FgcStationService {
    private final StationRepository stationRepository;

    public FgcStationService(StationRepository stationRepository) {
        this.stationRepository = stationRepository;
    }

    public List<StationDTO> getStationsDTO() {
        List<Station> stations = stationRepository.findAll();
        return MapperHelper.toStationDTOList(stations);
    }

    public List<Station> getStationsDomain() {
        public List<Station> getStationsDomain() {
            return stationRepository.findAll();
        }
    }

    public Station getStation(String name) {
        return stationRepository.findByName(name)
                .orElseThrow(() -> new StationDoesNotExistsException("Station with name " + name + " does not exist"));

    }

    public StationDTO getStationDTO(String name) {
        Station station = stationRepository.findByName(name)
                .orElseThrow(() -> new StationDoesNotExistsException("Station with name " + name + " does not exist"));
        return MapperHelper.toStationDTO(station);
    }

    public List<StationTopFavoriteJourney> getStationsOrderedByFavoriteJourneysAsEitherOriginOrDestination() {
        // TODO 5: this is an optional exercise because is quite tricky. You need to use a native query because is no possible to
        //  have a derived table (subquery).
        //  You first need to use a 'UNION' to get the stations either as origin or destination of a Journey. Then you need to group and count
        return null;
    }
}
