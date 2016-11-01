package project.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import project.models.RoleModel;

public interface RoleService {

	public RoleModel create(RoleModel role);

	public RoleModel read(Integer id);

	public void delete(Integer id);

	public Page<RoleModel> getAll(Integer id, String name, Pageable pageable);

}
