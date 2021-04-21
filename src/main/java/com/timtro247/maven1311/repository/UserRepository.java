package com.timtro247.maven1311.repository;

import com.timtro247.maven1311.model.Users;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<Users, Long> {

    Users findByUsernameAndDeletedAtNull(String username);

    Users findByPhoneNumberAndDeletedAtNull(String phoneNumber);

    Users findByIdAndDeletedAtNull(Long id);

    Page<Users> findAllByFullNameContainingOrUsernameContainingOrIdentityCardContainingOrAddressContaining(Pageable pageable,
                                                                                                           String fullName,
                                                                                                           String username,
                                                                                                           String identityCard,
                                                                                                           String address);

    Optional<Users> findById(Long id);
}
