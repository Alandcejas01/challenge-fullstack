package com.ar.backend.services.interfaces;

import com.ar.backend.dtos.response.JourneyResponse;
import java.util.List;

public interface JourneyServiceInterface {

  List<String> getMenus();

  JourneyResponse getJourney(Long accoundId);

}
