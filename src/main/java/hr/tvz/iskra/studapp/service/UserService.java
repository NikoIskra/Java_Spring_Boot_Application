package hr.tvz.iskra.studapp.service;

import hr.tvz.iskra.studapp.dto.UserDTO;

import java.util.Optional;

public interface UserService {
    Optional<UserDTO> findByUsername(String username);
}
