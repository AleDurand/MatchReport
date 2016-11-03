package project.services.imp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import project.models.EventModel;
import project.repositories.EventRepository;
import project.services.EventService;

@Service
public class EventServiceImp implements EventService {

	@Autowired
	private EventRepository eventRepository;
	
	@Override
	public Page<EventModel> getAll(Pageable pageable) {
		return eventRepository.findAll(pageable);
	}

}
