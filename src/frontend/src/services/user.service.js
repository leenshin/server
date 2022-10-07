import axios from 'axios';
import authHeader from './auth-header';

const API_URL = 'http://localhost:9000/api/test/';

class UserService {
    getPublicContent() {
        return axios.get(API_URL + 'all');
    }

    // 권한 정보가 필요한 request에만 Authorization 정보를 불러온다.
    getUserBoard() {
        return axios.get(API_URL + 'user', { headers: authHeader() });
    }

    getAdminBoard() {
        return axios.get(API_URL + 'admin', { headers: authHeader() });
    }
}

export default new UserService();