import axios from 'axios';

const API_URL = 'http://localhost:9000/api/auth/';

class AuthService {

    // POST {userId, password} & Local Storage에 JWT 저장
    login(user) {
        return axios
            .post(API_URL + 'login', {
                userId: user.userId,
                password: user.password
            })
            .then(response => {
                if (response.data.accessToken) {
                    localStorage.setItem('user', JSON.stringify(response.data));
                }

                return response.data;
            });
    }

    // Local Storage의 JWT 파기
    logout() {
        localStorage.removeItem('user');
    }
}

export default new AuthService();