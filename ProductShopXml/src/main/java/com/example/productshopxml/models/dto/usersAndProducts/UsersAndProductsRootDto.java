package com.example.productshopxml.models.dto.usersAndProducts;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.xml.bind.annotation.*;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@XmlRootElement(name = "users")
@XmlAccessorType(XmlAccessType.FIELD)
public class UsersAndProductsRootDto {

    @XmlAttribute(name = "count")
    private long usersCount;
    @XmlElement(name = "user")
    private List<UsersWithSoldProductsDto> users;

    public UsersAndProductsRootDto(List<UsersWithSoldProductsDto> users) {
        this.users = users;
        this.usersCount = users.size();
    }
}
