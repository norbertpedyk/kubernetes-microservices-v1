package pl.pedyk.user.service;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.stereotype.Service;
import pl.pedyk.user.dto.UserDto;
import pl.pedyk.user.model.User;
import pl.pedyk.user.repository.UserRepository;

@Service
@AllArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    private final ModelMapper userModelMapper;


    public UserDto addUser(String userName) {
        addMappings();

        return userModelMapper.map(userRepository.save(User.builder()
                .userName(userName)
                .build()), UserDto.class);
    }

    public UserDto getUser(Long id) {
        addMappings();
        return userRepository.findById(id)
                .map(user -> userModelMapper.map(user, UserDto.class))
                .orElse(null);
    }

    @Transactional
    public boolean deleteUser(Long id) {
        if (userRepository.existsById(id)) {
            userRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }

    @Transactional
    public UserDto updateUser(Long id, UserDto user) {
        addMappings();
        if (userRepository.existsById(id)) {
            String amount = user.getAmount() != null ? user.getAmount() : userRepository.findById(id).orElseThrow(IllegalStateException::new).getAmount();
            return userModelMapper.map(userRepository.save(User.builder()
                    .id(id)
                    .userName(user.getUsername())
                    .amount(amount)
                    .build()), UserDto.class);
        } else {
            return null;
        }
    }

    private void addMappings() {
        try {
            TypeMap<User, UserDto> propertyMapper = userModelMapper.createTypeMap(User.class, UserDto.class);
            propertyMapper.addMapping(User::getUserName, UserDto::setUsername);
        } catch (IllegalStateException ignored) {
        }
    }


}
