package com.airsoftShop.airsoftShop.service;

import com.airsoftShop.airsoftShop.dto.UserProfileDto;
import com.airsoftShop.airsoftShop.dto.UserRegistrationDto;
import com.airsoftShop.airsoftShop.model.User;
import com.airsoftShop.airsoftShop.repository.UserRepository;
import com.airsoftShop.airsoftShop.util.DefaultValues;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.Arrays;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private ResourceLoader resourceLoader;


    public boolean saveUser(UserRegistrationDto userRegistrationDto) {
        if (userRepository.findByEmail(userRegistrationDto.getEmail()).isEmpty() && userRepository.findByUsername(userRegistrationDto.getUsername()).isEmpty()) {
            User user = new User();
            user.setEmail(userRegistrationDto.getEmail());
            user.setUsername(userRegistrationDto.getUsername());
            String password = passwordEncoder.encode(userRegistrationDto.getPassword());
            user.setPassword(password);
            user.setRole(DefaultValues.defaultRegistrationRole);
            userRepository.save(user);
            user.setLogoImgName(null);
            return true;

        }
        return false;
    }

    public void updateUser(UserProfileDto userProfileDto, MultipartFile multipartFile) throws IOException {
        User user = userRepository.findById(Long.valueOf(userProfileDto.getId())).orElseThrow();

//        user.setUsername(userProfileDto.getUsername());
//

        user.setFirstName(userProfileDto.getFirstName());


        user.setLastName(userProfileDto.getLastName());


        user.setPhoneNumber(userProfileDto.getPhoneNumber());


        user.setAddress(userProfileDto.getAddress());


        user.setCity(userProfileDto.getCity());


        user.setCountry(userProfileDto.getCountry());


        user.setPostalCode(userProfileDto.getPostalCode());

        try {
            user.setLogoImgName(updateLogo(user, multipartFile));
        } catch (Exception e) {
//            Let user.logoImgName  as it was
        }

        try {
            user.setDob(LocalDate.parse(userProfileDto.getDob()));
        } catch (DateTimeParseException dateTimeParseException) {
            user.setDob(null);
        }


        userRepository.save(user);


    }


    public UserProfileDto getUserProfileDto(String email) throws IOException {
        User user = userRepository.findByEmail(email).orElseThrow();
        UserProfileDto result = new UserProfileDto();
        result.setAddress(user.getAddress());
        result.setCity(user.getCity());
        result.setDob(String.valueOf(user.getDob()));
        result.setId(String.valueOf(user.getId()));
        result.setFirstName(user.getFirstName());
        result.setLastName(user.getLastName());
        result.setPhoneNumber(user.getPhoneNumber());
        result.setCountry(user.getCountry());
        result.setPostalCode(user.getPostalCode());
        result.setUsername(user.getUsername());
        Path logoImgPath = getLogoImgPath(user);
        if (logoImgPath != null) {
            result.setLogoImg(getBase64EncodedFileContent(logoImgPath));
        }

        return result;
    }


    private static final String LOCAL_ROOT = Paths.get("src", "main", "images").toString();
    private static final String LOGOS_PATH = Paths.get(LOCAL_ROOT, "users", "logos").toString();

    private Path getLogoImgPath(User user) {

        String logoImgName = user.getLogoImgName();
        if (logoImgName == null || logoImgName.isEmpty()) {
            return null;
        }

        String userId = String.valueOf(user.getId());
        return Paths.get(LOGOS_PATH, userId, logoImgName);
    }


    private String getBase64EncodedFileContent(Path logoImgPath) throws IOException {
        return Base64.encodeBase64String(Files.readAllBytes(logoImgPath));
    }

    //pas 1: Se verifica daca folder ul cu numele userid exsita.
    //pas2 : Daca nu exsita il cream;
    //pas3 : Se verifica daca fieserul cu numele cu mulipartFile.originalName exista
    //pas4: Daca nu exista il cream
    //pas5 : Se verifica daca fisierul exsitent are acelasi continut cu fisierul uploadat
    // pas6: Daca nu, se suprascrie
    private String updateLogo(User user, MultipartFile multipartFile) throws IOException {
        String userId = String.valueOf(user.getId());

        Path userPath = Paths.get(LOGOS_PATH, userId);
        if (!(Files.exists(userPath) && Files.isDirectory(userPath))) {
            Files.createDirectory(userPath);
        }
        String logoName = multipartFile.getOriginalFilename();
        Path userLogoPath = Paths.get(String.valueOf(userPath), logoName);
        if (!Files.exists(userLogoPath)) {
            Files.createFile(userLogoPath);
        }
        byte[] uploadedContentFile = multipartFile.getBytes();
        byte[] exsitingContentFile = Files.readAllBytes(userLogoPath);

        if (!Arrays.equals(uploadedContentFile, exsitingContentFile)) {
            Files.write(userLogoPath, uploadedContentFile);
        }
        return logoName;
    }


    public void deleteUser(UserProfileDto userProfileDto){
        userRepository.deleteById(Long.valueOf(userProfileDto.getId()));
    }

}
