import axios from "axios";

const API_URL = "http://localhost:8080/api/auth/";

class AuthService {
    // POST {userId, password} && Local Storage에 JWT 저장
    login(userId, password) {
        return axios
            .post(API_URL + "login", { userId, password })
            .then((response) => {
                if (response.data.accessToken) {
                    localStorage.setItem("user", JSON.stringify(response.data));
                }

                return response.data;
            });
    }

    // Local Storage에서 JWT 제거
    logout() {
        localStorage.removeItem("user");
    }
}

export default new AuthService();