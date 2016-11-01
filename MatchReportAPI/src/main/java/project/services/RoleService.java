package project.services;

import java.util.List;

import project.models.RoleModel;

public interface RoleService {

	public RoleModel create(RoleModel role);

	public RoleModel read(Integer id);

	public void delete(Integer id);

	public List<RoleModel> getAll(Integer id, String name);

}
