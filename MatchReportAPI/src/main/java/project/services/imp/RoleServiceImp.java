package project.services.imp;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import project.exceptions.EntityNotFoundException;
import project.models.QRoleModel;
import project.models.RoleModel;
import project.repositories.RoleRepository;
import project.services.RoleService;

import com.querydsl.core.types.dsl.BooleanExpression;

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
	public Page<RoleModel> getAll(Integer id, String name, Pageable pageable) {
		List<BooleanExpression> expressions = new ArrayList<BooleanExpression>();
		
		QRoleModel role = QRoleModel.roleModel;
		BooleanExpression expression1 = (id != null) ? role.id.eq(id) : null;
		expressions.add(expression1);

		BooleanExpression expression2 = (name != null) ? role.name.contains(name) : null;
		expressions.add(expression2);
		
		BooleanExpression expression = null;
		for (BooleanExpression ex : expressions) {
			if (expression == null) {
				expression = ex;
			} else {
				expression = expression.or(ex);
			}
		}

		return roleRepository.findAll(expression, pageable);
	}

}
