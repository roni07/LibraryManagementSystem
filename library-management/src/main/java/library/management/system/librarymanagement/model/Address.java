package library.management.system.librarymanagement.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Embeddable
public class Address {
    private String street;
    private String city;
    private String postalCode;
    private String country;
    private String emailAddress;
    private String phoneNumber;
}
