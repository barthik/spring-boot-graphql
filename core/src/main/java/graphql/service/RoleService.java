package graphql.service;

import graphql.exception.NotFoundException;
import graphql.model.Role;
import graphql.repository.RoleRepository;
import io.leangen.graphql.annotations.GraphQLArgument;
import io.leangen.graphql.annotations.GraphQLMutation;
import io.leangen.graphql.annotations.GraphQLQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleService {
    private final RoleRepository roleRepository;

    @Autowired
    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @GraphQLQuery(name = "roles")
    public List<Role> findAll() {
        return roleRepository.findAll();
    }

    @GraphQLQuery(name = "role")
    public Role findById(@GraphQLArgument(name = "id") String id) {
        return roleRepository.findById(id).orElseThrow(() -> new NotFoundException("Role not found", id));
    }

    @GraphQLMutation(name = "newRole")
    public Role saveRole(@GraphQLArgument(name = "role") Role role) {
        return roleRepository.save(role);
    }

    @GraphQLMutation(name = "deleteRole")
    public void deleteRole(@GraphQLArgument(name = "id") String id) {
        roleRepository.deleteById(id);
    }
}
