package project.services.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import project.exceptions.EntityNotFoundException;
import project.models.RoleModel;
import project.repositories.RoleRepository;
import project.services.RoleService;

@Service
public class RoleServiceImp implements RoleService {

	@Autowired
	private RoleRepository roleRepository;

	@Override
	public RoleModel create(RoleModel role) {
		return roleRepository.save(role);
	}

	@Override
	public RoleModel read(Integer id) {
		if (!roleRepository.exists(id)) {
			throw new EntityNotFoundException("resource.not_found", null);
		}
		return roleRepository.findOne(id);
	}

	@Override
	public void delete(Integer id) {
		if (!roleRepository.exists(id)) {
			throw new EntityNotFoundException("resource.not_found", null);
		}
		roleRepository.delete(id);
	}

	@Override
	public List<RoleModel> getAll() {
		return roleRepository.findAll();
	}

}
