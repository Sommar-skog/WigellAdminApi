package com.example.wigelladminapi.services;

import com.example.wigelladminapi.entities.Address;
import com.example.wigelladminapi.entities.Member;
import com.example.wigelladminapi.exceptions.InvalidInputException;
import com.example.wigelladminapi.exceptions.NotUniqException;
import com.example.wigelladminapi.exceptions.ResourceNotFoundException;
import com.example.wigelladminapi.exceptions.UnprocessableEntityException;
import com.example.wigelladminapi.repositories.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;
    private final AddressServiceImpl addressServiceImpl;

    @Autowired
    public MemberServiceImpl(MemberRepository memberRepository, AddressServiceImpl addressServiceImpl) {
        this.memberRepository = memberRepository;
        this.addressServiceImpl = addressServiceImpl;
    }

    @Override
    public List<Member> getAllMembers() {
        return memberRepository.findAll();
    }

    @Override
    public Member getMemberById(Long id) {
        Optional<Member> result = memberRepository.findById(id);
        if (result.isPresent()) {
            return result.get();
        }
        throw new ResourceNotFoundException("Member", "id", id);
    }

    @Override
    public Member updateMember(Member member) {
        Optional<Member> memberToUpdate = memberRepository.findById(member.getId());

        if (memberToUpdate.isPresent()) {
            Member updatedMember = memberToUpdate.get();

            if (isValidForUpdate(member.getFirstName())){
                updatedMember.setFirstName(member.getFirstName());
            }

            if (isValidForUpdate(member.getLastName())){
                updatedMember.setLastName(member.getLastName());
            }

            if (isValidForUpdate(member.getEmail()) && !isEmailTaken(member.getEmail())){
                updatedMember.setEmail(member.getEmail());
            }

            if (isValidForUpdate(member.getPhone())){
                updatedMember.setPhone(member.getPhone());
            }

            if (isValidForUpdate(member.getDateOfBirth()) && isDateOfBirthValid(member.getDateOfBirth())){
                updatedMember.setDateOfBirth(member.getDateOfBirth());
            }

            if (isValidForUpdate(member.getAddress())){
                setValidatedAddress(member, updatedMember);
            }

            return memberRepository.save(updatedMember);
        }

        throw new ResourceNotFoundException("Member", "id", member.getId());
    }

    @Override
    public Member addMember(Member member) {
        validateInputAddMember("Address", member.getAddress());
        setValidatedAddress(member);

        validateInputAddMember("Email", member.getEmail());
        if (isEmailTaken(member.getEmail())) {
            throw new NotUniqException("Email", member.getEmail());
        }

        validateInputAddMember("Phone", member.getPhone());
        validateInputAddMember("FirstName", member.getFirstName());
        validateInputAddMember("LastName", member.getLastName());

        validateInputAddMember("DateOfBirth", member.getDateOfBirth());
        if (!isDateOfBirthValid(member.getDateOfBirth())) {
            throw new InvalidInputException("Member", "DateOfBirth", member.getDateOfBirth());
        }
        return memberRepository.save(member);
    }

    @Override
    public ResponseEntity<String> deleteMember(Long id) {
        Optional<Member> result = memberRepository.findById(id);
        if (result.isPresent()) {
            memberRepository.delete(result.get());
            return new ResponseEntity<>("Member with id " + id + " deleted", HttpStatus.OK);
        }
        throw new ResourceNotFoundException("Member", "id", id);
    }

    private boolean isValidForUpdate(Object input) {
        return input != null && (!(input instanceof String) || !((String) input).isEmpty());
    }

    private void validateInputAddMember(String field, Object input) {
        if (input == null || input instanceof String && ((String) input).isEmpty()) {
            throw new InvalidInputException("Member", field, input);
        }
    }

    private void setValidatedAddress(Member member) {
        validateAddressFields(member.getAddress());
        Address address = validateAddress(member.getAddress());
        member.setAddress(address);
    }

    private void setValidatedAddress(Member originalMember, Member incomingMember) {
        validateAddressFields(originalMember.getAddress());
        Address address = validateAddress(originalMember.getAddress());
        incomingMember.setAddress(address);
    }

    private Address validateAddress(Address address) {
        Address toReturn;
        if (address.getId() != null) {
            toReturn = addressServiceImpl.findAddressById(address.getId());
            if (toReturn != null) {
                return toReturn;
            }
        } else if (address.getStreet() != null && address.getPostalCode() != null && address.getCity() != null) {
            toReturn = addressServiceImpl.findAdressByStreetAndPostalCodeAndCity(address.getStreet(), address.getPostalCode(), address.getCity());
            if (toReturn != null) {
                return toReturn;
            }
            return addressServiceImpl.addAdress(address);
        }
        throw new UnprocessableEntityException("Failed to validate or create address", address);
    }

    private void validateAddressFields(Address address) {
        if (address != null) {
            if (address.getId() == null) {
                if (address.getStreet() == null || address.getStreet().isEmpty() ||
                        address.getPostalCode() == null || address.getPostalCode().isEmpty() ||
                        address.getCity() == null || address.getCity().isEmpty()) {
                    throw new InvalidInputException("Member", "address fields", address);
                }
            }
        }
    }

    private boolean isEmailTaken(String email) {
        return memberRepository.existsByEmail(email);
    }

    private boolean isDateOfBirthValid(LocalDate dateOfBirth) {
        return dateOfBirth.isBefore(LocalDate.now()) && dateOfBirth.isAfter(LocalDate.now().minusYears(115));
    }
}
