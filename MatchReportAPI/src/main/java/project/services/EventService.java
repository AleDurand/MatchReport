package project.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import project.models.EventModel;

public interface EventService {

	public Page<EventModel> getAll(Pageable pageable);
	
}
