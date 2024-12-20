import axios from 'axios';
import store from '../store';

const instance = axios.create({
  baseURL: 'http://localhost:8081', // Replace with your backend URL
});

// Add a request interceptor
instance.interceptors.request.use(
  config => {
    const token = store.state.auth.token;
    if (token) {
      config.headers['Authorization'] = `Bearer ${token}`;
    }
    return config;
  },
  error => {
    return Promise.reject(error);
  }
);

export default instance;
