package in.reqres.models;

import lombok.Data;

@Data
public class GetSingleUserResponseModel {
    Data data;
    Support support;

    @lombok.Data
    public static class Data {
        Integer id;
        String email, first_name, last_name, avatar;
        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getFirstName() {
            return first_name;
        }

        public void setFirstName(String first_name) {
            this.first_name = first_name;
        }

        public String getLastName() {
            return last_name;
        }

        public void setLastName(String last_name) {
            this.last_name = last_name;
        }

        public String getAvatar() {
            return avatar;
        }

        public void setAvatar(String avatar) {
            this.avatar = avatar;
        }

    }

    @lombok.Data
    public static class Support {
        String url, text;
    }

}
