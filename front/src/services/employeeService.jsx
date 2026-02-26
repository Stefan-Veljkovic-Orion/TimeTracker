// src/services/employeeService.js
import { apiClient } from "../api/client";

export const employeeService = {
  // GET all employees
  getAll: async () => {
    const response = await apiClient.get("/employees");
    return response.data;
  },

  // GET employee by id (opciono)
  getById: async (id) => {
    const response = await apiClient.get(`/employees/${id}`);
    return response.data;
  },
};
