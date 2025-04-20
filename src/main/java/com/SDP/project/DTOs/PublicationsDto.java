package com.SDP.project.DTOs;

import lombok.*;

import java.util.Objects;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PublicationsDto {
    private int id;
    private String gradewithcategory;

    public PublicationsDto(String gradewithcategory) {
        this.gradewithcategory = gradewithcategory;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PublicationsDto that = (PublicationsDto) o;
        return Objects.equals(gradewithcategory, that.gradewithcategory);
    }

    @Override
    public int hashCode() {
        return Objects.hash(gradewithcategory);
    }
}
