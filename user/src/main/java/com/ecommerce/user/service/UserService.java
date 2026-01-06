package com.ecommerce.user.service;

import com.ecommerce.user.dto.AddressDto;
import com.ecommerce.user.dto.UserRequest;
import com.ecommerce.user.dto.UserResponse;
import com.ecommerce.user.model.Address;
import com.ecommerce.user.model.User;
import com.ecommerce.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
//import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {


    private final UserRepository userRepository;

    public List<UserResponse> fetchUsers(){
        return userRepository.findAll().stream()
                .map(this::mapToUserResponse)
                .collect(Collectors.toList());

    }

    public Optional<UserResponse> fetchUserById(Long id){
        return userRepository.findById(id)
                .map(this::mapToUserResponse);

    }
    public void addUser(UserRequest userRequest){
        User u=new User();
        updateUserFromRequest(u,userRequest);
        userRepository.save(u);
//        return userRepository.save(user);
    }

    private void updateUserFromRequest(User u, UserRequest userRequest) {
        u.setName(userRequest.getName());
        u.setPhone(userRequest.getPhone());
        u.setEmail(userRequest.getEmail());
        if(userRequest.getAddress()!=null){
            Address ad=new Address();
            ad.setCity(userRequest.getAddress().getCity());
            ad.setCountry(userRequest.getAddress().getCountry());
            u.setAddress(ad);
        }
    }

    public void updateUser(Long id, UserRequest latest){
//        Optional<User> temp=usersList.stream().filter(u->u.getId()==id).findFirst();
        Optional<User> u=userRepository.findById(id);
        if(u.isPresent()){
            User temp=u.get();
            updateUserFromRequest(temp,latest);
//            temp.setName(latest.getName());
            userRepository.save(temp);
        }
    }

    private UserResponse mapToUserResponse(User user){
        UserResponse ur=new UserResponse();
        ur.setId(String.valueOf(user.getId()));
        ur.setName(user.getName());
        ur.setEmail(user.getEmail());
        ur.setPhone(user.getPhone());
        if(user.getAddress()!=null){
            AddressDto address=new AddressDto();
            address.setCity(user.getAddress().getCity());
            address.setCountry(user.getAddress().getCountry());
            ur.setAddress(address);
        }
        return ur;
    }
}
