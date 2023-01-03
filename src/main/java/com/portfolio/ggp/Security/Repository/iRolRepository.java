
package com.portfolio.ggp.Security.Repository;

import com.portfolio.ggp.Security.Entity.Rol;
import com.portfolio.ggp.Security.Enuns.RolNombre;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface iRolRepository extends JpaRepository<Rol, Integer>{
        Optional<Rol> findByRolNombre(RolNombre rolNombre);
}

