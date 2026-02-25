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

  // =======================
  // MOCK OPTION (za razvoj bez backend-a)
  // =======================
  /*
  getAll: async () => {
    return new Promise((resolve) => {
      setTimeout(() => {
        resolve([
          { id: 101, name: "John Doe", email: "john@example.com" },
          { id: 102, name: "Jane Smith", email: "jane@example.com" },
        ]);
      }, 300);
    });
  },

  getById: async (id) => {
    return new Promise((resolve) => {
      setTimeout(() => {
        const employees = [
          { id: 101, name: "John Doe", email: "john@example.com" },
          { id: 102, name: "Jane Smith", email: "jane@example.com" },
        ];
        resolve(employees.find((e) => e.id === Number(id)));
      }, 300);
    });
  },
  */
};
