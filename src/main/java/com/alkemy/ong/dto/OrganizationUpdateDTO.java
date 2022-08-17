package com.alkemy.ong.dto;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrganizationUpdateDTO {

    private String name;
    private String image;
    private String address;
    private Integer phone;
    private String email;
    private String welcomeText;
    private String aboutUsText;
    private String urlFacebook;
    private String urlLinkedin;
    private String urlInstagram;


}
