import axios from "axios";

export const apiClient = axios.create({
  baseURL: "", // Uneti pravi URL
  headers: {
    "Content-Type": "application/json",
  },
});
