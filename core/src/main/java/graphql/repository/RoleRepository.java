package graphql.repository;

import graphql.model.Role;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface RoleRepository extends PagingAndSortingRepository<Role, String> {
    List<Role> findAll();
}
