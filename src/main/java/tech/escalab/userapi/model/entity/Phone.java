package tech.escalab.userapi.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;
import org.hibernate.annotations.GenericGenerator;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "phones")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Phone implements Serializable  {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(name = "phone_id", updatable = false, nullable = false)
    private UUID phoneId;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @NotEmpty(message = "El número es Obligatorio")
    @Column(nullable = false, unique = true)
    private String phoneNumber;
    @NotEmpty(message = "El código ciudad Obligatorio")
    @Column(name="cod_city", nullable = false, unique = false)
    private String codCity;
    @NotEmpty(message = "El código pais Obligatorio")
    @Column(name="cod_country", nullable = false, unique = false)
    private String codCountry;
    @Column(nullable = true, unique = false)
    private Boolean is_deleted = false;
    @Column(name = "created_at")
    private LocalDateTime createdAt;
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
    @Column(nullable = true, unique = false)
    private LocalDateTime deleted_at;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Phone that = (Phone) o;
        return phoneId == that.phoneId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(phoneId);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Phone{");
        sb.append("id=").append(phoneId);
        sb.append(", phoneNumber='").append(phoneNumber).append('\'');
        sb.append('}');
        return sb.toString();
    }

    public UUID getPhoneId() {
        return phoneId;
    }

    public User getUser() {
        return user;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getCodCity() {
        return codCity;
    }

    public String getCodCountry() {
        return codCountry;
    }

    public Boolean getIs_deleted() {
        return is_deleted;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public LocalDateTime getDeleted_at() {
        return deleted_at;
    }

    public void setPhoneId(UUID phoneId) {
        this.phoneId = phoneId;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setCodCity(String codCity) {
        this.codCity = codCity;
    }

    public void setCodCountry(String codCountry) {
        this.codCountry = codCountry;
    }

    public void setIs_deleted(Boolean is_deleted) {
        this.is_deleted = is_deleted;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public void setDeleted_at(LocalDateTime deleted_at) {
        this.deleted_at = deleted_at;
    }
}
