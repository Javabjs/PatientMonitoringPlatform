import axios from "axios";

const BASE_URL = "http://localhost:8080/api/v1/user/register";

export const registerUser = (user) => axios.post(`${BASE_URL}/register`, user);
  
export const createUser = (user) => axios.post(BASE_URL, user);
export const getAllUsers = () => axios.get(BASE_URL);
export const getUserByUuid = (uuid) => axios.get(`${BASE_URL}/${uuid}`);
export const updateUserByUuid = (uuid, user) => axios.put(`${BASE_URL}/${uuid}`, user);
export const deleteUserByUuid = (uuid) => axios.delete(`${BASE_URL}/${uuid}`);
